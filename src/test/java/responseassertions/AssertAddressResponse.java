package responseassertions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertAddressResponse {

    public static void assertionsAddressResponse(String page, Map<String, String> data) {
        Document document = Jsoup.parse(page);
        step("Проверяем что параметр phone присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("phone").text(),
                        "Phone number: " + data.get("Address.PhoneNumber")));
        step("Проверяем что параметр title присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("title").text(),
                        "My account " + data.get("Address.FirstName") + " " + data.get("Address.LastName")));
        step("Проверяем что параметр name присутствует в ответе", () ->
                assertEquals(document.select("li.name").first().text(),
                        data.get("Address.FirstName") + " " + data.get("Address.LastName")));
        step("Проверяем что параметр email присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("email").text(),
                        "Email: " + data.get("Address.Email")));
        step("Проверяем что параметр fax присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("fax").text(),
                        "Fax number: " + data.get("Address.FaxNumber")));
        step("Проверяем что параметр company присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("company").text(),
                        data.get("Address.Company")));
        step("Проверяем что параметр address1 присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("address1").text(),
                        data.get("Address.Address1")));
        step("Проверяем что параметр address2 присутствует в ответе", () ->
                assertEquals(document.getElementsByClass("address2").text(),
                        data.get("Address.Address2")));
    }

}
