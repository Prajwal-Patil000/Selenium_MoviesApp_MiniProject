package Stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import pages.MovieDetailsPage;

import java.time.Duration;
import java.util.List;

public class MovieDetailsFunctionalitySteps {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    MovieDetailsPage movieDetailsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginPage = new LoginPage(driver);
        movieDetailsPage = new MovieDetailsPage(driver);
    }

    @Given("^I am logged into the QA Movies Application$")
    public void iAmLoggedIntoTheApplication() {
        driver.get("https://qamoviesapp.ccbp.tech/");
        loginPage.loginToApplication("rahul", "rahul@2021");
        wait.until(ExpectedConditions.urlContains("/"));
    }

    // ✅ This explicitly captures text inside quotes
    @When("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToTheSectionPage(String section) {
        if (section.equalsIgnoreCase("Home")) {
            wait.until(ExpectedConditions.urlMatches("https://qamoviesapp.ccbp.tech/?"));
        } else if (section.equalsIgnoreCase("Popular")) {
            WebElement popularLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular")));
            popularLink.click();
            wait.until(ExpectedConditions.urlContains("popular"));
        }
    }

    // ✅ This explicitly captures text inside quotes
    @And("^I click on a movie in the \"([^\"]*)\" section$")
    public void iClickOnAMovieInTheSection(String section) {
        if (section.equalsIgnoreCase("Home")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'react-slick-item')]//img")));
            List<WebElement> movies = driver.findElements(By.xpath("//div[contains(@class, 'react-slick-item')]//img"));
            Assert.assertTrue(movies.size() > 0, "No movies found on Home Page");
            wait.until(ExpectedConditions.elementToBeClickable(movies.get(0))).click();
        } else if (section.equalsIgnoreCase("Popular")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.movie-icon-item img")));
            List<WebElement> popularMovies = driver.findElements(By.cssSelector("li.movie-icon-item img"));
            Assert.assertTrue(popularMovies.size() > 0, "No movies found on Popular Page");
            wait.until(ExpectedConditions.elementToBeClickable(popularMovies.get(0))).click();
        }
    }

    @Then("^I should be redirected to the movie details page$")
    public void iShouldBeRedirectedToTheMovieDetailsPage() {
        wait.until(ExpectedConditions.urlContains("/movies/"));
    }

    @And("^I verify that the Background Image is displayed$")
    public void iVerifyThatTheBackgroundImageIsDisplayed() {
        Assert.assertTrue(movieDetailsPage.isBackgroundPosterDisplayed(), "❌ Background image is not displayed");
    }

    @And("^I verify that the Movie Title and Description are displayed$")
    public void iVerifyThatTheMovieTitleAndDescriptionAreDisplayed() {
        Assert.assertTrue(movieDetailsPage.isTitleDisplayed(), "❌ Movie Title is not displayed");
        Assert.assertTrue(movieDetailsPage.isDescriptionDisplayed(), "❌ Movie Description is not displayed");
    }

    @And("^I verify that the Movie Review Container is displayed$")
    public void iVerifyThatTheMovieReviewContainerIsDisplayed() {
        Assert.assertTrue(movieDetailsPage.isMovieReviewContainerDisplayed(), "❌ Movie Review Container is not displayed");
    }

    @And("^I verify that the Detailed Categories are displayed$")
    public void iVerifyThatTheDetailedCategoriesAreDisplayed() {
        Assert.assertTrue(movieDetailsPage.isDetailedMovieCategoriesDisplayed(), "❌ Detailed Categories Container is not displayed");
    }

    @And("^I verify that Similar Movies are displayed$")
    public void iVerifyThatSimilarMoviesAreDisplayed() {
        Assert.assertTrue(movieDetailsPage.areSimilarMoviesDisplayed(), "❌ Similar Movies are not displayed");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
