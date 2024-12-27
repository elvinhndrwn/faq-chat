package com.playground.chatbot_kab_bantul.repository;

import com.playground.chatbot_kab_bantul.model.Dusun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("all")
public interface DusunRepository extends JpaRepository<Dusun, Long> {
    @Query(value = "select  count(k.id)\n" +
            "from dusun k\n" +
            "inner join kelurahan k2 on k2.id = k.id_kelurahan \n" +
            "inner join kecamatan k3 on k3.id = k2.id_kecamatan \n" +
            "where k2.nama_kelurahan ilike %:val% or  k3.nama_kecamatan ilike %:val% ", nativeQuery = true)
    Integer countTotalDusun(@Param("val") String val);

    @Query(value = "select k.* " +
            "from dusun k " +
            "inner join kelurahan k2 on k2.id = k.id_kelurahan " +
            "inner join kecamatan k3 on k3.id = k2.id_kecamatan " +
            "where k2.nama_kelurahan ilike %:location% or  k3.nama_kecamatan ilike %:location% ", nativeQuery = true)
    List<Dusun> findDusunByLocation(@Param("location") String location);
}
