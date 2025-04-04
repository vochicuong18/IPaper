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


	static User getUserTest() {
		jsonData = user.getJSONObject("user")
		return User.builder()
            .userName(jsonData.getString("userName"))
            .password(jsonData.getString("password"))
            .email(jsonData.getString("email"))
            .phoneNumber(jsonData.getString("phoneNumber"))
            .build()
	}
}
