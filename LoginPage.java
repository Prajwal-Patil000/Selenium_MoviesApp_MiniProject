package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    // ✅ Corrected Locators (matched to actual HTML)
    By logo = By.className("login-website-logo");
    By heading = By.tagName("h1");
    By usernameLabel = By.xpath("//label[@for='usernameInput']");
    By passwordLabel = By.xpath("//label[@for='passwordInput']");
    By usernameInput = By.id("usernameInput");
    By passwordInput = By.id("passwordInput");
    By loginButton = By.className("login-button");
    By errorMessage = By.className("error-message");

    // ✅ Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Check logo presence
    public boolean checkTheLogoElement() {
        try {
            return driver.findElement(logo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Get heading text
    public String checkTheHeadingElement() {
        return driver.findElement(heading).getText().trim();
    }

    // ✅ Username label text
    public String checkUserNameLabel() {
        return driver.findElement(usernameLabel).getText().trim();
    }

    // ✅ Password label text
    public String checkPasswordLabel() {
        return driver.findElement(passwordLabel).getText().trim();
    }

    // ✅ Check login button visible
    public boolean checkTheLoginButton() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Login method
    public void loginToApplication(String username, String password) {
        WebElement userField = driver.findElement(usernameInput);
        WebElement passField = driver.findElement(passwordInput);
        WebElement loginBtn = driver.findElement(loginButton);

        userField.clear();
        userField.sendKeys(username);

        passField.clear();
        passField.sendKeys(password);

        loginBtn.click();
    }

    // ✅ Get error message safely
    public String errorMsg() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
