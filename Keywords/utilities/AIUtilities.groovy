package utilities

import static org.assertj.core.api.InstanceOfAssertFactories.STRING

import java.nio.file.Files

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

public class AIUtilities {

    static String API_KEY = "AIzaSyBdH5XPPt8hsRl37iWpdIxRe47N4mgNz7g"
    static String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${API_KEY}"

    static String analyzeImage(String imagePath, String promptText) {
        def base64Image = encodeImageToBase64(imagePath)
        def jsonBody = createRequestBody(promptText, base64Image)
        def response = sendRequestToAPI(jsonBody)
        def result = extractResultFromResponse(response)

        // Log thông tin token sử dụng
        logTokenUsage(response)

        return result
    }

    // Chuyển đổi hình ảnh thành Base64
    static String encodeImageToBase64(String imagePath) {
        byte[] imageBytes = Files.readAllBytes(new File(imagePath).toPath())
        return Base64.encoder.encodeToString(imageBytes)
    }

    static String createRequestBody(String promptText, String base64Image) {
        def requestBody = [
            contents: [
                [
                    parts: [
                        [text: promptText],
                        [
                            inline_data: [
                                mime_type: "image/png",
                                data: base64Image
                            ]
                        ]
                    ]
                ]
            ]
        ]
        return JsonOutput.toJson(requestBody)
    }

    static def sendRequestToAPI(String jsonBody) {
        RequestObject request = new RequestObject("GeminiRequest")
        request.setRestUrl(GEMINI_ENDPOINT)
        request.setRestRequestMethod("POST")

        List<TestObjectProperty> headers = new ArrayList<>()
        headers.add(new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"))
        request.setHttpHeaderProperties(headers)

        request.setBodyContent(new HttpTextBodyContent(jsonBody, "UTF-8", "application/json"))
        return WS.sendRequest(request)
    }

    // Convert kết quả từ response
    static String extractResultFromResponse(def response) {
        def json = new JsonSlurper().parseText(response.getResponseText())
		println(json.toString())
		String result = json?.candidates?.getAt(0)?.content?.parts?.getAt(0)?.text ?: "Không có phản hồi từ AI"
		Utilities.logInfo("🤖 Gemini response: ${result}")
        return result
    }

    // log số token sử dụng
    static void logTokenUsage(def response) {
        def json = new JsonSlurper().parseText(response.getResponseText())
        def promptTokens = json?.usageMetadata?.promptTokenCount ?: 0
        def responseTokens = json?.usageMetadata?.candidatesTokenCount ?: 0
        def totalTokens = json?.usageMetadata?.totalTokenCount ?: 0

        Utilities.logInfo(" Token usage → Prompt: $promptTokens | Response: $responseTokens | Total: $totalTokens")
    }
}
