/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service;

import com.auto.brand.model.Cantitati;
import com.auto.brand.repository.CantitatiRepository;
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
public class CantitatiService {

    @Autowired
    private CantitatiRepository cantitatiRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void newData(List<Cantitati> canList) {

        String sql = "INSERT INTO cantitati (id_cantitate, cantitate) VALUES (?, ?)";

        // Clear the database table
        cantitatiRepository.deleteAll();

        // Save all records to the database
        jdbcTemplate.batchUpdate(sql, canList, canList.size(), (ps, cantitate) -> {
            ps.setString(1, cantitate.getId_cantitate());
            ps.setDouble(2, (Double) cantitate.getCantitate());
        });
//        printFirstTenRows();
    }

//    public void printFirstTenRows() {
//        String sql = "SELECT * FROM cantitati LIMIT 10";
//
//        // Query to get the first 10 rows
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//
//        // Print the rows in the console
//        System.out.println("First 10 rows from the cantitati table:");
//        for (Map<String, Object> row : rows) {
//            System.out.println("id_cantitate: " + row.get("id_cantitate") + ", cantitate: " + row.get("cantitate"));
//        }
//    }
}
