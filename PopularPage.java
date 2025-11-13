package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PopularPage {

    public WebDriver driver;

    // Locators
    By LoginButton = By.className("login-button");
    By USERNAME = By.id("usernameInput");
    By PASSWORD = By.id("passwordInput");

    // Constructor
    public PopularPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Login method (used by test class)
    public void LoginToApplication(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LoginButton).click();
    }
}
