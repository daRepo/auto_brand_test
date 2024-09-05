/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auto.brand.service.pdf.steps;

import com.auto.brand.service.pdf.dto.Invoice;
import com.auto.brand.service.pdf.dto.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniel
 */
public class DataProcessor {

    public Invoice run(List<String> rows) {

        List<String> rawFiltered = filterRawArticles(rows);
        List<Item> items = processRawArticles(rawFiltered);
        String nrFactura = extractInvoiceNumber(rows);

        Invoice invoice = new Invoice();
        invoice.numarFactura = nrFactura;
        invoice.items = items;
        
        return invoice;
    }

    private List<String> filterRawArticles(List<String> rows) {

        List<String> filtered = new ArrayList();

        for (String row : rows) {

            if (!row.contains("provenienta articolului facturata TVA\n")) {
                continue;
            }

            String good = row.split("provenienta articolului facturata TVA\n")[1];

            if (good.contains("Reducere la pretul articolului pentru linia")) {
                good = good.split("\nReducere la pretul articolului pentru linia")[0];
            } else {
                good = good.split("\nPagina \\d+ din \\d+")[0];
            }

            filtered.add(good);
        }

        return filtered;
    }

    private List<Item> processRawArticles(List<String> rows) {

        List<Item> returnable = new ArrayList();

        for (String row : rows) {

            String[] fragment = row.split("\n");

            for (int i = 0; i < fragment.length; i++) {

                returnable.add(parse(fragment[i], fragment[++i]));
            }
        }
        return returnable;
    }

    private Item parse(String data, String name) {

        String[] nameArr = name.split(" ", 2);
        String[] dataArr = data.split(" ");

        Item returnable = new Item();

        returnable.line = nameArr[0];
        returnable.name = nameArr[1];

        returnable.pret = dataArr[0];
        returnable.moneda = dataArr[1];
        returnable.cantitate = dataArr[2];
        returnable.um = dataArr[3];
        returnable.tva = dataArr[4];
        returnable.valoare = dataArr[5];
        return returnable;
    }

    private String extractInvoiceNumber(List<String> rows) {

        Pattern pattern = Pattern.compile("Nr\\. factura (\\d+)");
        Matcher matcher = pattern.matcher(rows.get(0));

        if (matcher.find()) {
            String nrFactura = matcher.group(1);
            return nrFactura;
        }

        return null;
    }
}
