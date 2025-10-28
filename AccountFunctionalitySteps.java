package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccountFunctionalitySteps {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on Login page for Account")
    public void i_am_on_login_page_for_account() {
        driver.get("https://qamoviesapp.ccbp.tech/");
    }

    @When("I enter valid username and password for Account")
    public void i_enter_valid_username_and_password_for_account() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("I click on login button")
    public void i_click_on_login_button() {
        driver.findElement(By.className("login-button")).click();
    }

    @Given("I navigate to the Account Page for Account")
    public void i_navigate_to_the_account_page_for_account() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("avatar-button")));
        profileIcon.click();
    }

    @Then("All UI elements should be present and visible for Account")
    public void all_ui_elements_should_be_present_and_visible_for_account() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement accountHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Account']")));
        WebElement usernameInfo = driver.findElement(By.xpath("//p[contains(text(),'Username')]"));
        WebElement logoutBtn = driver.findElement(By.xpath("//button[text()='Logout']"));

        Assert.assertTrue(accountHeading.isDisplayed(), "Account heading not visible");
        Assert.assertTrue(usernameInfo.isDisplayed(), "Username info not visible");
        Assert.assertTrue(logoutBtn.isDisplayed(), "Logout button not visible");
    }

    @When("User clicks on the logout button for Account")
    public void user_click_on_the_logout_button_for_account() {
        driver.findElement(By.xpath("//button[text()='Logout']")).click();
    }

    @Then("I should be redirected to login page")
    public void i_should_be_redirected_to_login_page() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/login";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "User is not redirected to the login page after logout");
    }

    @After
    public void tearDown() {
            driver.quit();
        }
    }

