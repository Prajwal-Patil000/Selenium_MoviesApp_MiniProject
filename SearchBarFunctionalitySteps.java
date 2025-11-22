package Stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SearchBarFunctionalitySteps {

    WebDriver driver;
    WebDriverWait wait;

    // --- Hooks ---

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Increased wait time for stability
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://qamoviesapp.ccbp.tech/login");

        // Login is performed here
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInput"))).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();

        wait.until(ExpectedConditions.urlContains("https://qamoviesapp.ccbp.tech/"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- GIVEN ---

    @Given("I am logged in to the Movies App")
    public void i_am_logged_in_to_the_movies_app() {
        Assert.assertTrue(driver.getCurrentUrl().contains("https://qamoviesapp.ccbp.tech/"));
    }

    // --- WHEN ---

    @When("I open the search bar")
    public void i_open_the_search_bar() {
        // Clicks the search icon (search-empty-button) to reveal the input field
        wait.until(ExpectedConditions.elementToBeClickable(By.className("search-empty-button"))).click();
        // Wait for search input to be visible and ready
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
    }

    @When("I search for {string}")
    public void i_search_for_movie(String movieName) {
        // Clear previous search input before entering new text
        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.clear();
        searchInput.sendKeys(movieName);
        driver.findElement(By.className("search-button")).click();

        // Wait for results to load (either movies or the failure message)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.className("movie-image")),
                ExpectedConditions.visibilityOfElementLocated(By.className("not-found-search-image"))
        ));
    }

    @When("I click the first movie in the results")
    public void i_click_the_first_movie_in_the_results() {
        List<WebElement> movies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("movie-image")));
        Assert.assertTrue(movies.size() > 0, "No movies found to click on!");

        // Click the first movie
        wait.until(ExpectedConditions.elementToBeClickable(movies.get(0))).click();
    }

    @When("I navigate back to the search results page")
    public void i_navigate_back_to_the_search_results_page() {
        driver.navigate().back();
        // Wait until we are back on a URL containing 'search'
        wait.until(ExpectedConditions.urlContains("search"));
        // Ensure the search input is visible for the next search
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
    }

    // --- THEN ---

    @Then("I should see at least 1 movie displayed")
    public void i_should_see_at_least_one_movie_displayed() {
        List<WebElement> movies = driver.findElements(By.className("movie-image"));
        Assert.assertTrue(movies.size() > 0, "No movies found for valid search!");
        System.out.println("✅ Successfully found " + movies.size() + " movies.");
    }

    // UPDATED ANNOTATION TO RESOLVE AMBIGUITY
    @Then("I see the corresponding movie details page")
    public void i_see_the_corresponding_movie_details_page() {
        wait.until(ExpectedConditions.urlContains("/movies/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/movies/"), "Not redirected to Movie Details Page!");
        System.out.println("✅ Successfully navigated to Movie Details page.");
    }

    @Then("I should see a no results found message")
    public void i_should_see_a_no_results_found_message() {
        WebElement failureImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("not-found-search-image")));
        WebElement failureText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("not-found-search-paragraph")));

        Assert.assertTrue(failureImage.isDisplayed(), "Failure image is not displayed!");
        Assert.assertTrue(failureText.isDisplayed(), "Failure text is not displayed!");

        List<WebElement> movies = driver.findElements(By.className("movie-image"));
        Assert.assertEquals(movies.size(), 0, "Search results were incorrectly displayed for invalid query!");

        System.out.println("✅ Correctly displayed no results message for invalid query.");
    }
}

