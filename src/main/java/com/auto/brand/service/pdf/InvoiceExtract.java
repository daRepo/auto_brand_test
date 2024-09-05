/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.pdf;

import com.auto.brand.service.pdf.dto.Invoice;
import com.auto.brand.service.pdf.steps.DataProcessor;
import com.auto.brand.service.pdf.steps.TextExtractor;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class InvoiceExtract {

//    private final Gson g = new Gson();
    
    public Invoice process(InputStream input) {

        TextExtractor te = new TextExtractor();
        List<String> rows = te.run(input);

        DataProcessor dp = new DataProcessor();
        Invoice invoice = dp.run(rows);
        
//        return g.toJson(invoice);
        return invoice;
    }
}
