package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStellarBurgers {

    private static final By LOGIN_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");

    private WebDriver driver;

    public HomePageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickLoginAccount() {
        driver.findElement(LOGIN_ACCOUNT_BUTTON).click();
    }
}
