package hotel.booking.api.utils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestRequestHelper {

    /**
     * This method send GET REST request without query params
     * 
     * @param url the URL to send REST request to
     * @param bearerToken Authorization Bearer Token for REST request 
     * @return the REST response
     */
    public static Response getRequest(String url, String bearerToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .get(url);
    }
    
    /**
     * This method send GET REST request without query params and auth
     * 
     * @param url the URL to send REST request to 
     * @return the REST response
     */
    public static Response getRequest(String url) {
        return given()
                .contentType(ContentType.JSON)
                .get(url);
    }

    /**
     * This method send GET REST request with query params
     * 
     * @param url the URL to send REST request to
     * @param bearerToken Authorization Bearer Token for REST request
     * @param param the rest request query param
     * @param value the rest request query param value
     * @return the REST response
     */
    public static Response getRequest(String url, String bearerToken, String param, String value) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .queryParam(param, value)
                .get(url);
    }
    
    /**
     * This method send GET REST request with multiple query params
     * @param url the URL to send REST request to
     * @param queryMap multiple query params
     * @return the REST response
     */
    public static Response getRequest(String url, Map<String, Object> queryMap) {
        return given()
                .contentType(ContentType.JSON)
                .queryParams(queryMap)
                .get(url);
    }

    /**
     * This method send POST REST request
     * @param url the URL to send REST request to
     * @param body the body of the POST request
     * @return the REST response
     */
    public static Response postRequest(String url, String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(url);
    }
    
    /**
     * This method send POST REST request
     * @param url the URL to send REST request to
     * @param body the body of the POST request
     * @param bearerToken the bearer token to authorize the REST request
     * @return the REST response
     */
    public static Response postRequest(String url, String body, String bearerToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + bearerToken)
                .body(body)
                .post(url);
    }
    
    /**
     * This method send PATCH REST request
     * @param url the URL to send REST request to
     * @param body the body of the PATCH requst
     * @param bearerToken the bearer token to authorize the REST request
     * @return Response the REST response
     */
    public static Response patchRequest(String url, String body, String bearerToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + bearerToken)
                .body(body)
                .patch(url);
    }
    
    /**
     * This method send DELETE REST request
     * 
     * @param url the URL to send REST request to
     * @param bearerToken the bearer token to authorize the REST request
     * @return Response the REST response
     */
    public static Response deleteRequest(String url, String bearerToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + bearerToken)
                .delete(url);
    }

}
