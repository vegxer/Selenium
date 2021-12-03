import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    private WebDriver driver;
    private final String URL = "https://irr.ru/";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", getClass()
                .getResource("chromedriver.exe")
                .getPath());
        driver = new ChromeDriver();
    }

    @Test
    public void titleTest() {
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Из рук в руки - доска частных бесплатных объявлений в Москве");
    }

    @Test
    public void InputNameOfProductTest() throws InterruptedException {
        driver.get(URL);
        // ищем поле вода
        var nameOfProduct = driver
                .findElement(By.xpath("//input[@data-qa='search__input']"));
        // проверяем, найден ли элемент
        Assert.assertNotNull(nameOfProduct);
        // записываем значение в поле ввода
        nameOfProduct.sendKeys("мебель");
        // ставим паузу
        Thread.sleep(1000);
    }

    @Test
    public void ChooseCategoryTest() throws InterruptedException {
        driver.get(URL);
        var selectize = driver
                .findElement(
                        By.xpath("//a[@class='btnRegion js-selectRegionButton']"));
        Assert.assertNotNull(selectize);
        selectize.click();

        selectize.findElement(
                By.xpath("//input[@class='_3DjOn8-Hq1HYuSKY1RkHg-']"))
                .sendKeys("Киров");

        Thread.sleep(1000);
        var dataSelectables = selectize
                .findElements(By.xpath("//div[@class='_1qm1t2nc_Nj-A43kFe_bu9']"));
        Assert.assertNotNull(dataSelectables.get(0));
        dataSelectables.get(0).click();

        selectize.findElement(
                By.xpath("//button[@class='_3eOiTlyu2czPY7c6cIFsJS _2vM714Nq1v4c9xK4NBAXmB HIo8Zsdbkzj4aiy-u_4Dv']"))
                .click();
        Thread.sleep(2000);

        // ищем поле вода
        var nameOfProduct = driver
                .findElement(By.xpath("//input[@data-qa='search__input']"));
        // проверяем, найден ли элемент
        Assert.assertNotNull(nameOfProduct);
        // записываем значение в поле ввода
        nameOfProduct.sendKeys("мебель");

        driver.findElement(
                By.xpath("//button[@class='headerSearch__submitButton headerSearch__submitButton_mainPage']"))
                .click();
        // ставим паузу
        Thread.sleep(5000);
    }


    @After
    public void quit() {
        driver.quit();
    }

}
