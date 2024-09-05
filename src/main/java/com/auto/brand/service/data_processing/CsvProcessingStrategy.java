/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.data_processing;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
public interface CsvProcessingStrategy {
    void processFile(MultipartFile file) throws IOException;
}
