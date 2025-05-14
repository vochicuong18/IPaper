package utilities

import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils
import org.json.JSONObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

import entities.Document
import entities.User
import screens.PDFSignScreen

public class DataTest {
	// Constants
	public static final APP = [
		Android: "com.hdbank.ipaper",
		iOS    : "com.hdbank.ipaper"
	]

	private static final String USER_JSON_PATH = System.getProperty("user.dir") + "/user.json"
	private static final String DOCUMENT_JSON_PATH = System.getProperty("user.dir") + "/document.json"

	// Cached JSON objects
	private static JSONObject userData
	private static JSONObject documentData

	/**
	 * Initialize data from JSON files
	 * @return boolean Initialization status (true if successful)
	 */
	static boolean init() {
		try {
			// Read user data
			File userFile = new File(USER_JSON_PATH)
			String userContent = FileUtils.readFileToString(userFile, StandardCharsets.UTF_8)
			userData = new JSONObject(userContent)

			// Read document data
			File documentFile = new File(DOCUMENT_JSON_PATH)
			String documentContent = FileUtils.readFileToString(documentFile, StandardCharsets.UTF_8)
			documentData = new JSONObject(documentContent)

			return true
		} catch (Exception e) {
			KeywordUtil.logInfo("Error initializing data: ${e.message}")
			return false
		}
	}

	/**
	 * Get test document data with randomized values
	 * @return Document New document object
	 */
	static Document getDocumentTest() {
		// Check if initialized
		if (documentData == null) {
			init()
		}

		// Create tomorrow's date
		String tomorrow = getNextDayFormatted()

		// Choose random priority
		def randomPriority = getRandomPriority()

		// Get document information from JSON
		JSONObject docJson = documentData.getJSONObject("document")

		return Document.builder()
				.title("Trình ký + ${System.currentTimeMillis()}")
				.mainFileName(docJson.getString("mainFile"))
				.subFileName(docJson.getString("subfile"))
				.priority(randomPriority)
				.time(tomorrow)
				.description("Mobile automation test description")
				.assigner(docJson.getString("assigner"))
				.cc(docJson.getString("cc"))
				.comment("Mobile automation test comment")
				.build()
	}

	/**
	 * Create a test document with custom parameters
	 * @param sender The sender (required)
	 * @param assigner The assigner (required)
	 * @param cc The CC recipient (optional)
	 * @param mainFile The main file (required)
	 * @param subFileName The sub file (optional)
	 * @return Document New document object
	 */
	static Document createDocumentTest(User sender, User assigner, User cc = null, String mainFile, String subFileName = null) {
		return Document.builder()
				.title("Trình ký ${System.currentTimeMillis()}")
				.sender(sender)
				.mainFileName(mainFile)
				.subFileName(subFileName)
				.priority(getRandomPriority())
				.time(getNextDayFormatted())
				.description(Utilities.testCaseId)
				.assigner(assigner)
				.cc(cc)
				.comment("Mobile automation test comment")
				.build()
	}

	/**
	 * Get a User object based on key in JSON file
	 * @param userKey The key of the user object in the JSON file
	 * @return User The corresponding user object
	 */
	private static User getUserFromKey(String userKey) {
		if (userData == null) {
			init()
		}

		try {
			JSONObject userJson = userData.getJSONObject(userKey)
			return User.builder()
					.userName(userJson.getString("userName"))
					.name(userJson.getString("name"))
					.password(userJson.getString("password"))
					.email(userJson.getString("email"))
					.role(userJson.optString("role", ""))
					.department(userJson.optString("department", ""))
					.build()
		} catch (Exception e) {
			KeywordUtil.logInfo("Error getting user ${userKey}: ${e.message}")
			return null
		}
	}

	/**
	 * Get A3NV user from test data
	 * @return User A3NV user object
	 */
	static User getUserA3NVTest() {
		return getUserFromKey("userA3NV")
	}

	/**
	 * Get A4NV user from test data
	 * @return User A4NV user object
	 */
	static User getUserA4NVTest() {
		return getUserFromKey("userA4NV")
	}

	/**
	 * Get A5NV user from test data
	 * @return User A5NV user object
	 */
	static User getUserA5NVTest() {
		return getUserFromKey("userA5NV")
	}

	/**
	 * Get test1 user from test data
	 * @return User test1 user object
	 */
	static User getUserTest1() {
		return getUserFromKey("user1")
	}

	/**
	 * Get a specific user by key
	 * @param userKey The user key in the JSON file
	 * @return User The corresponding user object
	 */
	static User getUserTest(String userKey) {
		return getUserFromKey(userKey)
	}

	/**
	 * Get the next day formatted as d/M/yyyy
	 * @return String Formatted tomorrow's date
	 */
	private static String getNextDayFormatted() {
		Calendar calendar = Calendar.getInstance()
		calendar.add(Calendar.DATE, 1)
		return new SimpleDateFormat("d/M/yyyy").format(calendar.getTime())
	}

	/**
	 * Get a random priority level
	 * @return Priority Random priority level
	 */
	private static PDFSignScreen.Priority getRandomPriority() {
		def priorities = [
			PDFSignScreen.Priority.KHAN_CAP,
			PDFSignScreen.Priority.CAO,
			PDFSignScreen.Priority.BINH_THUONG
		]
		return priorities[new Random().nextInt(priorities.size())]
	}
}