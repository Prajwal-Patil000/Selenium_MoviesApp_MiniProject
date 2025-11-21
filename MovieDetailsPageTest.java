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
import pages.LoginPage;
import pages.MovieDetailsPage;

import java.time.Duration;
import java.util.List;

public class MovieDetailsPageTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    MovieDetailsPage movieDetailsPage;

    @BeforeMethod
    public void setUp() {
        // Ensure this path matches your local chromedriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://qamoviesapp.ccbp.tech/");
        loginPage = new LoginPage(driver);
        movieDetailsPage = new MovieDetailsPage(driver);

        // Login
        loginPage.loginToApplication("rahul", "rahul@2021");
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @Test(priority = 1)
    public void verifyMovieDetailsFromHomePage() {
        // 1. Wait for presence of movie elements on Home Page
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("poster"))
        );

        // 2. Get list of movies (using a generic locator usually valid for this app)
        // Adjust locator if necessary: often .poster or .react-slick-item img
        List<WebElement> movies = driver.findElements(By.className("poster"));

        // Fallback locator if class "poster" is not used in your specific version
        if(movies.isEmpty()) {
            movies = driver.findElements(By.xpath("//div[contains(@class, 'react-slick-item')]//img"));
        }

        Assert.assertTrue(movies.size() > 0, "❌ No movie posters found on Home Page!");

        // 3. Click the first movie
        wait.until(ExpectedConditions.elementToBeClickable(movies.get(0))).click();

        // 4. Wait for URL transition
        wait.until(ExpectedConditions.urlContains("/movies/"));

        // 5. Run Assertions on Details Page
        Assert.assertTrue(movieDetailsPage.isBackgroundPosterDisplayed(), "❌ Background image not displayed!");
        Assert.assertTrue(movieDetailsPage.isTitleDisplayed(), "❌ Movie title not displayed!");
        Assert.assertTrue(movieDetailsPage.isDescriptionDisplayed(), "❌ Movie description not displayed!");
        Assert.assertTrue(movieDetailsPage.areSimilarMoviesDisplayed(), "❌ Similar movies not visible!");

        Assert.assertTrue(movieDetailsPage.isMovieReviewContainerDisplayed(), "❌ Movie Review Container not displayed!");
        Assert.assertTrue(movieDetailsPage.isDetailedMovieCategoriesDisplayed(), "❌ Detailed Categories Container not displayed!");
    }

    @Test(priority = 2)
    public void verifyMovieDetailsFromPopularPage() {
        // 1. Navigate to Popular Page
        WebElement popularLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular")));
        popularLink.click();

        wait.until(ExpectedConditions.urlContains("popular"));

        // 2. Wait for presence of elements
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.movie-icon-item img"))
        );

        // 3. Get the list of popular movies
        List<WebElement> popularMovies = driver.findElements(By.cssSelector("li.movie-icon-item img"));

        Assert.assertTrue(popularMovies.size() > 0, "❌ No popular movies found!");

        // 4. Click the first movie
        wait.until(ExpectedConditions.elementToBeClickable(popularMovies.get(0))).click();

        wait.until(ExpectedConditions.urlContains("/movies/"));

        // 5. Run Assertions
        Assert.assertTrue(movieDetailsPage.isBackgroundPosterDisplayed(), "❌ Background image not displayed!");
        Assert.assertTrue(movieDetailsPage.isTitleDisplayed(), "❌ Movie title not displayed!");
        Assert.assertTrue(movieDetailsPage.isDescriptionDisplayed(), "❌ Movie description not displayed!");
        Assert.assertTrue(movieDetailsPage.areSimilarMoviesDisplayed(), "❌ Similar movies not visible!");

        Assert.assertTrue(movieDetailsPage.isMovieReviewContainerDisplayed(), "❌ Movie Review Container not displayed!");
        Assert.assertTrue(movieDetailsPage.isDetailedMovieCategoriesDisplayed(), "❌ Detailed Categories Container not displayed!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
