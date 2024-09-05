/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.model;

import com.opencsv.bean.CsvDate;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
/**
 *
 * @author Daniel
 */
@Table("DATA")
public class Data {
    
    @Id
    private Long numar_factura;
    @CsvDate("M/d/yyyy")
    private LocalDate data;

    public Long getNumar_factura() {
        return numar_factura;
    }

    public void setNumar_factura(Long numar_factura) {
        this.numar_factura = numar_factura;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
