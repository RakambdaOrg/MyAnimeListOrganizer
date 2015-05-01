package mrcraftcod.myanimelistorganizer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import org.apache.http.client.utils.URIBuilder;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLHandler
{
	private static final int TIMEOUT = 30000;
	private static final String USER_AGENT_KEY = "User-Agent";
	private static final String USER_AGENT = "MyAnimeListOrganizer/" + Main.VERSION;
	private static final String CHARSET_TYPE_KEY = "charset";
	private static final String CHARSET_TYPE = "utf-8";
	private static final String CONTENT_TYPE_KEY = "Content-Type";
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String LANGUAGE_TYPE_KEY = "Accept-Language";
	private static final String LANGUAGE_TYPE = "fr-FR";


	public static String getAsString(URL url, Map<String, String> headers) throws UnirestException, URISyntaxException
	{
		return getAsString(url, headers, null);
	}

	public static String getAsString(URL url, Map<String, String> headers, Map<String, String> params) throws UnirestException, URISyntaxException
	{
		GetRequest req = getRequest(url, headers, params);
		HttpResponse<String> response = req.asString();
		return response.getBody();
	}

	public static String[] getAsStringArray(URL url, Map<String, String> headers) throws UnirestException, URISyntaxException
	{
		return getAsString(url, headers).split("\n");
	}

	public static int getStatus(URL url, Map<String, String> headers) throws UnirestException, URISyntaxException
	{
		return getStatus(url, headers, null);
	}

	public static int getStatus(URL url, Map<String, String> headers, Map<String, String> params) throws UnirestException, URISyntaxException
	{
		GetRequest request = getRequest(url, headers, params);
		return request.asBinary().getStatus();
	}

	private static GetRequest getRequest(URL url, Map<String, String> headers, Map<String, String> params) throws URISyntaxException
	{
		Unirest.clearDefaultHeaders();
		Unirest.setTimeouts(TIMEOUT, TIMEOUT);
		Unirest.setDefaultHeader(USER_AGENT_KEY, USER_AGENT);
		URIBuilder uriBuilder = new URIBuilder(url.toURI());
		if(params != null)
			for(String key : params.keySet())
				uriBuilder.addParameter(key, params.get(key));
		System.out.println(uriBuilder.build().toString());
		return Unirest.get(uriBuilder.build().toString()).headers(headers).header(LANGUAGE_TYPE_KEY, LANGUAGE_TYPE).header(CONTENT_TYPE_KEY, CONTENT_TYPE).header(CHARSET_TYPE_KEY, CHARSET_TYPE).header(USER_AGENT_KEY, USER_AGENT);
	}

	private static MultipartBody postRequest(URL url, Map<String, String> headers, Map<String, String> params, String data) throws URISyntaxException
	{
		Unirest.clearDefaultHeaders();
		Unirest.setTimeouts(TIMEOUT, TIMEOUT);
		Unirest.setDefaultHeader(USER_AGENT_KEY, USER_AGENT);
		URIBuilder uriBuilder = new URIBuilder(url.toURI());
		if(params != null)
			for(String key : params.keySet())
				uriBuilder.addParameter(key, params.get(key));
		System.out.println(uriBuilder.build().toString());
		HttpRequestWithBody request = Unirest.post(uriBuilder.build().toString()).headers(headers).header(LANGUAGE_TYPE_KEY, LANGUAGE_TYPE).header(CONTENT_TYPE_KEY, CONTENT_TYPE).header(CHARSET_TYPE_KEY, CHARSET_TYPE).header(USER_AGENT_KEY, USER_AGENT);
		return  data == null ? request.field("", "") : request.field("data", data);
	}

	public static String postAsString(URL url, HashMap<String, String> headers, String data) throws URISyntaxException, UnirestException
	{
		MultipartBody request = postRequest(url, headers, null, data);
		return request.asString().getBody();
	}

	public static String postAsString(URL url, HashMap<String, String> headers) throws URISyntaxException, UnirestException
	{
		return postAsString(url, headers, null);
	}

	public static int postCode(URL url, HashMap<String, String> headers, String data) throws URISyntaxException, UnirestException
	{
		MultipartBody request = postRequest(url, headers, null, data);
		return request.asString().getStatus();
	}

	public static int postCode(URL url, HashMap<String, String> headers) throws URISyntaxException, UnirestException
	{
		return postCode(url, headers, null);
	}
}