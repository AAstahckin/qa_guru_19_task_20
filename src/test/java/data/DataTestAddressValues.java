package data;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class DataTestAddressValues {


    public static Map<String, String> getValuesMapAddress() {
        Faker faker = new Faker();
        Map<String, String> data = new HashMap<>();
        data.put("Address.Id", "3122427");
        data.put("Address.FirstName", faker.name().firstName());
        data.put("Address.LastName", faker.name().lastName());
        data.put("Address.Email", faker.internet().emailAddress());
        data.put("Address.Company", faker.company().name());
        data.put("Address.CountryId", Integer.valueOf(faker.random().nextInt(230) + 1).toString());
        data.put("Address.StateProvinceId", Integer.valueOf(faker.random().nextInt(52)).toString());
        data.put("Address.City", faker.address().city());
        data.put("Address.Address1", faker.address().countryCode());
        data.put("Address.Address2", faker.address().fullAddress());
        data.put("Address.ZipPostalCode", faker.address().zipCode());
        data.put("Address.PhoneNumber", faker.phoneNumber().cellPhone());
        data.put("Address.FaxNumber", faker.phoneNumber().subscriberNumber());
        return data;
    }




}
