package graphQL;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class RickMortyAPITest {

    @Test
    public void testGraphQLQuery() {
        // Base URL for the GraphQL endpoint
        RestAssured.baseURI = "https://rickandmortyapi.com/graphql";


        // GraphQL query and variables

        String query = "query character($id: ID!) { character(id: $id) { origin { id } location { id } created } }";
        String variables = "{\"id\":\"1\"}";

        // Send the GraphQL request
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"" + query + "\", \"variables\":" + variables + "}")
                .when()
                .post()
                .then()
                .extract()
                .response();

        // Print the response
        response.prettyPrint();
    }


    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"1"},
                {"3"}
        };
    }


    @Test(dataProvider = "getData")
    public void testGraphQLQueryWithPOJO(String id) {
        // Base URL for the GraphQL endpoint
        RestAssured.baseURI = "https://rickandmortyapi.com/graphql";

        // Create Variables instance
        Variables variables = new Variables(id);

        // Create GraphQL request instance
        GraphQLQuery graphQLQuery = new GraphQLQuery(
                "query character($id: ID!) { character(id: $id) { origin { id } location { id } created } }",
                variables
        );

        // Send the GraphQL request
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(graphQLQuery)
                .when()
                .post()
                .then()
                .extract()
                .response();

        // Print the response
        System.out.println(response.prettyPrint());

    }



}
