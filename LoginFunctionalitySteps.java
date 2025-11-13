package Stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginFunctionalitySteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User is on Login page")
    public void user_is_on_login_page() {
        driver.get("https://qamoviesapp.ccbp.tech/");
    }

    @When("User enters valid credentials and clicks login")
    public void user_enters_valid_credentials_and_clicks_login() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();
    }

    @Then("User should be redirected to Home page")
    public void user_should_be_redirected_to_home_page() {
        wait.until(ExpectedConditions.urlContains("/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("qamoviesapp.ccbp.tech"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
