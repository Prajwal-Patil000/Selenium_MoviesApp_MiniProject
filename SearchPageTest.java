import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SearchPage;

import java.time.Duration;
import java.util.List;

public class SearchPageTest {

    WebDriver driver;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        // ChromeDriver path
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open application
        driver.get("https://qamoviesapp.ccbp.tech/");
        searchPage = new SearchPage(driver);

        // Login before each test
        searchPage.login("rahul", "rahul@2021");
    }

    @Test(priority = 1)
    public void searchVenomMovie() {
        searchPage.openSearchPage();
        searchPage.searchMovie("Venom");

        int count = searchPage.getSearchResultsCount();
        System.out.println("Venom results count: " + count);
        Assert.assertTrue(count > 0, "No movies found for Venom!");
    }

    @Test(priority = 2)
    public void searchAvatarMovie() {
        searchPage.openSearchPage();
        searchPage.searchMovie("Avatar");

        int count = searchPage.getSearchResultsCount();
        System.out.println("Avatar results count: " + count);
        Assert.assertTrue(count > 0, "No movies found for Avatar!");
    }

    @Test(priority = 3)
    public void searchInvalidMovie() {
        searchPage.openSearchPage();
        searchPage.searchMovie("xyz123");

        int count = searchPage.getSearchResultsCount();
        System.out.println("Invalid search count: " + count);
        Assert.assertEquals(count, 0, "Invalid movie search should return 0 results!");
        Assert.assertTrue(searchPage.isFailureDisplayed(), "Failure image/text not displayed for invalid search!");
    }

    // ✅ New Test - Check movie navigation from search results
    @Test(priority = 4)
    public void testClickMovieNavigatesToDetailsPage() {
        searchPage.openSearchPage();
        searchPage.searchMovie("Venom");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("movie-image")));

        List<WebElement> results = driver.findElements(By.className("movie-image"));
        Assert.assertTrue(results.size() > 0, "No movies found to click on!");

        // Click first movie
        WebElement firstMovie = results.get(0);
        String movieAlt = firstMovie.getAttribute("alt");
        firstMovie.click();

        // Wait for navigation to details page
        wait.until(ExpectedConditions.urlContains("/movies/"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/movies/"), "Not redirected to Movie Details Page!");
        System.out.println("✅ Clicking on movie '" + movieAlt + "' navigated to details page: " + currentUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
