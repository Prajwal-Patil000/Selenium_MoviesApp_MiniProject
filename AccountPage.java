package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    public WebDriver driver;

    // Login locators
    By USERNAME = By.id("usernameInput");
    By PASSWORD = By.id("passwordInput");
    By LOGIN_BUTTON = By.className("login-button");

    // Account page locators
    By HEADING = By.className("account-heading");
    By MEMBERSHIP_CONTAINER = By.className("membership-container");
    By MEMBERSHIP_HEADING = By.className("membership-heading");
    By MEMBERSHIP_USERNAME = By.className("membership-username");
    By MEMBERSHIP_PASSWORD = By.className("membership-password");
    By PLAN_CONTAINER = By.className("plan-container");
    By PLAN_PARAGRAPH = By.className("plan-paragraph");
    By LOGOUT_BUTTON = By.className("logout-button");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LoginToApplication(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public boolean isHeadingDisplayed() { return driver.findElement(HEADING).isDisplayed(); }
    public boolean isMembershipContainerDisplayed() { return driver.findElement(MEMBERSHIP_CONTAINER).isDisplayed(); }
    public boolean isMembershipHeadingDisplayed() { return driver.findElement(MEMBERSHIP_HEADING).isDisplayed(); }
    public boolean isMembershipUsernameDisplayed() { return driver.findElement(MEMBERSHIP_USERNAME).isDisplayed(); }
    public boolean isMembershipPasswordDisplayed() { return driver.findElement(MEMBERSHIP_PASSWORD).isDisplayed(); }
    public boolean isPlanContainerDisplayed() { return driver.findElement(PLAN_CONTAINER).isDisplayed(); }
    public boolean isPlanParagraphDisplayed() { return driver.findElement(PLAN_PARAGRAPH).isDisplayed(); }
    public boolean isLogoutButtonDisplayed() { return driver.findElement(LOGOUT_BUTTON).isDisplayed(); }
}
