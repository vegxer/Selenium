package ru.vyatsu.selenium;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CDS {
    private static final String URL = "https://m.cdsvyatka.com/";

    public CDS() {
        System.setProperty("webdriver.chrome.driver", getClass()
                .getClassLoader()
                .getResource("chromedriver.exe")
                .getPath());
    }

    public List<String> getStopsList(int routeNumber) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(URL);

            //выбор маршрута
            driver.findElements(
                            By.xpath(String.format("//option[@value='%d']", routeNumber)))
                    .get(1)
                    .click();

            //перейти на страницу маршрута
            driver.findElement(
                            By.xpath("//input[@onclick='marsh_stops.php']"))
                    .click();

            //получение списка остановок
            List<String> stops = Lists.transform(
                    driver.findElements(
                            By.xpath("//a")),
                    WebElement::getText);
            stops.remove(stops.size() - 1);

            return stops.stream().toList();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
