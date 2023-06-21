package tests;

import api.AuthApi;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;
import static api.Requests.sendGetAddressRequest;
import static api.Requests.sendPostAddressEditRequest;
import static data.DataTestAddressValues.getValuesMapAddress;
import static responseassertions.AssertAddressResponse.assertionsAddressResponse;
import static specs.Specs.response200Spec;
import static specs.Specs.response302Spec;

@DisplayName("API /customer/addressedit/ Изменение адреса")
public class AddressEditTests extends TestBase{

    String authCookieValue = AuthApi.getAuthCookie(login, password);

    @Test
    @DisplayName("Проверка позитивного сценария с изменением адреса")
    @Description("Изменение адреса")
    void editAddressValuesTest1() {
        String addressId = getValuesMapAddress().get("Address.Id");
        Map<String, String> data = getValuesMapAddress();
        sendPostAddressEditRequest("/customer/addressedit/" + addressId, authCookieValue, response302Spec, data);
        assertionsAddressResponse(sendGetAddressRequest("/customer/addresses", authCookieValue, response200Spec), data);
    }

    @DisplayName("Проверка негативного сценария отправки запроса без обязательного параметра")
    @Description("Проверка негативных сценариев")
    @ParameterizedTest(name = " : [{0}]")
    @ValueSource(strings = {"Address.Email", "Address.LastName", "Address.CountryId",
            "Address.FirstName", "Address.City", "Address.Address1",
            "Address.ZipPostalCode", "Address.PhoneNumber"})
    public void negativeTests(String param) {
        String addressId = getValuesMapAddress().get("Address.Id");
        Map<String, String> data = getValuesMapAddress();
        data.remove(param);
        sendPostAddressEditRequest("/customer/addressedit/" + addressId, authCookieValue, response200Spec, data);
    }

    @DisplayName("Проверка негативного сценария отправки запроса без необязательного параметра")
    @Description("Проверка негативных сценариев")
    @ParameterizedTest(name = " : [{0}]")
    @ValueSource(strings = {"Address.Company", "Address.Address2"})
    public void negativeTests3(String param) {
        String addressId = getValuesMapAddress().get("Address.Id");
        Map<String, String> data = getValuesMapAddress();
        data.put(param, "");
        sendPostAddressEditRequest("/customer/addressedit/" + addressId, authCookieValue, response302Spec, data);
        assertionsAddressResponse(sendGetAddressRequest("/customer/addresses", authCookieValue, response200Spec), data);
    }
}
