import entities.Document
import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import utilities.DataTest as DataTest
int index = 0

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()

List<String> documentTitles
String waitApproveStatus = 'Chưa duyệt'

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

// check filter by WAIT_APPROVE STATUS
IPaper.inComingDocument.filterByStatus(waitApproveStatus)
documentTitles = IPaper.inComingDocument.getListDocumentTitle()
for (int i = 0; i < documentTitles.size() - 1; i++) {
	String titleItem = documentTitles[i]
	document.setTitle(titleItem)
	IPaper.inComingDocument.viewInformationDocument(document)
	IPaper.documentInformationScreen.checkStatus(DocumentStatus.WAIT_APPROVE)
	IPaper.documentInformationScreen.backToHome()
}

// check filter by APPROVE STATUS
IPaper.inComingDocument.filterByStatus(DocumentStatus.APPROVED)
documentTitles = IPaper.inComingDocument.getListDocumentTitle()
for (int i = 0; i < documentTitles.size() - 1; i++) {
	String titleItem = documentTitles[i]
    document.setTitle(titleItem)
    IPaper.inComingDocument.viewInformationDocument(document)
    IPaper.documentInformationScreen.checkStatus(DocumentStatus.APPROVED)
    IPaper.documentInformationScreen.backToHome()
}

// check filter by REJECT STATUS
IPaper.inComingDocument.filterByStatus(DocumentStatus.REJECT)
documentTitles = IPaper.inComingDocument.getListDocumentTitle()
for (int i = 0; i < documentTitles.size() - 1; i++) {
	String titleItem = documentTitles[i]
	document.setTitle(titleItem)
	IPaper.inComingDocument.viewInformationDocument(document)
	IPaper.documentInformationScreen.checkStatus(DocumentStatus.REJECT)
	IPaper.documentInformationScreen.backToHome()
}

// check filter by RETURN STATUS
IPaper.inComingDocument.filterByStatus(DocumentStatus.RETURNED)
documentTitles = IPaper.inComingDocument.getListDocumentTitle()
for (int i = 0; i < documentTitles.size() - 1; i++) {
	String titleItem = documentTitles[i]
	document.setTitle(titleItem)
	IPaper.inComingDocument.viewInformationDocument(document)
	IPaper.documentInformationScreen.checkStatus(DocumentStatus.RETURNED)
	IPaper.documentInformationScreen.backToHome()
}

""" check filter by DONE STATUS """
IPaper.inComingDocument.filterByStatus(DocumentStatus.DONE)
documentTitles = IPaper.inComingDocument.getListDocumentTitle()
for (int i = 0; i < documentTitles.size() - 1; i++) {
	String titleItem = documentTitles[i]
	document.setTitle(titleItem)
	IPaper.inComingDocument.viewInformationDocument(document)
	IPaper.documentInformationScreen.checkStatus(DocumentStatus.DONE)
	IPaper.documentInformationScreen.backToHome()
}

//IPaper.homeScreen.goToOutComingDocument()
//IPaper.homeScreen.goToRelatedDocument()
