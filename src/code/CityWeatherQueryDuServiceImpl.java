package code;

/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 娉ㄦ剰锛氭湰鍐呭浠呴檺浜庢繁鍦冲競鎹烽『閲戠鐮斿彂鏈夐檺鍏徃鍐呴儴浼犻槄锛岀姝㈠娉勪互鍙婄敤浜庡叾浠栫殑鍟嗕coderk.dubbo.service.impl;
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;



/** 
 * 
 * @Package: com.jst.park.dubbo.service.impl  
 * @ClassName: ChargeScanDuServiceImpl 
 * @Description: TODO
 *
 * @author: Administrator 
 * @date: 2018骞?1鏈?18鏃? 涓嬪崍2:28:12 
 * @version V1.0 
 * 
 * 缂栧啓澶╂皵鏌ヨ鐨勪笟鍔℃祦绋嬩唬鐮?
 */
public class CityWeatherQueryDuServiceImpl {
	
	
	public static CityQueryWeatherInfoBean cityQueryWeatherInfoBean=new CityQueryWeatherInfoBean();
	
	public void setWeatherInfoBean(){
		String urlWeather="http://forecast.weather.com.cn/town/api/v1/sk?lat=32.39&lng=119.42";
		String resultWeather = "["+CityWeatherQueryDuServiceImpl.getHttpResponse(urlWeather)+"]";

		String urlAqi = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
		String resultAqi = "["+CityWeatherQueryDuServiceImpl.getHttpResponse(urlAqi)+"]";
		
		JSONArray jsonArray=null;
		
		try {
			jsonArray=new JSONArray(resultWeather);
			cityQueryWeatherInfoBean.setWS(jsonArray.getJSONObject(0).get("WS").toString());
			cityQueryWeatherInfoBean.setWendu(jsonArray.getJSONObject(0).get("temp").toString());
			cityQueryWeatherInfoBean.setWeather(jsonArray.getJSONObject(0).get("weather").toString());
			cityQueryWeatherInfoBean.setWD(jsonArray.getJSONObject(0).get("WD").toString());
			cityQueryWeatherInfoBean.setWeathercode(jsonArray.getJSONObject(0).get("weathercode").toString());
			cityQueryWeatherInfoBean.setHumidity(jsonArray.getJSONObject(0).get("humidity").toString());
			
			jsonArray=new JSONArray(resultAqi);
			
			cityQueryWeatherInfoBean.setAqi(jsonArray.getJSONObject(0).get("aqi").toString());
			
			int aqiInt=Integer.parseInt(jsonArray.getJSONObject(0).get("aqi").toString());
			
			if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("一级，优，可多参加户外活动呼吸新鲜空气");
			else if(aqiInt>=51 && aqiInt<=101)
				cityQueryWeatherInfoBean.setAqidesc("二级，良，除少数对某些污染物特别容易过敏的人群外，其他人群可以正常进行室外活动");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("三级，轻度污染，敏感人群需减少体力消耗较大的户外活动");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("四级，中度污染，敏感人群应尽量减少外出，一般人群适当减少户外运动");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("五级，重度污染，敏感人群应停止户外运动，一般人群尽量减少户外运动");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("六级，严重污染，除有特殊需要的人群外，尽量不要留在室外");
			else
				cityQueryWeatherInfoBean.setAqidesc("数据错误！");
			
			
			//cityQueryWeatherInfoBean.setAqidesc(jsonArray.getJSONObject(0).get("level").toString());
			
			
			//CityQueryWeatherInfoBean 
			
			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		CityWeatherQueryDuServiceImpl cityWeatherQueryDuServiceImpl=new CityWeatherQueryDuServiceImpl();
		cityWeatherQueryDuServiceImpl.setWeatherInfoBean();
		System.out.println(cityQueryWeatherInfoBean.getAqi()+cityQueryWeatherInfoBean.getWeather());
	}
	
	
	
	public static String getWeatherJson(String wid) {
		
		String urlWeather="http://forecast.weather.com.cn/town/api/v1/sk?lat=32.39&lng=119.42";
		String resultWeather = "["+CityWeatherQueryDuServiceImpl.getHttpResponse(urlWeather)+"]";

		String urlAqi = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
		String resultAqi = "["+CityWeatherQueryDuServiceImpl.getHttpResponse(urlAqi)+"]";
		
		
		
		
		JSONArray jsonArray=null;
		
		try {
			jsonArray=new JSONArray(resultWeather);
			
			//CityQueryWeatherInfoBean 
			
			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return resultWeather;
	}

	/*public static void main(String[] args) {
		boolean result = false;
		try {
			String url = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
			String json = WeatherJsonGet.getHttpResponse(url);

			System.out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String url = "http://api.help.bj.cn/apis/aqi3/?id=yangzhou";
		
		String result="[";
		result=result+CityWeatherQueryDuServiceImpl.getHttpResponse(url)+"]";
		
		
		String result = WeatherJsonGet.getHttpResponse(url);
		System.out.println(result);
		
		JSONArray jsonArray1=null;
		try {
			jsonArray1 = new JSONArray(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(jsonArray1.getJSONObject(0).get("aqi").toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(jsonArray1.get(3));
		
		String cityName="yangzhou";
		getWeatherJson(cityName);
	}*/

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
			// 璇诲彇URL鐨勫搷搴?
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
