package code;

/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商�coderk.dubbo.service.impl;
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
 * @date: 2018�?1�?18�? 下午2:28:12 
 * @version V1.0 
 * 
 * 编写天气查询的业务流程代�?
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
				cityQueryWeatherInfoBean.setAqidesc("һ�����ţ��ɶ�μӻ����������ʿ���");
			else if(aqiInt>=51 && aqiInt<=101)
				cityQueryWeatherInfoBean.setAqidesc("������������������ĳЩ��Ⱦ���ر����׹�������Ⱥ�⣬������Ⱥ����������������");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("�����������Ⱦ��������Ⱥ������������Ľϴ�Ļ���");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("�ļ����ж���Ⱦ��������ȺӦ�������������һ����Ⱥ�ʵ����ٻ����˶�");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("�弶���ض���Ⱦ��������ȺӦֹͣ�����˶���һ����Ⱥ�������ٻ����˶�");
			else if(aqiInt>=0 && aqiInt<=50)
				cityQueryWeatherInfoBean.setAqidesc("������������Ⱦ������������Ҫ����Ⱥ�⣬������Ҫ��������");
			else
				cityQueryWeatherInfoBean.setAqidesc("���ݴ���");
			
			
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
			// 读取URL的响�?
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
