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
}
