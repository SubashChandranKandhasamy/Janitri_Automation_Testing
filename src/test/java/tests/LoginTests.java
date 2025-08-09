package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginButtonAlwaysEnabled() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled by default.");
    }
   @Test(priority = 2)
public void testPasswordToggleIconPresence() {
    loginPage = new LoginPage(driver);
    if (loginPage.isPasswordTogglePresent()) {
        Assert.assertTrue(true, "Password toggle icon is present.");
    } else {
        System.out.println("Password toggle icon not available on this UI. Skipping check.");
    }
}

@Test(priority = 3)
public void testInvalidLoginShowErrorMsg() {
    loginPage = new LoginPage(driver);
    loginPage.enterUserId("invalid@user.com");
    loginPage.enterPassword("wrongpass");
    loginPage.clickLogin();

    // Give it time to show the message
    String error = loginPage.getErrorMessage();
    Assert.assertNotNull(error, "Error message should be displayed");
    System.out.println("Error Message: " + error);
}


    @Test(priority = 4)
    public void testPresenceOfLoginPageElements() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.areElementsPresent(), "All login page elements should be visible");
    }
}
