import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MovieDetailsPage;
import java.time.Duration;

public class MovieDetailsPageTest {

    public WebDriver driver;
    public MovieDetailsPage movieDetailsPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qamoviesapp.ccbp.tech/");
        movieDetailsPage = new MovieDetailsPage(driver);
    }

    @Test
    public void moviePageUitest() {
        movieDetailsPage.LoginToApplication("rahul", "rahul@2021");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ✅ Correct XPath for the first movie in the “Home” page
        WebElement firstMovie = driver.findElement(By.xpath("(//div[contains(@class,'home-movie-item')])[1]/img"));
        firstMovie.click();

        Assert.assertTrue(movieDetailsPage.isMovieHeadingDisplayed(), "Heading not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieReviewDisplayed(), "Review not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieOverviewDisplayed(), "Overview not displayed");
        Assert.assertTrue(movieDetailsPage.isMoviePlaybuttonDisplayed(), "Play button not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieCategoriesDisplayed(), "Categories not displayed");
    }

    @Test
    public void popularPageUitest() {
        movieDetailsPage.LoginToApplication("rahul", "rahul@2021");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        WebElement popularTab = driver.findElement(By.xpath("//a[contains(@href,'/popular')]"));
        popularTab.click();

        // Wait briefly to load page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        WebElement firstPopularMovie = driver.findElement(By.xpath("(//div[contains(@class,'movie-image-container')])[1]/img"));
        firstPopularMovie.click();

        Assert.assertTrue(movieDetailsPage.isMovieHeadingDisplayed(), "Heading not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieReviewDisplayed(), "Review not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieOverviewDisplayed(), "Overview not displayed");
        Assert.assertTrue(movieDetailsPage.isMoviePlaybuttonDisplayed(), "Play button not displayed");
        Assert.assertTrue(movieDetailsPage.isMovieCategoriesDisplayed(), "Categories not displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
