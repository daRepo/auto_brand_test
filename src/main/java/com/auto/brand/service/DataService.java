/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service;

import com.auto.brand.model.Data;
import com.auto.brand.repository.DataRepository;
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
public class DataService {
    
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void newData(List<Data> canList) {

        String sql = "INSERT INTO `data` (numar_factura, `data`) VALUES (?, ?)";

        // Clear the database table
        dataRepository.deleteAll();

        // Save all records to the database
        jdbcTemplate.batchUpdate(sql, canList, canList.size(), (ps, data) -> {
            ps.setLong(1, data.getNumar_factura());
            ps.setDate(2, java.sql.Date.valueOf(data.getData()));
        });
        //printFirstTenRows();
    }

    public void printFirstTenRows() {
        String sql = "SELECT * FROM data LIMIT 30";

        // Query to get the first 10 rows
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Print the rows in the console
        System.out.println("First 10 rows from the cantitati table:");
        for (Map<String, Object> row : rows) {
            System.out.println("1: " + row.get("numar_factura") + ", 2: " + row.get("data"));
        }
    }
}