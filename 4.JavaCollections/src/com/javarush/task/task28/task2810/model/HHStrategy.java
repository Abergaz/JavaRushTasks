package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    public String getURL_FORMAT() {
        return URL_FORMAT;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> listVacancy = new ArrayList<Vacancy>();
        Document document = null;
        for (int pageNumber=0; ;pageNumber++){
            try {
                document = getDocument(searchString, pageNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (elements.size()==0) break;
            for (Element element : elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(element.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-title").text().trim());
                vacancy.setCity(element.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-address").text().trim());
                vacancy.setCompanyName(element.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-employer").text().trim());
                vacancy.setUrl(element.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-title").attr("href").trim());
                vacancy.setSalary(element.getElementsByAttributeValueContaining("data-qa", "vacancy-serp__vacancy-compensation").text().trim());
                vacancy.setSiteName(URL_FORMAT);
                listVacancy.add(vacancy);
            }
        }
        return listVacancy;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = null;
        String html="";
        try {
            html = String.format(URL_FORMAT, searchString, page);
            //System.out.println(html);
            setProxy(); //т.к. через прохи, нужно отдельно устанавливать параметры прокси
            document = Jsoup.connect(html).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36").referrer("http://google.ru").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }


    static void setProxy() {
        System.setProperty("http.proxyHost", "192.168.1.120");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "192.168.1.120");
        System.setProperty("https.proxyPort", "8080");
        /*
        System.setProperty("http.proxyUser", "*****");
        System.setProperty("http.proxyPassword", "*****");
        System.setProperty("https.proxyUser", "*****");
        System.setProperty("https.proxyPassword", "*****");
        */
    }
}
