package tests;

import io.qameta.allure.Description;
import models.AddingItemResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.AuthApi.getAuthCookie;
import static api.Requests.sendGetMyAccountRequest;
import static api.Requests.sendPostAddItemToCartRequest;
import static responseassertions.AssertAddProductResponse.assertionsAddProductResponse;
import static specs.Specs.response200Spec;

@DisplayName("API /addproducttocart/details Добавление товара в корзинру")
public class CartTest extends TestBase {

    String authCookieValue = getAuthCookie(login,password);
    int countOfItems;
    int quantity = 3;
    String data = "product_attribute_72_5_18=52" +
            "&product_attribute_72_6_19=54" +
            "&product_attribute_72_3_20=58" +
            "&addtocart_72.EnteredQuantity=" + quantity;

    @Test
    @DisplayName("Проверка позитивного сценария с добавлением товара в корзину")
    @Description("Добавление товара в карзину")
    void addItemToCartTest() {
            Document document = Jsoup.parse(sendGetMyAccountRequest("/cart", authCookieValue, response200Spec));
            countOfItems = Integer.parseInt(document.select(".cart-qty").text().replaceAll("[()]", ""));
        AddingItemResponse response = sendPostAddItemToCartRequest(
                "/addproducttocart/details/72/1", authCookieValue, data, AddingItemResponse.class, response200Spec);
        assertionsAddProductResponse(response, countOfItems, quantity);
    }
}
