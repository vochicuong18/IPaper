package utilities

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils;
import org.json.JSONObject

import com.kms.katalon.core.configuration.RunConfiguration

import entities.Document
import entities.User
import screens.PDFSignScreen

public class DataTest {
	static File file
	static JSONObject jsonData, user, document

	public static final APP = [
		Android: "com.hdbank.ipaper",
		iOS    : "com.hdbank.ipaper"
	]

	static init() {
		file = new File(System.getProperty("user.dir") + "/user.json")
		String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		user = new JSONObject(content)

		file = new File(System.getProperty("user.dir") + "/document.json")
		String documentData = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		document = new JSONObject(documentData)
	}

	static Document getDocumentTest() {
		Calendar calendar = Calendar.getInstance()
		calendar.add(Calendar.DATE, 1)
		String tomorrow = new SimpleDateFormat("d/M/yyyy").format(calendar.getTime())
		def priorities = [
			PDFSignScreen.Priority.KHAN_CAP,
			PDFSignScreen.Priority.CAO,
			PDFSignScreen.Priority.BINH_THUONG
		]

		def randomPriority = priorities[new Random().nextInt(priorities.size())]
		jsonData = document.getJSONObject("document")

		return Document.builder()
				.title("Trình ký + ${System.currentTimeMillis()}")
				.mainFileName(jsonData.getString("mainFile"))
				.subFileName(jsonData.getString("subfile"))
				.priority(randomPriority)
				.time(tomorrow)
				.description("Mobile automation test description")
				.assigner(jsonData.getString("assigner"))
				.cc(jsonData.getString("cc"))
				.comment("Mobile automation test comment")
				.build()
	}

	/**	 
	 * @param sender (require)
	 * @param assigner (require)
	 * @param cc (optional).
	 * @param mainFile (require)
	 * @param subFileName (optional).
	 *
	 * @return Document The created Document object.
	 */
	static Document createDocumentTest(User sender, User assigner, User cc = null, String mainFile, String subFileName = null) {
		Calendar calendar = Calendar.getInstance()
		calendar.add(Calendar.DATE, 1)
		String tomorrow = new SimpleDateFormat("d/M/yyyy").format(calendar.getTime())

		def priorities = [
			PDFSignScreen.Priority.KHAN_CAP,
			PDFSignScreen.Priority.CAO,
			PDFSignScreen.Priority.BINH_THUONG
		]
		def randomPriority = priorities[new Random().nextInt(priorities.size())]

		return Document.builder()
				.title("Trình ký ${System.currentTimeMillis()}")
				.sender(sender)
				.mainFileName(mainFile)
				.subFileName(subFileName)
				.priority(randomPriority)
				.time(tomorrow)
				.description(Utilities.testCaseId)
				.assigner(assigner)
				.cc(cc)
				.comment("Mobile automation test comment")
				.build()
	}

	static User getUserA3NVTest() {
		jsonData = user.getJSONObject("userA3NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.build()
	}

	static User getUserA4NVTest() {
		jsonData = user.getJSONObject("userA4NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.build()
	}


	static User getUserA5NVTest() {
		jsonData = user.getJSONObject("userA5NV")
		return User.builder()
				.name(jsonData.getString("name"))
				.userName(jsonData.getString("userName"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.build()
	}

	static User getUserTest1() {
		jsonData = user.getJSONObject("user1")
		return User.builder()
				.userName(jsonData.getString("userName"))
				.name(jsonData.getString("name"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.role(jsonData.getString("role"))
				.department(jsonData.getString("department"))
				.build()
	}

	static User getUserTest(String autoUser) {
		jsonData = user.getJSONObject(autoUser)
		return User.builder()
				.userName(jsonData.getString("userName"))
				.name(jsonData.getString("name"))
				.password(jsonData.getString("password"))
				.email(jsonData.getString("email"))
				.role(jsonData.getString("role"))
				.department(jsonData.getString("department"))
				.build()
	}
}
