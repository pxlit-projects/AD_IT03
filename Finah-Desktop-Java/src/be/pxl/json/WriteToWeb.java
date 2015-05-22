package be.pxl.json;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class WriteToWeb {

	public WriteToWeb() {
		// TODO Auto-generated constructor stub
	}

	public <T> int Add(T type, String url) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setHeader("content-type", "application/json");

		HttpResponse resp;
		int resultCode = 0;
		try {
			StringEntity entity = new StringEntity(convertToJSON(type));
			post.setEntity(entity);

			resp = httpClient.execute(post);
			resultCode = resp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultCode;
	}

	public <T> int Update(T type, String url, int id) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url + id);
		put.setHeader("content-type", "application/json");
		HttpResponse resp;
		int resultCode = 0;
		try {
			StringEntity entity = new StringEntity(convertToJSON(type));
			put.setEntity(entity);
			resp = httpClient.execute(put);
			resultCode = resp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultCode;
	}

	public void delete(List<Integer> ids, String url) {
		for (int i = 0; i < ids.size(); i++) {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpDelete delete = new HttpDelete(url + ids.get(i));
			delete.setHeader("content-type", "application/json");
			HttpResponse resp;
			int resultCode = 0;
			try {
				resp = httpClient.execute(delete);
				resultCode = resp.getStatusLine().getStatusCode();
				if (resultCode != 200)
					System.out.println(resp.getStatusLine().toString());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	private String convertToJSON(Object toConvert) {
		Gson gson = new Gson();
		String json = gson.toJson(toConvert);
		return json;
	}

}
