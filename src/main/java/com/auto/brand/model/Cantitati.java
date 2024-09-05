/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.model;

import com.opencsv.bean.CsvBindByName;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
/**
 *
 * @author Daniel
 */
@Table("CANTITATI")
public class Cantitati {
    
    @Id
    @CsvBindByName(column = "id_cantitate")
    private String id_cantitate;
    
    @CsvBindByName(column = "cantitate")
    private double cantitate;

    public String getId_cantitate() {
        return id_cantitate;
    }

    public void setId_cantitate(String id_cantitate) {
        this.id_cantitate = id_cantitate;
    }

    public double getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
