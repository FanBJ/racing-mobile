package com.hy.utils.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpCommonUtil {

	public static String post(Map<String,String> params,String url){
		
		CloseableHttpClient httpclient = null;
		
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			if(params!=null){
				for(Map.Entry<String, String> entry : params.entrySet()){
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			if(code==200){
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally{
			if(httpclient!=null){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static String post(String url){
		return post(null, url);
	}
	
	
	public static String postByBody(String param,String url){
		
		CloseableHttpClient httpclient = null;
		
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			
			httpPost.setEntity(new StringEntity(param, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			if(code==200){
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally{
			if(httpclient!=null){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String get(String url){
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			if(code==200){
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally{
			if(httpclient!=null){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		HashMap<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("tableName", "db.area");
		hashMap.put("fieldName", "房山区");
		System.out.println(post(hashMap, "http://localhost:8080/cache/getDictInfoByTableNameAndFieldName"));
	}
	public static String  rootPath() {
		HashMap<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("tableName", "sys.path");
		hashMap.put("fieldName", "web_visit_root");
		post(hashMap, "http://localhost:8080/cache/getDictInfoByTableNameAndFieldName");
		return null;
	}
}
