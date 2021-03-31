package service;

import com.google.common.io.Resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.example.RequestSpec;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class OtherService extends RequestSpec {


    public OtherService(){
        super("https://restful-booker.herokuapp.com");
    }

    public Response creteAuth(ResponseSpecification responseSpecification) throws IOException {
        URL file= Resources.getResource("CreateToken.json");
        String myJson=Resources.toString(file, Charset.defaultCharset());
        JSONObject json=new JSONObject(myJson);

        return RestAssured.given()
                .spec(super.getRequestSpec())
                .body(json.toString())
                .when()
                .post("/auth")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public Response createBooking(ResponseSpecification responseSpecification) throws IOException {
        URL file= Resources.getResource("CreateBooking.json");
        String myJson=Resources.toString(file, Charset.defaultCharset());
        JSONObject json=new JSONObject(myJson);

        return RestAssured.given()
                .spec(super.getRequestSpec())
                .body(json.toString())
                .when()
                .post("/booking")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public Response getBookingById(ResponseSpecification responseSpecification,String bookingid)
    {
        return RestAssured.given()
                .spec(super.getRequestSpec())
                .when()
                .get("/booking/{bookingid}",bookingid)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public Response updateBooking(ResponseSpecification responseSpecification,String bookingid,String token) throws IOException {
        URL file= Resources.getResource("UpdateBooking.json");
        String myJson=Resources.toString(file, Charset.defaultCharset());
        JSONObject json=new JSONObject(myJson);

        return RestAssured.given()
                .spec(super.getRequestSpec())
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(json.toString())
                .when()
                .put("/booking/{bookingid}",bookingid)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }
}
