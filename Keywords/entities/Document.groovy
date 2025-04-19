package entities

import screens.PDFSignScreen

class Document {
    String title
    String mainFileName
    String subFileName
    PDFSignScreen.Priority priority
    String time
    String description
    String assigner
    String cc
    String opinion

    private Document() {}

    String getTitle() {
        return title
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

    String getAssigner() {
        return assigner
    }

    String getCc() {
        return cc
    }

    String getOpinion() {
        return opinion
    }

    static class Builder {
        private String title
        private String mainFileName
        private String subFileName
        private PDFSignScreen.Priority priority
        private String time
        private String description
        private String assigner
        private String cc
        private String opinion

        Builder title(String title) {
            this.title = title
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

        Builder assigner(String assigner) {
            this.assigner = assigner
            return this
        }

        Builder cc(String cc) {
            this.cc = cc
            return this
        }

        Builder opinion(String opinion) {
            this.opinion = opinion
            return this
        }

        Document build() {
            Document document = new Document()
            document.title = this.title
            document.mainFileName = this.mainFileName
            document.subFileName = this.subFileName
            document.priority = this.priority
            document.time = this.time
            document.description = this.description
            document.assigner = this.assigner
            document.cc = this.cc
            document.opinion = this.opinion
            return document
        }
    }

    static Builder builder() {
        return new Builder()
    }
} 

