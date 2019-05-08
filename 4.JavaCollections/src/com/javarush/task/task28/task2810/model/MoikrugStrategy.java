package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    //private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT= "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    public String getURL_FORMAT() {
        return URL_FORMAT;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
            List<Vacancy> Vacancies = new ArrayList<>();
            int pageNum = 0;
            Document document = null;
            while(true)
            {
                try {
                    document = getDocument(searchString, pageNum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements vacancies = document.getElementsByClass("job");
                if (vacancies.size()==0) break;
                for (Element element: vacancies)
                {
                    if (element != null)
                    {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                        vac.setSiteName(URL_FORMAT);
                        vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                        String salary = element.getElementsByAttributeValue("class", "salary").text();
                        String city = element.getElementsByAttributeValue("class", "location").text();
                        vac.setSalary(salary.length()==0 ? "" : salary);
                        vac.setCity(city.length()==0 ? "" : city);
                        Vacancies.add(vac);
                    }
                }
                pageNum++;
            }
            return Vacancies;
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