

import com.google.gson.JsonElement;
import core.GeoApiAnswer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import core.GeoApi;

import java.util.List;

import static core.GeoConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestGeoJSON {
    @Test
    public void simpleApiCall(){
        RestAssured
                .given()
                    .queryParam(PARAM_GEOCODE, geocodeTest )
                    .params(PARAM_FORMAT, Formats.json)
                //.param("format", "json")
                    .accept(ContentType.JSON)
                    .auth().basic("abcName", "abcPassword")
                    .header("custom header1", "header1.value")
                    .and()
                    .body("some body payroll")
                    .log().everything()
                .when()
                    .get(GEO_API_URI)
                    .prettyPeek()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.allOf(
                        Matchers.containsString(requestResp),
                        Matchers.containsString(foundResp),
                        Matchers.containsString(requestResp)))
                .contentType(ContentType.JSON)
                .time(lessThan(20000L)); // Milliseconds

    }
    // different http methods calls
    @Test
    public void spellerApiCallsWithDifferentMethods() {
        //GET
        RestAssured
                .given()
                .param(PARAM_GEOCODE,geocodeTest)
                .log().everything()
                .get(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //POST
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log().everything()
                .post(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //HEAD
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log().everything()
                .head(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //OPTIONS
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log().everything()
                .options(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //PUT
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log().everything()
                .put(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //PATCH
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log()
                .everything()
                .patch(GEO_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //DELETE
        RestAssured
                .given()
                .param(PARAM_GEOCODE, geocodeTest)
                .log()
                .everything()
                .delete(GEO_API_URI).prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .statusLine("HTTP/1.1 405 Not Allowed");
    }

    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(GeoApi.baseRequestConfiguration())
                .param(PARAM_GEOCODE, geocodeTest)
                .get().prettyPeek()
                .then().specification(GeoApi.successResponse());
    }

    @Test
    public void reachBuilderUsage(){
        GeoApi.with()
                .format(Formats.xml)
                .geocode(geocodeTest)
                .callApi()
                .then().specification(GeoApi.successResponse());
    }

    //validate an object we've got in API response
    @Test
    public void validateGeoAnswerAsAnObject() {
        List<GeoApiAnswer> answers =
                GeoApi.getGeoAnswers(
                        GeoApi.with().geocode(PARAM_GEOCODE).format(Formats.json).callApi());
        assertThat(answers.get(0).equals("request"), equalTo("geocode"));
        assertThat(answers.get(0).equals("found"), equalTo("0"));
        assertThat(answers.get(0).equals("results"), equalTo("10"));

    }
}
