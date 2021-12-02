import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StackOverflowLogin {
        private static WebDriver driver;
        private static final String URL = "https://stackoverflow.com/";

        @Before
        public void setup() {
            System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").getPath());
            driver = new ChromeDriver();
        }

        @Test
        public void stackOverflowLogin() throws InterruptedException, IOException, URISyntaxException {
            driver.get(URL);

            //кнопка log in
            driver.findElement(
                            By.xpath("//a[@class='login-link s-btn s-btn__filled py8 js-gps-track']"))
                    .click();

            //email
            driver.findElement(
                            By.xpath("//input[@class='s-input']"))
                    .sendKeys("vegxer@mail.ru");

            //пароль
            driver.findElement(
                            By.xpath("//input[@class='flex--item s-input']"))
                    .sendKeys(Files.readString(Paths.get(getClass().getResource("password.txt").toURI())));

            //кнопка log in
            driver.findElement(
                            By.xpath("//button[@class='flex--item s-btn s-btn__primary']"))
                    .click();

            Thread.sleep(10000);
        }

        @After
        public void quit() {
            driver.quit();
        }
}
