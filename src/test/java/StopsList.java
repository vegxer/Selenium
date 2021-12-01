import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StopsList {
    private static WebDriver driver;
    private static final String URL = "https://m.cdsvyatka.com/";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").getPath());
        driver = new ChromeDriver();
    }

    @Test
    public void route47Test() throws IOException {
        driver.get(URL);

        //выбор 47 маршрута
        driver.findElements(
                By.xpath("//option[@value='1047']"))
                .get(1)
                .click();

        //перейти на страницу 47 маршрута
        driver.findElement(
                By.xpath("//input[@onclick='marsh_stops.php']"))
                .click();

        //получение списка остановок
        List<String> stops = Lists.transform(
                driver.findElements(
                By.xpath("//a")),
                WebElement::getText);
        stops.remove(stops.size() - 1);

        try (InputStream is = getClass().getResourceAsStream("stops.txt")) {
            Assert.assertArrayEquals(String.join("\r\n", stops)
                    .getBytes(),
                    is.readAllBytes());
        }
    }

    @After
    public void quit() {
        driver.quit();
    }

}
