import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PopularPage;

import java.time.Duration;
import java.util.List;

public class PopularPageTest {

    public WebDriver driver;
    public PopularPage popularPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qamoviesapp.ccbp.tech/");
        popularPage = new PopularPage(driver);
    }

    @Test
    public void testPopularPageUI() {
        // Log in
        popularPage.LoginToApplication("rahul", "rahul@2021");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Click on "Popular" link in the header
        WebElement popularBtn = driver.findElement(By.xpath("//a[contains(@href,'/popular')]"));
        popularBtn.click();

        // Wait until navigation completes
        String expectedUrl = "https://qamoviesapp.ccbp.tech/popular";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, " Navigation to Popular Page failed!");


        List<WebElement> movies = driver.findElements(By.className("movie-image-container"));
        Assert.assertTrue(movies.size() > 0, " No movies are displayed on the Popular Page!");

        System.out.println("The Popular Movies Page is displayed successfully with " + movies.size() + " movies.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
