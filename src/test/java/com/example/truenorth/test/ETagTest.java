package com.example.truenorth.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ETagTest.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class ETagTest {

	@Value("${com.truenorht.uri}")
	private String uriOfResource;

	@Test
	public void givenResourceExists_whenRetrievingResource_thenEtagIsAlsoReturned() {
		Response findOneResponse = RestAssured.given().header("Accept", "application/json").get(uriOfResource);
		assertNotNull(findOneResponse.getHeader("ETag"));
	}
	
	@Test
	public void givenResourceWasRetrieved_whenRetrievingAgainWithEtag_thenNotModifiedReturned() {
	    // Given
	    Response findOneResponse = RestAssured.given().
	      header("Accept", "application/json").get(uriOfResource);
	    String etagValue = findOneResponse.getHeader(HttpHeaders.ETAG);
	 
	    // When
	    Response secondFindOneResponse= RestAssured.given().
	      header("Accept", "application/json").headers("If-None-Match", etagValue)
	      .get(uriOfResource);
	 
	    // Then
	    assertTrue(secondFindOneResponse.getStatusCode() == 304);
	}
	
	@Test
	public void putResourceWasRetrieved_whenRetrievingAgainWithEtag_thenNotModifiedReturned() {
	    Response findOneResponse = RestAssured.given()
	    		.contentType("application/json")
	    		.body("{\"dateStart\": \"2018-02-23\",\"dateEnd\": \"2018-02-25\",\"email\": \"fafafa@fer.com\",\"fullName\": \"faaf\"}")
	    		.header("Accept", "application/json").put("http://localhost:8080/api/reservation/26");
	    String etagValue = findOneResponse.getHeader(HttpHeaders.ETAG);
	 
	    Response secondFindOneResponse = RestAssured.given().contentType("application/json")
	    		.body("{\"dateStart\": \"2018-02-23\",\"dateEnd\": \"2018-02-25\",\"email\": \"fafafa@fer.com\",\"fullName\": \"faaf\"}")
	      		.header("Accept", "application/json").headers("If-None-Match", etagValue)
	      		.put(uriOfResource);
	 
	    // Then
	    assertTrue(secondFindOneResponse.getStatusCode() == 412);
	}
	
}

	
