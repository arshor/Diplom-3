package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PersonalAccountPageStellarBurgers {

    private static final By SAVE_BUTTON = By.xpath(".//button[text()='Сохранить']");
    private static final By CONSTRUCTOR_LINK = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p");
    private static final By LOGO_LINK = By.xpath("//*[@id='root']/div/header/nav/div/a");
    private static final By QUIT_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button");

    private WebDriver driver;

    public PersonalAccountPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки страницы Личный кабинет")
    public boolean checkLoadPersonalAccountPage() {
        WebElement saveButton = new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return saveButton.isDisplayed();
    }

    @Step("Клик по ссылке Конструктор")
    public void clickConstructor() {
        driver.findElement(CONSTRUCTOR_LINK).click();
    }

    @Step("Клик по лого")
    public void clickLogo() {
        driver.findElement(LOGO_LINK).click();
    }

    @Step("Клик кнопке Выход")
    public void clickQuit() {
        new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(QUIT_BUTTON));
        driver.findElement(QUIT_BUTTON).click();
    }
}
