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
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qamoviesapp.ccbp.tech/");
        popularPage = new PopularPage(driver);
        popularPage.LoginToApplication("rahul", "rahul@2021");
    }

    @Test(priority = 1)
    public void testPopularPageUI() {
        // Click on "Popular" link in the header
        WebElement popularBtn = driver.findElement(By.xpath("//a[contains(@href,'/popular')]"));
        popularBtn.click();

        // Wait until navigation completes
        String expectedUrl = "https://qamoviesapp.ccbp.tech/popular";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Navigation to Popular Page failed!");

        // Use correct class name to get movie list
        List<WebElement> movies = driver.findElements(By.className("movie-icon-item"));
        Assert.assertTrue(movies.size() > 0, "No movies are displayed on the Popular Page!");

        System.out.println("✅ Popular Movies Page loaded successfully with " + movies.size() + " movies.");
    }

    @Test(priority = 2)
    public void testMovieNavigationToDetailsPage() {
        // Click on "Popular" link in the header
        WebElement popularBtn = driver.findElement(By.xpath("//a[contains(@href,'/popular')]"));
        popularBtn.click();

        // Wait until movies load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-icon-item")));

        // Get first movie element
        List<WebElement> movies = driver.findElements(By.className("movie-icon-item"));
        Assert.assertTrue(movies.size() > 0, "No movies found to test navigation!");

        // Click on first movie
        WebElement firstMovie = movies.get(0);
        String movieTitle = firstMovie.getAttribute("alt");
        firstMovie.click();

        // Wait for navigation to movie details
        wait.until(ExpectedConditions.urlContains("/movies/"));
        String currentUrl = driver.getCurrentUrl();

        // Assertion: Check if it navigates to a movie details page
        Assert.assertTrue(currentUrl.contains("/movies/"),
                "Clicking movie did not navigate to Movie Details page!");
        System.out.println("✅ Clicking on movie '" + movieTitle + "' navigated to details page: " + currentUrl);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
