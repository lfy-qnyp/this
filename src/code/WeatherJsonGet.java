/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;


public class WeatherJsonGet {

	public static String getWeatherJson(String wid) {

		String url = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
		String result = WeatherJsonGet.getHttpResponse(url);
		JSONArray jsonArray=null;
		
		try {
			jsonArray=new JSONArray(result);
			System.out.println(jsonArray.length());
			System.out.println(jsonArray);
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return result;
	}

	public static void main(String[] args) {
		/*boolean result = false;
		try {
			String url = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
			String json = WeatherJsonGet.getHttpResponse(url);

			System.out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		String url = "http://forecast.weather.com.cn/town/api/v1/sk?lat=32.39&lng=119.42";
		
		String result="[";
		result=result+WeatherJsonGet.getHttpResponse(url)+"]";
		
		
		/*String result = WeatherJsonGet.getHttpResponse(url);*/
		System.out.println(result);
		
		JSONArray jsonArray1=new JSONArray(result);
		System.out.println(jsonArray1.getJSONObject(0).get("weather").toString());
		
		
		/*System.out.println(jsonArray1.get(3));*/
		
		/*String cityName="yangzhou";
		getWeatherJson(cityName);*/
	}

	public static String getHttpResponse(String allConfigUrl) {
		BufferedReader in = null;
		StringBuffer result = null;
		try {

			URI uri = new URI(allConfigUrl);
			URL url = uri.toURL();
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");

			connection.connect();

			result = new StringBuffer();
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}

			return result.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;

	}
}
