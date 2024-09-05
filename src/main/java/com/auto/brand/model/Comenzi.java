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
@Table("COMENZI")
public class Comenzi {
    
    @Id
    private Long id;
    private Long numar_factura;
    private Long numar_comanda;
    private Long cod_articol ;
    private String id_cantitate;

    public Long getNumar_factura() {
        return numar_factura;
    }

    public void setNumar_factura(Long numar_factura) {
        this.numar_factura = numar_factura;
    }

    public Long getNumar_comanda() {
        return numar_comanda;
    }

    public void setNumar_comanda(Long numar_comanda) {
        this.numar_comanda = numar_comanda;
    }

    public Long getCod_articol() {
        return cod_articol;
    }

    public void setCod_articol(Long cod_articol) {
        this.cod_articol = cod_articol;
    }

    public String getId_cantitate() {
        return id_cantitate;
    }

    public void setId_cantitate(String id_cantitate) {
        this.id_cantitate = id_cantitate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
