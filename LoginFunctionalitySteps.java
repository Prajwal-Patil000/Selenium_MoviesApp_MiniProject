package Stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class LoginFunctionalitySteps {

    public WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("I am on the login page")
    public void imontheloginpage() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I entered valid username and password")
    public void enteringusercredentials() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("I click on a login button")
    public void clickonloginbtn() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I shoud be redirected to home page")
    public void redirectedtohomepage() {

        String expecturl = "https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expecturl));

        String currenturl = driver.getCurrentUrl();
        Assert.assertEquals(currenturl, expecturl, "Not match");
    }

    @Then("I Should check heading text of each section is presented")
    public void CheckingHeadingOfEachSectionIsPresented() {

        Assert.assertEquals(
                driver.findElement(By.cssSelector("div.home-bottom-container > div:first-child > h1.movies-list-heading")).getText(),
                "Trending Now"
        );

        Assert.assertEquals(
                driver.findElement(By.cssSelector("div.home-bottom-container > div:nth-child(2) > h1.movies-list-heading")).getText(),
                "Originals"
        );
    }

    @And("Verifying weather play button is presented")
    public void CheckingPlaybtnIsPresented() {
        Assert.assertTrue(
                driver.findElement(By.className("home-movie-play-button")).isDisplayed()
        );
    }

    @And("Verifying weather movies are displayed in trending section")
    public boolean CheckingtrendingMvoiesAreDisplayed() {

        List<WebElement> trendingmvoies =
                driver.findElements(By.cssSelector("div.home-bottom-container > div:first-child > div.movies-container > img"));

        return trendingmvoies.isEmpty();
    }

    @And("Verifying weather movies are displayed in origional section")
    public boolean CheckingorigionalMvoiesAreDisplayed() {

        List<WebElement> origionalgmvoies =
                driver.findElements(By.cssSelector("div.home-bottom-container > div:nth-child(2) > div.movies-container > img"));

        return origionalgmvoies.isEmpty();
    }

    @Then("I should check weather contact section is presented")
    public void CheckingContactSection() {
        Assert.assertTrue(
                driver.findElement(By.className("contact-us-paragraph")).isDisplayed()
        );
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

