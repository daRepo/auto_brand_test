/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service;

import com.auto.brand.model.Comenzi;
import com.auto.brand.repository.ComenziRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel
 */
@Service
public class ComenziService {

    @Autowired
    private ComenziRepository comenziRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void newData(List<Comenzi> canList) {

        comenziRepository.deleteAll();

        comenziRepository.saveAll(canList);
    }

    public List<Comenzi> findByNumarFactura(String numarFactura) {
        String sql = "SELECT * FROM comenzi WHERE numar_factura = ?";
        return jdbcTemplate.query(sql, new Object[]{numarFactura}, new BeanPropertyRowMapper<>(Comenzi.class));
    }
}
