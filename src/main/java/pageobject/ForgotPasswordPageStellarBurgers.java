package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPageStellarBurgers {

    private static final By ENTER_LINK = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    private WebDriver driver;

    public ForgotPasswordPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылке Войти на странице Восстановление пароля")
    public void clickEnter() {
        driver.findElement(ENTER_LINK).click();
    }
}
