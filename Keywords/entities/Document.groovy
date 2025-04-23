package entities

import screens.PDFSignScreen

class Document {
	String title
	DocumentStatus status
	String mainFileName
	String subFileName
	PDFSignScreen.Priority priority
	String time
	String description
	User sender
	User assigner
	User cc
	String comment

	private Document() {}

	String getTitle() {
		return title
	}

	DocumentStatus getStatus() {
		return status
	}

	String getMainFileName() {
		return mainFileName
	}

	String getSubFileName() {
		return subFileName
	}

	PDFSignScreen.Priority getPriority() {
		return priority
	}

	String getTime() {
		return time
	}

	String getDescription() {
		return description
	}

	User getSender() {
		return sender
	}

	User getAssigner() {
		return assigner
	}

	User getCc() {
		return cc
	}

	String getComment() {
		return comment
	}

	static class Builder {
		private String title
		private DocumentStatus status
		private String mainFileName
		private String subFileName
		private PDFSignScreen.Priority priority
		private String time
		private String description
		private User sender
		private User assigner
		private User cc
		private String comment

		Builder title(String title) {
			this.title = title
			return this
		}

		Builder status(DocumentStatus status) {
			this.status = status
			return this
		}

		Builder mainFileName(String mainFileName) {
			this.mainFileName = mainFileName
			return this
		}

		Builder subFileName(String subFileName) {
			this.subFileName = subFileName
			return this
		}

		Builder priority(PDFSignScreen.Priority priority) {
			this.priority = priority
			return this
		}

		Builder time(String time) {
			this.time = time
			return this
		}

		Builder description(String description) {
			this.description = description
			return this
		}

		Builder sender(User sender) {
			this.sender = sender
			return this
		}

		Builder assigner(User assigner) {
			this.assigner = assigner
			return this
		}

		Builder cc(User cc) {
			this.cc = cc
			return this
		}

		Builder comment(String comment) {
			this.comment = comment
			return this
		}

		Document build() {
			Document document = new Document()
			document.title = this.title
			document.status = this.status
			document.mainFileName = this.mainFileName
			document.subFileName = this.subFileName
			document.priority = this.priority
			document.time = this.time
			document.description = this.description
			document.sender = this.sender
			document.assigner = this.assigner
			document.cc = this.cc
			document.comment = this.comment
			return document
		}
	}

	static Builder builder() {
		return new Builder()
	}
}