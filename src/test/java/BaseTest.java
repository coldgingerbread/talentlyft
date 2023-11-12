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
    public void testCreateTask() {
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


    @Test
    public void testCancelSubmit() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "create");

        driver.findElement(By.cssSelector("body > div > form > textarea"))
                .sendKeys("Task");

        driver.findElement(By.cssSelector("body > div > form > p > a")).click();

        boolean formExists = driver.findElement(By.id("start")).isDisplayed();

       if(!formExists)
           throw new NoSuchElementException();
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

        String taskValue = driver.findElement(By.id("t1")).getText();

        if(!taskValue.equals("Operi veš"))
            throw new NoSuchElementException();

        driver.findElement(By.cssSelector("#tasktable > tbody > tr.t1.tasktr > td.trend.more > a")).click();
        boolean t = driver.findElement(By.id("tasktable")).isDisplayed();

        if(!t)
            throw new NoSuchElementException();

        String headline = driver.findElement(By.id("headline")).getText();
        if(!headline.equals("Operi veš"))
            throw new NoSuchElementException();

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

        String taskValue = driver.findElement(By.id("t1")).getText();

        if(!taskValue.equals("Operi veš"))
            throw new NoSuchElementException();

        driver.findElement(By.id("check1")).click();

        Thread.sleep(5000);

        boolean isDisplayed = driver.findElement(By.id("check1")).getCssValue("display").equals("none");
        boolean checked = driver.findElement(By.id("today1")).getCssValue("display").equals("inline");
        if(!isDisplayed || !checked)
            throw new NoSuchElementException();

        driver.findElement(By.id("today1")).click();

        Thread.sleep(5000);

        isDisplayed = driver.findElement(By.id("check1")).getCssValue("display").equals("inline-block");
        checked = driver.findElement(By.id("today1")).getCssValue("display").equals("none");
        if(!isDisplayed || !checked)
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
