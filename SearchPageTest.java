
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchPageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open application
        driver.get("https://qamoviesapp.ccbp.tech/");

        // Perform login
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();

        // Wait for home page
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
    }

    @Test(priority = 1)
    public void searchAvatarMovie() {
        // Click on Search link
        driver.findElement(By.linkText("Search")).click();

        // Wait for search page
        wait.until(ExpectedConditions.urlContains("search"));

        // Locate search input
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("searchInput"))
        );

        // Type and search for "Avatar"
        searchInput.sendKeys("Avatar");
        searchInput.sendKeys(Keys.ENTER);

        // Wait for results to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-item")));

        // Validate results are visible
        boolean resultsDisplayed = driver.findElement(By.className("movie-item")).isDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results not displayed for Avatar");

        System.out.println("✅ Avatar search test passed!");
    }

    @Test(priority = 2)
    public void searchVenomMovie() {
        // Click on Search link
        driver.findElement(By.linkText("Search")).click();

        // Wait for search page
        wait.until(ExpectedConditions.urlContains("search"));

        // Locate search input
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("searchInput"))
        );

        // Type and search for "Venom"
        searchInput.sendKeys("Venom");
        searchInput.sendKeys(Keys.ENTER);

        // Wait for results
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-item")));

        // Verify search results displayed
        boolean resultsDisplayed = driver.findElement(By.className("movie-item")).isDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results not displayed for Venom");

        System.out.println("✅ Venom search test passed!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
