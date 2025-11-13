import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://qamoviesapp.ccbp.tech/");
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void checkTheLogoElement() {
        Assert.assertTrue(loginPage.checkTheLogoElement(), "Logo is not displayed");
        System.out.println("Logo Element Checked Successfully");
    }

    @Test(priority = 2)
    public void checkTheHeadingElement() {
        Assert.assertEquals(loginPage.checkTheHeadingElement(), "Login", "Main heading mismatch");
    }

    @Test(priority = 3)
    public void checkTheLabelElementTexts() {
        String userLabel = loginPage.checkUserNameLabel();
        String passwordLabel = loginPage.checkPasswordLabel();
        Assert.assertEquals(userLabel, "USERNAME");
        Assert.assertEquals(passwordLabel, "PASSWORD");
        System.out.println("Both Labels Matched Successfully");
    }

    @Test(priority = 4)
    public void checkTheLoginButton() {
        Assert.assertTrue(loginPage.checkTheLoginButton(), "Login button not found");
    }

    @Test(priority = 5)
    public void testLoginWithEmptyInput() {
        loginPage.loginToApplication("", "");
        Assert.assertEquals(loginPage.errorMsg(), "*Username or password is invalid");
    }

    @Test(priority = 6)
    public void testLoginWithEmptyUsername() {
        loginPage.loginToApplication("", "rahul@2021");
        Assert.assertEquals(loginPage.errorMsg(), "*Username or password is invalid");
    }


    @Test(priority = 7)
    public void testLoginWithEmptyPassword() {
        loginPage.loginToApplication("rahul", "");
        Assert.assertEquals(loginPage.errorMsg(), "*Username or password is invalid");
    }

    // Invalid username, correct password
    @Test(priority = 8)
    public void testLoginWithInvalidUsername() {
        loginPage.loginToApplication("raul", "rahul@2021");
        Assert.assertEquals(loginPage.errorMsg(), "*invalid username");
    }

    // Correct username, wrong password
    @Test(priority = 9)
    public void testLoginWithInvalidPassword() {
        loginPage.loginToApplication("rahul", "rahul2021");
        Assert.assertEquals(loginPage.errorMsg(), "*username and password didn't match");
    }

    // Invalid username and password
    @Test(priority = 10)
    public void testLoginWithInvalidUsernameAndPassword() {
        loginPage.loginToApplication("rahl", "rahul2021");
        Assert.assertEquals(loginPage.errorMsg(), "*invalid username");
    }

    @Test(priority = 11)
    public void testLoginWithValidCredentials() {
        loginPage.loginToApplication("rahul", "rahul@2021");
        String expectedUrl = "https://qamoviesapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Navigation to Home Page failed");
        System.out.println("Login Successful! Redirected to Home Page.");
    }
}
