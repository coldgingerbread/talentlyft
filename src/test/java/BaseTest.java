import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public String baseUrl = "https://www.javatpoint.com/";
    public WebDriver driver;
    @Test
    public void test() {
// Create driver object for CHROME browser
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
// get the current URL of the page
        String URL= driver.getCurrentUrl();
        System.out.println(URL);

//get the title of the page
        String title = driver.getTitle();
        System.out.println(title);
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
