package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class StoresAssertionTest extends TestBase {
    /*1. Verify the if the total is equal to 1561
2. Verify the if the stores of limit is equal to 10
3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
5. Verify the storeId=7 inside storeservices of the third store of second services
6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
7. Verify the state = MN of forth store
8. Verify the store name = Rochester of 9th store
9. Verify the storeId = 11 for the 6th store
10. Verify the serviceId = 4 for the 7th store of forth service
     */
    @Test
    public void verifyStoreDetails() {
        given()
                .get("/stores")
                .then()
                .statusCode(200)
                .body("total", equalTo(1561),
                        "limit", equalTo(10),
                        "data.name", hasItems("Inver Grove Heights"),
                        "data.name", hasItems("Roseville", "Burnsville", "Maplewood"),
                        "data[2].services[1].storeservices.storeId",equalTo(7),
//                        "data[2].services[0].storeservices", hasEntry("createdAt", "2016-11-17T17:57:09.417Z"),
                        "data[2].services.storeservices",everyItem(hasKey("createdAt")),
                        "data[3].state", equalTo("MN"),
                        "data[8].name", equalTo("Rochester"),
                        "data[5].services[0].storeservices.storeId",equalTo(11),
                        "data[6].services[3].storeservices.serviceId",equalTo(4)
                );

    }
}
