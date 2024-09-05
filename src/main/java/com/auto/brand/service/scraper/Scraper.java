package com.auto.brand.service.scraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to automate login and data extraction using HtmlUnit and HttpClient.
 *
 * @author Daniel
 */
public class Scraper {
    
    private Gson g = new Gson();

    public String main() {
        try (final WebClient webClient = new WebClient()) {

            webClient.getOptions().setThrowExceptionOnScriptError(false);

            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
//            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

            HtmlPage loginPage = webClient.getPage("https://online.autobrand.ro/");
            HtmlTextInput usernameInput = loginPage.getFirstByXPath("//input[@name='NAME']");
            HtmlPasswordInput passwordInput = loginPage.getFirstByXPath("//input[@name='WEBPASSC']");
            HtmlElement loginButton = loginPage.getFirstByXPath("//input[@type='submit' and @value='Login']");

            usernameInput.setValueAttribute("0009999!2");
            passwordInput.setValueAttribute("12345678");
            HtmlPage homePage = loginButton.click();
            System.out.println("Logged in successfully!");

            homePage.getElementById("searchinput").setAttribute("value", "F 026 400 068");
            HtmlPage lastPage = homePage.getElementById("searchbutton").click();

            webClient.waitForBackgroundJavaScript(1000);
            DomElement tableContainer = lastPage.getElementById("itemlist");
            HtmlElement tbody = tableContainer.getElementsByTagName("tbody").getFirst();
            DomNodeList<HtmlElement> rows = tbody.getElementsByTagName("tr");

            List<RowDto> parsedRows = new ArrayList();
            for (HtmlElement row : rows) {
                DomNodeList<HtmlElement> fields = row.getElementsByTagName("td");

                RowDto parsed = new RowDto();
                parsed.codArticol = fields.get(0).asNormalizedText();
                parsed.articol = fields.get(1).asNormalizedText();
                parsed.pret = fields.get(2).asNormalizedText().split("\n")[0];
                parsed.unitate = fields.get(3).asNormalizedText();
                parsed.producator = fields.get(4).asNormalizedText();
                parsed.stoc = fields.get(5).getElementsByTagName("img").get(0).getAttribute("title");
                parsed.id = fields.get(6).asNormalizedText();
                parsed.prentruArticol = fields.get(7).asNormalizedText();

                parsedRows.add(parsed);
            }

            return g.toJson(parsedRows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
    
        System.out.println(new Scraper().main());
    }
}
