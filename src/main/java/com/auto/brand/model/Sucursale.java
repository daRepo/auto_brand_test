/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Daniel
 */
@Table("SUCURSALE")
public class Sucursale {
    
    @Id
    private Long numar_comanda;
    private String sucursala;

    public String getSucursala() {
        return sucursala;
    }

    public void setSucursala(String sucursala) {
        this.sucursala = sucursala;
    }

    public Long getNumar_comanda() {
        return numar_comanda;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setNumar_comanda(Long numar_comanda) {
        this.numar_comanda = numar_comanda;
    }
}
