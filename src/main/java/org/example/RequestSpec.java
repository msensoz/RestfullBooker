package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

   RequestSpecification requestSpecification;

    public RequestSpec(String baseUrl) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }



   public RequestSpecification getRequestSpec()
   {
       return requestSpecification;
   }

}
