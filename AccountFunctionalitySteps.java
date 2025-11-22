package Stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;

public class AccountFunctionalitySteps {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ---------------- LOGIN -----------------

    @Given("I am on the movies login page")
    public void openLoginPage() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I enter valid login credentials")
    public void enterLoginCredentials() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("I click the login button")
    public void clickLoginButton() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should be logged in successfully")
    public void validateLogin() {
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://qamoviesapp.ccbp.tech/", "Login failed");
    }

    // ------------- ACCOUNT PAGE UI ----------------

    @When("I navigate to account page")
    public void navigateToAccountPage() {
        driver.findElement(By.className("avatar-button")).click();
        wait.until(ExpectedConditions.urlContains("/account"));
    }

    @Then("I should be redirected to account page")
    public void verifyAccountPageURL() {
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://qamoviesapp.ccbp.tech/account",
                "Not navigated to Account Page");
    }

    @Then("I should see all the account page UI elements")
    public void verifyAllAccountPageElements() {

        Assert.assertTrue(driver.findElement(By.className("account-heading")).isDisplayed(),
                "Heading not displayed");

        Assert.assertTrue(driver.findElement(By.className("membership-container")).isDisplayed(),
                "Membership container missing");

        Assert.assertTrue(driver.findElement(By.className("membership-heading")).isDisplayed(),
                "Membership heading missing");

        Assert.assertTrue(driver.findElement(By.className("membership-username")).isDisplayed(),
                "Membership username missing");

        Assert.assertTrue(driver.findElement(By.className("membership-password")).isDisplayed(),
                "Membership password missing");

        Assert.assertTrue(driver.findElement(By.className("plan-container")).isDisplayed(),
                "Plan container missing");

        Assert.assertTrue(driver.findElement(By.className("plan-paragraph")).isDisplayed(),
                "Plan paragraph missing");

        Assert.assertTrue(driver.findElement(By.className("logout-button")).isDisplayed(),
                "Logout button missing");
    }

    // ---------------- LOGOUT FUNCTIONALITY ----------------

    @When("I click on logout button")
    public void clickLogout() {
        driver.findElement(By.className("logout-button")).click();
    }

    @Then("I should be redirected to login page after logout")
    public void verifyLogout() {

        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/login"));

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://qamoviesapp.ccbp.tech/login",
                "Logout did not navigate to login page");

        Assert.assertTrue(driver.findElement(By.id("usernameInput")).isDisplayed(),
                "Username input not visible after logout");
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

