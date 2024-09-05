/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.pdf.steps;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class TextExtractor {

    public List<String> run(InputStream pdfStream) {

        List<String> returnable = new ArrayList();

        try {
            PdfReader reader = new PdfReader(pdfStream);
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i + 1);

                returnable.add(pageContent);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnable;
    }
}
