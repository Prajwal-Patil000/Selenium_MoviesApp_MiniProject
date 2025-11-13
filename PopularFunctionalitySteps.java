package Stepdefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;

// Selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TestNG imports
import org.testng.Assert;

// Java imports
import java.time.Duration;

public class PopularFunctionalitySteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qamoviesapp.ccbp.tech/");
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();
    }

    @Given("User is on the Popular movies page")
    public void user_is_on_the_popular_movies_page() {
        driver.findElement(By.linkText("Popular")).click();
        wait.until(ExpectedConditions.urlContains("/popular"));
    }

    @Then("All popular movies should be displayed")
    public void all_popular_movies_should_be_displayed() {
        Assert.assertTrue(driver.findElements(By.className("movie-image")).size() > 0, "No popular movies displayed");
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
