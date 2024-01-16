package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public void productExtractionText() {
        response = given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);


//        21. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("Limit of product: " + limit);

//        22. Extract the total
        int total = response.extract().path("total");
        System.out.println("Total of product: " + total);

//        23. Extract the name of 5th product
        String productName = response.extract().path("data[4].name");
        System.out.println("5th Product name: " + productName);

//        24. Extract the names of all the products
        List<String> productList = response.extract().path("data.name");
        System.out.println("names of all the products: " + productList);

//        25. Extract the productId of all the products
        List<Integer> productIdList = response.extract().path("data.id");
        System.out.println("productId of all the products: " + productIdList);

//        26. Print the size of the data list
        System.out.println("Size of data list: " + productList.size());

        //        27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
        List<String> product = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("value of the product where product name = Energizer - MAX Batteries AA (4-Pack): " + product);

        //        28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
        List<String> productModel = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("Value of the product Model where product name is 'Energizer - N Cell E90 Batteries (2-Pack)': " + productModel);

        //        29. Get all the categories of 8th products
        List<String> categoriesOfProduct = response.extract().path("data[7].categories");
        System.out.println("Categories of 8th product: " + categoriesOfProduct);

        //        30. Get categories of the store where product id = 150115
        List<String> categoriesOfStore = response.extract().path("data.findAll{it.id==150115}.categories");
        System.out.println("Categories of store product id is 150115: " + categoriesOfStore);

        //        31. Get all the descriptions of all the products
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("Descriptions of all products: " + descriptions);

        //        32. Get id of all the all categories of all the products
        List<Integer> id = response.extract().path("data.categories.id");
        System.out.println("Id of all categories of all products: " + id);

        //        33. Find the product names Where type = HardGood
        List<String> productNameTypeIsHardGood = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("Product name where type is HardGood: " + productNameTypeIsHardGood);

        //        34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
        List<Integer> totalCategoriesProductNameIsDuracell = response.extract().path("data.find{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("Total number of categories for product name is Duracell - AA: " + totalCategoriesProductNameIsDuracell.size());


        //        35. Find the createdAt for all products whose price < 5.49
        List<String> listOfCreatedAt = response.extract().path("data.findAll { it.price < 5.49 }.createdAt");
        System.out.println("createdAt for all products whose price < 5.49: "+ listOfCreatedAt);

        //        36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
        List<String> categoriesProductNameIsEnergizer = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”:  " + categoriesProductNameIsEnergizer);

        //        37. Find the manufacturer of all the products
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("manufacturer of all the products: "+ manufacturer);

//        38. Find the imge of products whose manufacturer is = Energizer
        List<String> imageWhereManufacturerIsEnergizer = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("imge of products whose manufacturer is = Energizer: "+ imageWhereManufacturerIsEnergizer);

        //        39. Find the createdAt for all categories products whose price > 5.99
        List<String> listOfCategoryCreatedAt = response.extract().path("data.findAll { it.price > 5.99 }.categories.createdAt");
        System.out.println("createdAt for all categories products whose price > 5.99: "+ listOfCategoryCreatedAt);

//        40. Find the uri of all the products
        List<String> url = response.extract().path("data.url");
        System.out.println("uri of all the products: "+ url);

    }
}
