import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public String baseUrl = "https://dailytodo.org/";
    public WebDriver driver;
    @Test
    public void test() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Task");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();
    }

    @Test
    public void test2() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Task");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();

        String taskValue = driver.findElement(By.id("t1")).getText();

        if(!taskValue.equals("Task"))
            throw new NoSuchElementException();
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
        System.out.println("after test");
    }
}
