import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {
    public String baseUrl = "https://dailytodo.org/";
    public WebDriver driver;

    @Test
    public void testCreateTask() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Task");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();
        assertEquals(driver.findElement(By.id("t1")).getText(), "Task");
    }


    @Test
    public void testCancelSubmit() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Task");

        driver.findElement(By.cssSelector("body > div > form > p > a")).click();

        assertTrue(driver.findElement(By.id("start")).isDisplayed());

    }



    @Test
    public void testCreateTaskAndOverview() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Operi veš\nSkuhaj ručak\nZamijeni gume");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();

        assertEquals(driver.findElement(By.id("t1")).getText(), "Operi veš");

        driver.findElement(By.cssSelector("#tasktable > tbody > tr.t1.tasktr > td.trend.more > a")).click();
        assertTrue(driver.findElement(By.id("tasktable")).isDisplayed());

        assertEquals(driver.findElement(By.id("headline")).getText(), "Operi veš");

    }

    @Test
    public void markTodaysTask() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Operi veš\nSkuhaj ručak\nZamijeni gume");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();

        assertEquals(driver.findElement(By.id("t1")).getText(), "Operi veš");

        driver.findElement(By.id("check1")).click();

        Thread.sleep(5000);

        boolean isDisplayed = driver.findElement(By.id("check1")).getCssValue("display").equals("none");
        boolean checked = driver.findElement(By.id("today1")).getCssValue("display").equals("inline");
        assertTrue(isDisplayed);
        assertTrue(checked);

        driver.findElement(By.id("today1")).click();

        Thread.sleep(5000);

        isDisplayed = driver.findElement(By.id("check1")).getCssValue("display").equals("inline-block");
        checked = driver.findElement(By.id("today1")).getCssValue("display").equals("none");
        assertTrue(isDisplayed);
        assertTrue(checked);

    }

    @Test
    public void editTask() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Operi veš\nSkuhaj ručak\nZamijeni gume");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();

        assertEquals(driver.findElement(By.id("t1")).getText(), "Operi veš");


        driver.findElement(By.cssSelector("#headline > a")).click();
        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Okopaj vrt");

        driver.findElement(By.cssSelector("body > div > form > p > input")).click();

        assertEquals(driver.findElement(By.id("t4")).getText(), "Okopaj vrt");
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
