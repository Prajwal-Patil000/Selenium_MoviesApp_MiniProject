package Stepdefinitions;


// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;

// Selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TestNG imports
import org.testng.Assert;

// Java imports
import java.time.Duration;


public class SearchBarFunctionalitySteps {
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

    @Given("User is on Home page with search bar visible")
    public void user_is_on_home_page_with_search_bar_visible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-icon")));
    }

    @When("User searches for a movie named {string}")
    public void user_searches_for_a_movie_named(String movieName) {
        driver.findElement(By.className("search-icon")).click();
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        searchInput.sendKeys(movieName);
        searchInput.sendKeys(Keys.ENTER);
    }

    @Then("Search results should display movies related to {string}")
    public void search_results_should_display_movies_related_to(String movieName) {
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-image")));
        Assert.assertTrue(result.isDisplayed(), "No movies found for " + movieName);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
