package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageStellarBurgers {

    private static final By REGISTER_LINK = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");
    private static final By EMAIL_FIELD = By.xpath(".//input[@type='text']");

    private static final By PASSWORD_FIELD = By.xpath(".//input[@type='password']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath(".//a[@href='/forgot-password']");

    private WebDriver driver;

    public LoginPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылке Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(REGISTER_LINK).click();
    }

    @Step("Проверка загрузки страницы входа в аккаунт")
    public boolean checkLoadLoginPage() {
        WebElement loginButton = new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return loginButton.isDisplayed();
    }

    @Step("Ввод email для входа")
    public void enterEmail(String email){
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Ввод пароля для входа")
    public void enterPassword(String password){
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickEnter(){
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Клик по ссылке Восстановить пароль")
    public void clickForgotPassword(){
        driver.findElement(FORGOT_PASSWORD_LINK).click();
    }

}
