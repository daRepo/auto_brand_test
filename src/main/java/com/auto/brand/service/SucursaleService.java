/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service;

import com.auto.brand.model.Sucursale;
import com.auto.brand.repository.SucursaleRepository;
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
public class SucursaleService {

    @Autowired
    private SucursaleRepository sucursaleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void newData(List<Sucursale> canList) {

        String sql = "INSERT INTO `sucursale` (numar_comanda, `sucursala`) VALUES (?, ?)";

        // Clear the database table
        sucursaleRepository.deleteAll();

        // Save all records to the database
        jdbcTemplate.batchUpdate(sql, canList, canList.size(), (ps, cantitate) -> {
            ps.setLong(1, cantitate.getNumar_comanda());
            ps.setString(2, cantitate.getSucursala());
        });
//        printFirstTenRows();
    }

    private void printFirstTenRows() {
        String sql = "SELECT * FROM sucursale LIMIT 30";

        // Query to get the first 10 rows
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Print the rows in the console
        System.out.println("First 30 rows from the cantitati table:");
        for (Map<String, Object> row : rows) {
            System.out.println("1: " + row.get("numar_comanda") + ", 2: " + row.get("sucursala"));
        }
    }
}
