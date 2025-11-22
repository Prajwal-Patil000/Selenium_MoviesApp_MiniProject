package Stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HeaderFunctionalitySteps {

    public WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("I am on the enter in login")
    public void imOnTheLoginPage() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I enter valid username and password")
    public void enterUserCredentials() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("I click on the login button")
    public void clickLoginButton() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should be redirected to the home page")
    public void redirectedToHomePage() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Home page URL does not match!");
    }

    @When("I navigate to the Popular page through header")
    public void navigateToPopularPage() {
        driver.findElement(By.xpath("//a[text()='Popular']")).click();
    }

    @Then("I should be redirected to the Popular page")
    public void redirectedToPopularPage() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/popular";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Popular page URL does not match!");
    }

    @When("I navigate to the Account page through header")
    public void navigateToAccountPage() {
        driver.findElement(By.className("avatar-button")).click();
    }

    @Then("I should be redirected to the Account page")
    public void redirectedToAccountPage() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/account";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Account page URL does not match!");
    }

    @When("I navigate back to the Home page through header")
    public void navigateBackToHomePage() {
        driver.findElement(By.xpath("//a[text()='Home']")).click();
    }

    @Then("I should be redirected back to the Home page")
    public void redirectedBackToHomePage() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Failed to navigate back to Home page!");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

