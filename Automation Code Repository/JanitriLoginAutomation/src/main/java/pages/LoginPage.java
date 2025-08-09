package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "formEmail")
    WebElement userIdInput;

    @FindBy(id = "formPassword")
    WebElement passwordInput;

    @FindBy(css = "button.login-button")
    WebElement loginButton;

    @FindBy(css = "button[aria-label='toggle password visibility'], .MuiInputAdornment-root svg")
    WebElement passwordEyeIcon;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserId(String userId) {
        userIdInput.clear();
        userIdInput.sendKeys(userId);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled(); 
    }

    public boolean isPasswordMasked() {
        return passwordInput.getAttribute("type").equals("password");
    }

    public void togglePasswordVisibility() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(passwordEyeIcon));
            passwordEyeIcon.click();
        } catch (Exception e) {
            System.out.println("Password toggle icon not found or not clickable.");
        }
    }

    public String getErrorMessage() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'MuiAlert-message')]")));
        return error.getText();
    } catch (Exception e) {
        return null;
    }
}


    public boolean areElementsPresent() {
        try {
            boolean userIdVisible = userIdInput.isDisplayed();
            boolean passwordVisible = passwordInput.isDisplayed();
            boolean loginBtnVisible = loginButton.isDisplayed();
            return userIdVisible && passwordVisible && loginBtnVisible;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordTogglePresent() {
    try {
        return passwordEyeIcon.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

}
