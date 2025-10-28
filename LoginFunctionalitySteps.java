package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginFunctionalitySteps {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on the Login page")
    public void i_am_on_the_login_page() {
        driver.get("https://qamoviesapp.ccbp.tech/");
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @When("I enter invalid username and password")
    public void i_enter_invalid_username_and_password() {
        driver.findElement(By.id("usernameInput")).sendKeys("rhul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul2021");
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should be redirected to the home page")
    public void i_should_be_redirected_to_the_home_page() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Login failed — URLs do not match!");
    }

    @Then("I should see an error message for invalid login")
    public void i_should_see_an_error_message_for_invalid_login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        String errorMsg = driver.findElement(By.className("error-message")).getText();
        Assert.assertTrue(errorMsg.contains("invalid"), "Expected an error message, but none was displayed!");
    }

    @After
    public void tearDown() {
            driver.quit();

    }
}
