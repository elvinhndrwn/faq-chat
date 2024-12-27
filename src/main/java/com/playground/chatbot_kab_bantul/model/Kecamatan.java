package com.playground.chatbot_kab_bantul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "kecamatan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kecamatan implements Serializable {

    @Serial
    private static final long serialVersionUID = 1270416336938720080L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kecamatan")
    private String namaKecamatan;
}
