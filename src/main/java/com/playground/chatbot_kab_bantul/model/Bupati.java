package com.playground.chatbot_kab_bantul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "bupati")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bupati implements Serializable {
    @Serial
    private static final long serialVersionUID = -1170110355764181852L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_bupati")
    private String namaBupati;

    @Column(name = "periode")
    private String periode;
}
