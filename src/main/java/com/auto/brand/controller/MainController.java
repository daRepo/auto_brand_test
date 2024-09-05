/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.controller;

import com.auto.brand.model.Comenzi;
import com.auto.brand.service.ComenziService;
import com.auto.brand.service.data_processing.CsvProcessingContext;
import com.auto.brand.service.pdf.InvoiceExtract;
import com.auto.brand.service.pdf.dto.Invoice;
import com.auto.brand.service.pdf.dto.Item;
import com.auto.brand.service.scraper.Scraper;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Controller
public class MainController {

    @Autowired
    private CsvProcessingContext context;
    
    @Autowired
    private ComenziService comenziService;

    @GetMapping("/init")
    public String init() {
        return "forward:/init.html";
    }

    @PostMapping("/init")
    public String initUpload(@RequestParam("cantitati") MultipartFile cantitatiFile,
            @RequestParam("comenzi") MultipartFile comenziFile,
            @RequestParam("data") MultipartFile dataFile,
            @RequestParam("sucursale") MultipartFile sucursaleFile) {

        System.out.println("jej");

        try {
            context.processFile("cantitati", cantitatiFile);
            context.processFile("comenzi", comenziFile);
            context.processFile("data", dataFile);
            context.processFile("sucursale", sucursaleFile);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/init";
    }

    @GetMapping("/ex1")
    public String homeQuery() {

        return "forward:/query_test.html";
    }

    @PostMapping("/ex1")
    public String homeQueryPost(@RequestParam("query") String query) {

        System.out.println(query);
        return "redirect:/ex1";
    }

    @GetMapping("/scraper")
    public String scraper() {

        return "forward:/scraper.html";
    }

    @GetMapping(value = "/scraper/data", produces = "application/json")
    @ResponseBody
    public String data() {

        Scraper scrp = new Scraper();
        return scrp.main();
    }

    @GetMapping("/pdf")
    public String pdfProcess() {

        return "forward:/pdf.html";
    }

    @PostMapping(value = "/scraper/data")
    public void pdfToCsvParse(HttpServletResponse response, @RequestParam("file") MultipartFile file) {

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"articles.csv\"");

        InputStream pdfInputStream = null;
        try {
            pdfInputStream = file.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        InvoiceExtract ie = new InvoiceExtract();
        Invoice invoice = ie.process(pdfInputStream);

        try {
            StatefulBeanToCsv<Item> writer = new StatefulBeanToCsvBuilder<Item>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER) // Do not quote fields
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            writer.write(invoice.items);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostMapping(value = "/scraper/sql")
    public void sqlSearch(HttpServletResponse response, @RequestParam("file") MultipartFile file) {

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"articles.csv\"");

        InputStream pdfInputStream = null;
        try {
            pdfInputStream = file.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        InvoiceExtract ie = new InvoiceExtract();
        Invoice invoice = ie.process(pdfInputStream);
        String invoiceNumber = invoice.numarFactura;
        
        List<Comenzi> comenzi = comenziService.findByNumarFactura(invoiceNumber);

        try {
            StatefulBeanToCsv<Comenzi> writer = new StatefulBeanToCsvBuilder<Comenzi>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER) // Do not quote fields
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            writer.write(comenzi);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
