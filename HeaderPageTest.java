import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class HeaderPageTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage headerPageTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech/");
        headerPageTest = new HomePage(driver);
    }

    @Test(priority = 1)
    public void checkTheLogoElement() {
        headerPageTest.LoginToApplication("rahul", "rahul@2021");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(headerPageTest.isLogoElementlocated(), "Logo Element Not Found");
    }

    @Test(priority = 3)
    public void checkTheNavigationFunctionalities() {
        headerPageTest.LoginToApplication("rahul", "rahul@2021");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Home Header Section
        WebElement homeLink = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
        homeLink.click();
        String homePage = driver.getCurrentUrl();
        Assert.assertEquals(homePage, "https://qamoviesapp.ccbp.tech/", "Not Navigated");

        // Popular Header Section
        WebElement popularHeaderSection = driver.findElement(By.xpath("//a[contains(text(), 'Popular')]"));
        popularHeaderSection.click();
        String popularPage = driver.getCurrentUrl();
        Assert.assertEquals(popularPage, "https://qamoviesapp.ccbp.tech/popular", "Not Navigated");

        // Search Header Section
        WebElement searchHeaderSection = driver.findElement(By.className("search-empty-button"));
        searchHeaderSection.click();
        String searchPage = driver.getCurrentUrl();
        Assert.assertEquals(searchPage, "https://qamoviesapp.ccbp.tech/search", "Not Navigated");

        // Account Header Section
        WebElement accountHeaderSection = driver.findElement(By.className("avatar-button"));
        accountHeaderSection.click();
        String accountPage = driver.getCurrentUrl();
        Assert.assertEquals(accountPage, "https://qamoviesapp.ccbp.tech/account", "Not Navigated");
    }

    @Test(priority = 2)
    public void checkTheNavBars() {
        headerPageTest.LoginToApplication("rahul", "rahul@2021");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Assert.assertTrue(headerPageTest.isHomeNavbarDisplayed(), "Home Page NavBar is not Working");
        Assert.assertTrue(headerPageTest.isAccountNavBarDisplayed(), "Account Page NavBar is not Working");
        Assert.assertTrue(headerPageTest.isSerachNavBarDisplayed(), "Search Button NavBar is not Working");
        Assert.assertEquals(headerPageTest.testPopularHeading(), "Popular", "Popular heading mismatch");
        Assert.assertTrue(headerPageTest.testPlayButton(), "Play Button Section is not Working");
        Assert.assertTrue(headerPageTest.testContactUsSection(), "Contact Us Section is not Working");
        Assert.assertTrue(headerPageTest.checkMoviesSection(), "Movie Section is not Working");
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }
}
