/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service;

import com.auto.brand.model.Comenzi;
import com.auto.brand.repository.ComenziRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

//        String sql = "INSERT INTO comenzi (numar_factura, numar_comanda, cod_articol, id_cantitate) VALUES (?, ?, ?, ?)";

        // Clear the database table
        comenziRepository.deleteAll();

        // Save all records to the database
//        jdbcTemplate.batchUpdate(sql, canList, canList.size(), (ps, comanda) -> {
//            ps.setLong(1, comanda.getNumar_factura());
//            ps.setLong(2, comanda.getNumar_comanda());
//            ps.setLong(3, comanda.getCod_articol());
//            ps.setString(4, comanda.getId_cantitate());
//        });
        comenziRepository.saveAll(canList);
        //printFirstTenRows();
    }

    public void printFirstTenRows() {
        String sql = "SELECT * FROM comenzi LIMIT 30";

        // Query to get the first 10 rows
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Print the rows in the console
        System.out.println("First 10 rows from the cantitati table:");
        for (Map<String, Object> row : rows) {
            System.out.println("1: " + row.get("numar_factura") + ", 2: " + row.get("numar_comanda"));
        }
    }
}
