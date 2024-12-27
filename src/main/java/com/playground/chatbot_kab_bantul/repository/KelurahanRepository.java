package com.playground.chatbot_kab_bantul.repository;

import com.playground.chatbot_kab_bantul.model.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("all")
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {
    @Query(value = "select  count(k.id) \n" +
            "from kelurahan k \n" +
            "inner join kecamatan k2 on k2.id  = k.id_kecamatan \n" +
            "where k2.nama_kecamatan ilike %:kecamatan% ", nativeQuery = true)
    Integer countTotalKelurahan(@Param("kecamatan") String kecamatan);

    @Query(value = "select k.* " +
            "from kelurahan k " +
            "inner join kecamatan k3 on k3.id = k.id_kecamatan " +
            "where k3.nama_kecamatan ilike %:location% ", nativeQuery = true)
    List<Kelurahan> findKelurahanByLocation(@Param("location") String location);
}
