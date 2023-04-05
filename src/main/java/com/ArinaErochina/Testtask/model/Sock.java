package com.ArinaErochina.Testtask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "socks")
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private Long cottonPart;

    @Column(name = "quantity")
    private Long quantity;
}
