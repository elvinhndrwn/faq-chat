package com.playground.chatbot_kab_bantul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "kelurahan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kelurahan implements Serializable {
    @Serial
    private static final long serialVersionUID = -3901746468325050185L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kelurahan")
    private String namaKelurahan;

    @Column(name = "id_kecamatan")
    private Long idKecamatan;
}
