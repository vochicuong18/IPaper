package utilities

import javax.persistence.metamodel.StaticMetamodel

import org.json.JSONObject
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;

import entities.User

public class DataTest {
	static File file
	static JSONObject jsonData, user

	public static final APP = [
		Android: "/Users/cuongvo/hdb_katalon/ipaper.apk",
		iOS    : "com.hdbank.ipaper"
	]

	static init() {
		file = new File(System.getProperty("user.dir") + "/user.json")
		String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		user = new JSONObject(content)
		println "a"
	}


	static User getUserA3NVTest() {
		jsonData = user.getJSONObject("userA3NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.phoneNumber(jsonData.getString("phoneNumber"))
				.build()
	}

	static User getUserA4NVTest() {
		jsonData = user.getJSONObject("userA4NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.phoneNumber(jsonData.getString("phoneNumber"))
				.build()
	}


	static User getUserA5NVTest() {
		jsonData = user.getJSONObject("userA5NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.phoneNumber(jsonData.getString("phoneNumber"))
				.build()
	}

	static User getUserTest1() {
		jsonData = user.getJSONObject("user1")
		return User.builder()
				.userName(jsonData.getString("userName"))
				.name(jsonData.getString("name"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.phoneNumber(jsonData.getString("phoneNumber"))
				.build()
	}
}
