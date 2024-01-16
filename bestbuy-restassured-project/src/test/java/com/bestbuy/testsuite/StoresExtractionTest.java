package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void init() {
        response = given()
                .when()
                .get("/stores")
                .then()
                .statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void testLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void testTotal() {
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);
    }

    //3. Extract the name of 5th store
    @Test
    public void nameOfFifthStore() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of the 5th store: " + name);
    }

    //4. Extract the names of all the store
    @Test
    public void nameOfAllStore() {
        List<String> storeName = response.extract().path("data.name");
        System.out.println("The name of All stores: " + storeName);
    }

    //5. Extract the storeId of all the store
    @Test
    public void storeIdAllStore() {
        List<Integer> storeIdAllStore = response.extract().path("data.id");
        System.out.println("Store Id of All the store: " + storeIdAllStore);
    }

    //6. Print the size of the data list
    @Test
    public void sizeOfDataList() {
        List<HashMap<String, ?>> sizeOfData = response.extract().path("data");
        System.out.println("Size of the All Data List: " + sizeOfData.size());
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void storeWhereNameIsStCloud() {
        List<String> dataOfStore = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("All value of the store where store name = St Cloud: " + dataOfStore);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void addressWhereNameIsRochester(){
        List<String> addressOfStore = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("Address of store where store name is Rochester: " + addressOfStore);
    }
    //9. Get all the services of 8th store
    @Test
    public void allServices(){
        List<HashMap<String, Object>> allServices = response.extract().path("data[7].services.findAll{it.name}");
        System.out.println("All the services of 8th store: " + allServices);
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServices(){
        List<HashMap<String,?>> storeServices = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Windows Store'}.collect { it.storeservices }}");
        System.out.println("Service name is Windows Store get Store services of the store: " + storeServices);
    }
    //11. Get all the storeId of all the store
    @Test
    public void getStoreId(){
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("All storeId: " + storeId);
    }
    //12. Get id of all the store
    @Test
    public void getId(){
        List<Integer>  id = response.extract().path("data.id");
        System.out.println("All store ID: " + id);

    }
    //13. Find the store names Where state = ND
    @Test
    public void storeNameStateIsNd(){
        List<String> storeName = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("Store name for State is ND: " + storeName);
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void servicesStoreNameIsRochester(){
        List<HashMap<String,?>> numberOfServices = response.extract().path("data.find{ it.name == 'Rochester'}.services");
        System.out.println("Store name is Rochester get the number of services: " + numberOfServices.size());
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void createAtNameIsWindowsStore() {
       List<HashMap<String,?>> createdAtName = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Windows Store'}.collect { it.createdAt }}");
        System.out.println("Services name is 'Windows Store' find all createdAt: " + createdAtName);
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void servicesNameForFargo(){
        List<String> servicesName = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("Services Name where store name is Fargo: " + servicesName);
    }
    //17. Find the zip of all the store
    @Test
    public void zipForAllStore(){
        List<String> zipOfStore = response.extract().path("data.zip");
        System.out.println("Zip for all the store: " + zipOfStore);
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void zipForStoreIsRoseville(){
        List<String> zipForRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("Zip for Roseville store: " + zipForRoseville);
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void getStoreServicesStoreNameMangnoliaHomeTheater(){
        List<HashMap<String,?>> storeServices = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Magnolia Home Theater'}.collect { it.storeservices }}");
        System.out.println("Service name is 'Magnolia Home Theater' get Store services of the store: " + storeServices);
    }
    //20. Find the lat of all the stores
    @Test
    public void getLat(){
        List<String> latOfAllStore = response.extract().path("data.lat");
        System.out.println("The lat of all the stores: " + latOfAllStore);
    }
}
