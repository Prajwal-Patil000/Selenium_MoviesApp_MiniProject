package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class MovieDetailsFunctionalitySteps {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on Login page For movie details")
    public void i_am_on_login_page_for_movie_details() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I enter valid username and password for movie details")
    public void i_enter_valid_username_and_password_for_movie_details() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("i click on login button for movie details")
    public void i_click_on_login_button_for_movie_details() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should see the movies details page for Movie Details")
    public void i_should_see_movie_details_page() {
        // Click the first movie on the home page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/movies/']"))).click();

        // Verify navigation
        wait.until(ExpectedConditions.urlContains("movies"));
        Assert.assertTrue(driver.getCurrentUrl().contains("movies"), "Not navigated to movie details page!");
    }

    @And("i should see all the UI elements are present and visible")
    public void i_should_see_all_ui_elements_visible() {
        // Validate key elements on the movie details page
        Assert.assertTrue(driver.findElement(By.className("movie-title")).isDisplayed(), "Movie title missing!");
        Assert.assertTrue(driver.findElement(By.className("movie-overview")).isDisplayed(), "Overview missing!");
        Assert.assertTrue(driver.findElement(By.className("similar-movies-container")).isDisplayed(), "Similar movies section missing!");
    }

    @And("I click on a movies in popular page for Movies Details")
    public void i_click_on_a_movies_in_popular_page_for_movies_details() {
        driver.findElement(By.linkText("Popular")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/movies/']"))).click();

        wait.until(ExpectedConditions.urlContains("movies"));
        Assert.assertTrue(driver.getCurrentUrl().contains("movies"), "Navigation from Popular page failed!");
    }

    @After
    public void tearDown() {
            driver.quit();

    }
}
