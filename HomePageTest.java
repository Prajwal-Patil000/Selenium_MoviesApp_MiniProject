import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class HomePageTest {
    public WebDriver driver;
    public HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qamoviesapp.ccbp.tech/");
        homePage = new HomePage(driver);
        homePage.LoginToApplication("rahul", "rahul@2021");
    }

    @Test(priority = 1)
    public void testHomePageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/",
                "Home Page did not load properly!");
    }

    @Test(priority = 2)
    public void testHeadingTexts() {
        Assert.assertTrue(homePage.isTrendingNowHeadingDisplayed(), "Trending Now heading missing!");
        Assert.assertTrue(homePage.isOriginalsHeadingDisplayed(), "Originals heading missing!");
    }

    @Test(priority = 3)
    public void testPlayButtonDisplayed() {
        Assert.assertTrue(homePage.isPlayButtonDisplayed(), "Play Button not visible!");
    }

    @Test(priority = 4)
    public void testMoviesInTrendingNow() {
        Assert.assertTrue(homePage.areTrendingMoviesDisplayed(), "No movies displayed in Trending Now section!");
    }

    @Test(priority = 5)
    public void testMoviesInOriginals() {
        Assert.assertTrue(homePage.areOriginalsMoviesDisplayed(), "No movies displayed in Originals section!");
    }

    @Test(priority = 6)
    public void testContactUsSection() {
        Assert.assertTrue(homePage.isContactUsSectionDisplayed(), "Contact Us section is missing!");
    }

    // ✅ Fixed Slick Next functionality test
    @Test
    public void testSlickNextFunctionality() {
        // Step 1: Capture the first visible movie/poster image
        String firstPoster = homePage.getCurrentMoviePoster();
        System.out.println("Before clicking next: " + firstPoster);

        // Step 2: Click the slick "Next" button
        homePage.clickNextInSlick();

        // Step 3: Wait until the poster changes (max 5 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> {
            String currentPoster = homePage.getCurrentMoviePoster();
            return !currentPoster.equals(firstPoster);
        });

        // Step 4: Capture the new poster
        String nextPoster = homePage.getCurrentMoviePoster();
        System.out.println("After clicking next: " + nextPoster);

        // Step 5: Validate that it changed
        Assert.assertNotEquals(nextPoster, firstPoster, "Next button did not change the movie!");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
