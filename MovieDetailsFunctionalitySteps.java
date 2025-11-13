package Stepdefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;

// Selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TestNG imports
import org.testng.Assert;

// Java imports
import java.time.Duration;

public class MovieDetailsFunctionalitySteps {
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

    @Given("User clicks on a movie from Home page")
    public void user_clicks_on_a_movie_from_home_page() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-image"))).click();
    }

    @Then("Movie details page should display correct information")
    public void movie_details_page_should_display_correct_information() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-title")));
        Assert.assertTrue(title.isDisplayed(), "Movie title not displayed");
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
