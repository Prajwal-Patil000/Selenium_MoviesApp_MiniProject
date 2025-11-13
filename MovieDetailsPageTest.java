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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://qamoviesapp.ccbp.tech/");
        loginPage = new LoginPage(driver);
        movieDetailsPage = new MovieDetailsPage(driver);

        // ✅ Login
        loginPage.loginToApplication("rahul", "rahul@2021");
        wait.until(ExpectedConditions.urlContains("/"));
    }

    // ✅ Test Home Page movie details
    @Test(priority = 1)
    public void verifyMovieDetailsFromHomePage() {
        List<WebElement> movies = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class*='movie'] img"))
        );

        Assert.assertTrue(movies.size() > 0, "❌ No movie posters found on Home Page!");
        movies.get(0).click();

        wait.until(ExpectedConditions.urlContains("/movies/"));

        Assert.assertTrue(movieDetailsPage.isBackgroundPosterDisplayed(), "❌ Background image not displayed!");
        Assert.assertTrue(movieDetailsPage.isTitleDisplayed(), "❌ Movie title not displayed!");
        Assert.assertTrue(movieDetailsPage.isDescriptionDisplayed(), "❌ Movie description not displayed!");
        Assert.assertTrue(movieDetailsPage.areSimilarMoviesDisplayed(), "❌ Similar movies not visible!");
    }

    // ✅ Test Popular Page movie details
    @Test(priority = 2)
    public void verifyMovieDetailsFromPopularPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular"))).click();

        List<WebElement> popularMovies = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class*='movie'] img"))
        );

        Assert.assertTrue(popularMovies.size() > 0, "❌ No popular movies found!");
        popularMovies.get(0).click();

        wait.until(ExpectedConditions.urlContains("/movies/"));

        Assert.assertTrue(movieDetailsPage.isBackgroundPosterDisplayed(), "❌ Background image not displayed!");
        Assert.assertTrue(movieDetailsPage.isTitleDisplayed(), "❌ Movie title not displayed!");
        Assert.assertTrue(movieDetailsPage.isDescriptionDisplayed(), "❌ Movie description not displayed!");
        Assert.assertTrue(movieDetailsPage.areSimilarMoviesDisplayed(), "❌ Similar movies not visible!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
