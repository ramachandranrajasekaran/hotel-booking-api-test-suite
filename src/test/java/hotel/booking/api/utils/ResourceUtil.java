package hotel.booking.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ResourceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

    /**
     * This method reads the test.properties file from resources directory.
     * 
     * @return the properties object
     */
    public static Properties getProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("test.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Test properties read successfully");
        return properties;
    }

    /**
     * This method reads the json file from resources directory
     * 
     * @param requestFileName the file to read from resource directory
     * @return the string formatted json content of the read file 
     */
    public static String getRequest(String requestFileName) {
        String jsonString = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("requests/" + requestFileName)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(resourceStream);
            jsonString = mapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Json file {} read successfully", requestFileName);
        return jsonString;
    }

    /**
     * This method converts the Pojo class to json string
     * 
     * @param object the Pojo class to convert to json string
     * @return String the json string after conversion
     */
    public static String getJsonString(Object object) {
    	JsonMapper mapper = new JsonMapper();
    	String requestBody = null;
    	try {
    		requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return requestBody;
    }
}
