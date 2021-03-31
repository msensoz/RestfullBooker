package org.example;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.BaseTestService;

import java.io.IOException;

import static org.testng.Assert.assertNotNull;


public class APITests extends BaseTestService {



    public String shouldCreateToken() throws IOException {

        Response response= otherService.creteAuth(ResponseSpec.checkStatusOK());

        String token=response.getBody().jsonPath().getString("token");
        assertNotNull(token);
        return token;

    }


    public String shouldCreateBooking() throws IOException {
        Response response=otherService.createBooking(ResponseSpec.checkStatusOK());

        String id=response.getBody().jsonPath().getString("bookingid");
        assertNotNull(id);
        System.out.println(id);
        return id;


    }

    public void shouldGetBookingById(String id)
    {
        Response response=otherService.getBookingById(ResponseSpec.checkStatusOK(),id);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    public void shouldUpdateBooking(String id,String token) throws IOException {
        Response response=otherService.updateBooking(ResponseSpec.checkStatusOK(),id,token);
        String name=response.getBody().jsonPath().getString("firstname");
        Assert.assertEquals(name,"Mine");
    }


    @Test
    public void APIJourney() throws IOException {
     String token=shouldCreateToken();
     String bookingid=shouldCreateBooking();
     shouldGetBookingById(bookingid);
     shouldUpdateBooking(bookingid,token);
     shouldGetBookingById(bookingid);


 }
}
