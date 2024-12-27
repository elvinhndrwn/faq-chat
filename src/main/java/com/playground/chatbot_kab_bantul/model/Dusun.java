package com.playground.chatbot_kab_bantul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "dusun")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dusun implements Serializable {
    @Serial
    private static final long serialVersionUID = 5228194795379891078L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_dusun")
    private String namaDusun;

    @Column(name = "id_kelurahan")
    private Long idKelurahan;
}
