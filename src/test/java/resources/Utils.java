package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public RequestSpecification req;
	private static String CONFIG_PROPERTIES_FILE_LOCATION ="src/test/java/resources/global.properties";
	
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
//				.addQueryParam("lat", latitude)
//				.addQueryParam("lon", longitude)
//				.addQueryParam("appid",appId)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		 return req;
		}
		return req;
		
	}
	
	/**
	 * Fetch the property mentioned in global properties file based on the key
	 * @param key
	 * @return String property value
	 */
	public static String getGlobalValue(String key)
	{
		Properties prop =new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(CONFIG_PROPERTIES_FILE_LOCATION);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);	
	}
	
	
	public String getJsonPath(Response response,String key)
	{
		String resp = response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
}

