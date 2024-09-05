/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.data_processing;

import com.auto.brand.service.data_processing.strategies.CantitatiProcessingStrategy;
import com.auto.brand.service.data_processing.strategies.ComenziProcessingStrategy;
import com.auto.brand.service.data_processing.strategies.DataProcessingStrategy;
import com.auto.brand.service.data_processing.strategies.SucursaleProcessingStrategy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Component
public class CsvProcessingContext {

    private final Map<String, CsvProcessingStrategy> strategyMap;

    public CsvProcessingContext(CantitatiProcessingStrategy cantitatiProcessingStrategy,
                                ComenziProcessingStrategy comenziProcessingStrategy,
                                DataProcessingStrategy dataProcessingStrategy,
                                SucursaleProcessingStrategy sucursaleProcessingStrategy) {
        strategyMap = new HashMap<>();
        strategyMap.put("cantitati", cantitatiProcessingStrategy);
        strategyMap.put("data", dataProcessingStrategy);
        strategyMap.put("sucursale", sucursaleProcessingStrategy);
        strategyMap.put("comenzi", comenziProcessingStrategy);
    }

    public void processFile(String type, MultipartFile file) throws IOException {
        CsvProcessingStrategy strategy = strategyMap.get(type.toLowerCase());
        if (strategy != null) {
            strategy.processFile(file);
        } else {
            throw new IllegalArgumentException("No strategy found for type: " + type);
        }
    }
}
