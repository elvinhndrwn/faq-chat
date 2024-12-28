package com.playground.chatbot_kab_bantul.service.impl;

import com.playground.chatbot_kab_bantul.dto.WitAiResponseDto;
import com.playground.chatbot_kab_bantul.model.Dusun;
import com.playground.chatbot_kab_bantul.model.Kecamatan;
import com.playground.chatbot_kab_bantul.model.Kelurahan;
import com.playground.chatbot_kab_bantul.repository.DusunRepository;
import com.playground.chatbot_kab_bantul.repository.KecamatanRepository;
import com.playground.chatbot_kab_bantul.repository.KelurahanRepository;
import com.playground.chatbot_kab_bantul.service.DataModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataModelServiceImpl implements DataModelService {
    private final KecamatanRepository kecamatanRepository;
    private final KelurahanRepository kelurahanRepository;
    private final DusunRepository dusunRepository;


    @Override
    public String getName(String region, String location, String period) {
       return "";
    }

    @Override
    public String getCount(WitAiResponseDto rsp) {
        try {
            String region0 = rsp.getEntities().get("region:region").get(0).getValue();
            String location0 = rsp.getEntities().get("location:location").get(0).getValue();

            // Define a Map to associate region with corresponding count method
            Map<String, String> regionCounts = new HashMap<>();
            regionCounts.put("kecamatan", "Jumlah kecamatan yang ada di Kabupaten Bantul adalah %d");
            regionCounts.put("kelurahan", "Jumlah kelurahan yang ada di %s adalah %d");
            regionCounts.put("dusun", "Jumlah dusun yang ada di %s adalah %d");

            String resultTemplate = regionCounts.get(region0);

            if (resultTemplate != null) {
                int count = 0;
                switch (region0) {
                    case "kecamatan":
                        count = kecamatanRepository.countTotalKecamatan();
                        break;
                    case "kelurahan":
                        count = kelurahanRepository.countTotalKelurahan(location0);
                        break;
                    case "dusun":
                        count = dusunRepository.countTotalDusun(location0);
                        break;
                }

                return String.format(resultTemplate, region0.equals("kecamatan") ? "" : location0, count);
            } else {
                return "Maaf, belum ada data yang tersimpan";
            }
        } catch (Exception e) {
            log.error("Error processing message", e);
            return "Terjadi kesalahan saat memproses permintaan.";
        }
    }

    @Override
    public String getList(WitAiResponseDto rsp) {
        try {
            String result = "";
            StringBuilder builder = new StringBuilder();
            List<Kelurahan> kelurahan = new ArrayList<>();
            List<Dusun> dusun = new ArrayList<>();
            List<Kecamatan> kecamatan = new ArrayList<>();
            int initialNumber = 1;

            String region0 = rsp.getEntities().get("region:region").get(0).getValue();
            String location0 = rsp.getEntities().get("location:location").get(0).getValue();

            switch (region0) {
                case "kelurahan":
                    kelurahan = kelurahanRepository.findKelurahanByLocation(location0);
                    if(!kelurahan.isEmpty()){
                        builder.append("Kelurahan yang ada di ").append(location0).append(" adalah:\n");
                        for (Kelurahan k : kelurahan) {
                            builder.append(initialNumber++).append(". ")
                                    .append(k.getNamaKelurahan()).append("\n");
                        }
                        result = builder.toString();
                    }
                    break;
                case "dusun":
                    dusun = dusunRepository.findDusunByLocation(location0);
                    if(!dusun.isEmpty()){
                        builder.append("Dusun yang ada di ").append(location0).append(" adalah:\n");
                        for (Dusun d : dusun) {
                            builder.append(initialNumber++).append(". ")
                                    .append(d.getNamaDusun()).append("\n");
                        }
                        result = builder.toString();
                    }
                    break;
                case "kecamatan":
                    kecamatan = kecamatanRepository.findKecamatanByLocation();
                    if(!kecamatan.isEmpty()){
                        builder.append("Kecamatan yang ada di Kabupaten Bantul adalah:\n");
                        for (Kecamatan k : kecamatan) {
                            builder.append(initialNumber++).append(". ")
                                    .append(k.getNamaKecamatan()).append("\n");
                        }
                        result = builder.toString();
                    }
                    break;
                default:
                    result = "<none>";
                    break;

            }

            return result;
        }catch (Exception e) {
            log.error("Error processing message", e);
            return "Terjadi kesalahan saat memproses permintaan.";
        }
    }

}
