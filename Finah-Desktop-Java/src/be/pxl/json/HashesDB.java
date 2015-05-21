package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import be.pxl.objects.Hashes;
import be.pxl.settings.SettingClass;

public class HashesDB {
	
	List<Hashes> hashesList;
	private String URLHASHES = new SettingClass().getSiteUrl()+"api/hashes";

	
	public List<Hashes> readHashes() {

		String json;
		try {
			json = new ReadUrl().read(URLHASHES);

			Gson gson = new Gson();

			hashesList = Arrays.asList(gson.fromJson(json, Hashes[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashesList;

	}
	
	public String getHashById(int id) {
		hashesList = readHashes();
		String hashes = "";
		for (Hashes hashes2 : hashesList) {
			if (hashes2.getId() == id) {
				hashes = hashes2.getHash();
			}
		}
		return hashes;
	}
	
	public boolean addHash(String hash) {
		Hashes hashes = new Hashes(hash);
		new WriteToWeb().Add(hashes, URLHASHES);
		return true;
	}

	
}
