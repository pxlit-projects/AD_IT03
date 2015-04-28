//package be.pxl.data;
//
//import java.io.*;
//
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.*;
//// Problemen met HttpClient library.. die is wss te oud er moet een nieuwe library zijn.
//
//public class parserFromUrl {
//
//	public static Data getData(String url){
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.addHeader("accept","application/json");
//		HttpResponse response = httpClient.execute(httpGet);
//		String data = readData(response);
//		Gson gson = new Gson();
//		return gson.fromJson(data, Data.class);
//		
//	}
//	
//	public static String readData(HttpResponse httpResponse){
//		BufferedReader bufferedReader = null; 
//		try{
//			bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//			StringBuffer buffer = new StringBuffer();
//			char[] dataLength = new char [1024];
//			int read;
//			while((read=bufferedReader.read(dataLength))!=-1){
//				buffer.append(dataLength,0,read);
//			}
//			return buffer.toString();
//		}catch (Exception e){
//			throw e;
//		}finally {
//			if(bufferedReader!=null)
//				bufferedReader.close();
//		}
//	}
//	
//}
