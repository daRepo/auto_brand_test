/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.data_processing.strategies;

import com.auto.brand.model.Sucursale;
import com.auto.brand.service.SucursaleService;
import com.auto.brand.service.data_processing.CsvProcessingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Service
public class SucursaleProcessingStrategy implements CsvProcessingStrategy {

    @Autowired
    private SucursaleService sucursaleService;

    @Override
    public void processFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return;
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Sucursale> csvToBean = new CsvToBeanBuilder<Sucursale>(reader)
                    .withType(Sucursale.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Sucursale> cant = csvToBean.parse();

            // Save all records to the database
            sucursaleService.newData(cant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
