package tests;

import io.qameta.allure.Description;
import models.AddingItemResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.CartInfoPage;
import static api.AuthApi.authCookieKey;
import static api.AuthApi.getAuthCookie;
import static api.Requests.sendGetMyAccountRequest;
import static api.Requests.sendPostAddItemToCartRequest;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static responseassertions.AssertAddProductResponse.assertionsAddProductResponse;
import static specs.Specs.response200Spec;

@DisplayName("Корзина")
public class CartTest extends TestBase {

    CartInfoPage cartInfoPage = new CartInfoPage();

    String authCookieValue = getAuthCookie(login, password);
    int countOfItems;
    int quantity = 3;
    String data = "product_attribute_72_5_18=52" +
            "&product_attribute_72_6_19=54" +
            "&product_attribute_72_3_20=58" +
            "&addtocart_72.EnteredQuantity=" + quantity;

    @Test
    @DisplayName("Проверка позитивного сценария с добавлением товара в корзину")
    @Description("Добавление товара в корзину")
    void addItemToCartTest() {
        Document document = Jsoup.parse(sendGetMyAccountRequest("/cart", authCookieValue, response200Spec));
        countOfItems = Integer.parseInt(document.select(".cart-qty")
                .text().replaceAll("[()]", ""));
        AddingItemResponse response = sendPostAddItemToCartRequest(
                "/addproducttocart/details/72/1", authCookieValue, data, AddingItemResponse.class, response200Spec);
        assertionsAddProductResponse(response, countOfItems, quantity);
    }

    @Test
    @DisplayName("Проверка удаления товара из корзины")
    @Description("Удаление товара")
    void deleteItemToCartTest() {
        Document document = Jsoup.parse(sendGetMyAccountRequest("/cart", authCookieValue, response200Spec));
        countOfItems = Integer.parseInt(document.select(".cart-qty")
                .text().replaceAll("[()]", ""));
        AddingItemResponse response = sendPostAddItemToCartRequest(
                "/addproducttocart/details/72/1", authCookieValue, data, AddingItemResponse.class, response200Spec);
        assertionsAddProductResponse(response, countOfItems, quantity);
        open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
        Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
        getWebDriver().manage().addCookie(authCookie);
        cartInfoPage
                .openPage()
                .checkOrders("Processor: Slow")
                .clickCheckbox()
                .clickDeleteButton()
                .checkDeleteItemCard();
    }

}
