package com.auto.brand.service.data_processing.strategies;

import com.auto.brand.model.Data;
import com.auto.brand.service.DataService;
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

@Service
public class DataProcessingStrategy implements CsvProcessingStrategy {

    @Autowired
    private DataService dataService;

    @Override
    public void processFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return;
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Data> csvToBean = new CsvToBeanBuilder<Data>(reader)
                    .withType(Data.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Data> cant = csvToBean.parse();

            // Save all records to the database
            dataService.newData(cant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
