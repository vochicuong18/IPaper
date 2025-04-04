package utilities

import com.kms.katalon.core.util.KeywordUtil



public class Utilities {
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

	def static log(String text) {
		KeywordUtil.logInfo(text)
	}

	def runCommand(String command) {
		log(command)

		def process
		log(getOS())
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
		log("Output: ${output.toString()}")
		return output.toString()
	}
}
