package utilities

import com.kms.katalon.core.util.KeywordUtil

import java.util.logging.Logger

import com.kms.katalon.core.logging.KeywordLogger;


public class Utilities {
	private static final KeywordLogger logger = KeywordLogger.getInstance(KeywordUtil.class);
	
	def getOS() {
		def osName = System.getProperty("os.name").toLowerCase()
		if (osName.contains("win")) {
			return "Windows"
		}
		else if (osName.contains("mac")) {
			return "Mac"
		}
		else {
			return "Other"
		}
	}

	def static logInfo(String text) {
		KeywordUtil.logInfo(text)
	}
	
	def static logPass(String text) {
		logger.logPassed(text)
	}
	
	def static logFailed(String text) {
		logger.logFailed(text)}
	

	def runCommand(String command) {
		logInfo(command)

		def process
		logInfo(getOS())
		if (getOS() == 'Windows') {
			process = ["cmd", "/c", command].execute()
		}
		else {
			process =  ["zsh", "-c", command].execute()
		}


		def reader = new BufferedReader(new InputStreamReader(process.getInputStream()))

		StringBuilder output = new StringBuilder()
		String line

		while ((line = reader.readLine()) != null) {
			output.append(line).append("\n")
		}

		process.waitFor() // Wait for the process to finish
		logInfo("Output: ${output.toString()}")
		return output.toString()
	}
}
