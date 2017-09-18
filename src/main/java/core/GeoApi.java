package core;


import beans.GeoAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static core.GeoConstants.*;
import static org.hamcrest.Matchers.lessThan;

public class GeoApi {

    //builder pattern
    private GeoApi(){
    }
    private HashMap<String, String> params = new HashMap<>();

    public  static  class ApiBuilder{
        GeoApi geoApi;

        private ApiBuilder(GeoApi gcApi) { geoApi = gcApi; }

        public ApiBuilder geocode(String geocode){
            geoApi.params.put(PARAM_GEOCODE, geocode) ;
            return this;
        }

        public ApiBuilder format(GeoConstants.Formats format){
            geoApi.params.put(PARAM_FORMAT, format.formatCode);
            return  this;
        }
        public Response callApi() {
            return RestAssured.with()
                    .queryParams(geoApi.params)
                    .log().all()
                    .get(GEO_API_URI).prettyPeek();
        }
    }
        public static ApiBuilder with(){
        GeoApi api = new GeoApi();
        return new ApiBuilder(api);

}
    //get ready Speller answers list form api response


        public static List <GeoAnswer> getGeoAnswers(Response response){
        return new Gson().fromJson(response.asString(), new TypeToken<List<GeoAnswer>>() {}.getType());
    }

    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.XML)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(GEO_API_URI)
                .build();
    }

}
