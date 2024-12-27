package com.playground.chatbot_kab_bantul.repository;

import com.playground.chatbot_kab_bantul.model.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {
    @Query(value = "select count(id) from kecamatan", nativeQuery = true)
    Integer countTotalKecamatan();

    @Query(value = "select * " +
            "from kecamatan k ", nativeQuery = true)
    List<Kecamatan> findKecamatanByLocation();
}
