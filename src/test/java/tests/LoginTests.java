package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.HeaderBlock;
import pages.LoginPage;
import static api.AuthApi.authCookieKey;
import static api.AuthApi.getAuthCookie;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

@DisplayName("Авторизация")
public class LoginTests extends TestBase{

    LoginPage loginPage = new LoginPage();
    HeaderBlock headerBlock = new HeaderBlock();

    @Test
    @DisplayName("Проверка авторизации")
    @Description("Проверка авторизации")
    public void loginTest() {
        loginPage.openPage().setEmail(login).setPassword(password).login();
        step("Проверяем что в хедерах акаунта присутствует текст: " + login,
                () -> headerBlock.accountEmail.getText().equalsIgnoreCase(login));
    }

    @Test
    @DisplayName("Проверка успешной авторизации через API")
    @Description("Проверка авторизации")
    public void loginTestDecomposition() {
        open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
        Cookie authCookie = new Cookie(authCookieKey, getAuthCookie(login, password));
        getWebDriver().manage().addCookie(authCookie);
        open("");
        step("Проверяем что в хедерах акаунта присутствует текст: " + login,
                () -> headerBlock.accountEmail.getText().equalsIgnoreCase(login));
    }

}
