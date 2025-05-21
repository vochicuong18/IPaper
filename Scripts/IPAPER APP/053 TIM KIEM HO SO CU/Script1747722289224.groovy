import entities.Document
import entities.User
import ipaper.IPaper
import utilities.DataTest

User a8nv = DataTest.getUserTest("userA8NV")

User auto6 = DataTest.getUserTest('auto6') // assigner

Document document = DataTest.createDocumentTest(a8nv, auto6, null, 'dummy.pdf', 'dummy.pdf')

document.setTitle("Tờ trình ngoại lệ sản phẩm KHDN. CONG TY ABD:-sắt thép-TT22011400030")

IPaper.loginScreen.login(a8nv)

// ======================== CHECK ENABLED ======================== 

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableShowDocumentOlder(true)

IPaper.settingScreen.backToHome()

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.loadDataResult()

IPaper.inComingDocument.searchDocument(document.getTitle())

IPaper.inComingDocument.isDocumentDisplayed(document)

IPaper.documentInformationScreen.backToHome()

// ======================== CHECK DISABLED ========================

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableShowDocumentOlder(false)

IPaper.settingScreen.backToHome()

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.loadDataResult()

IPaper.inComingDocument.searchDocument(document.getTitle())

IPaper.inComingDocument.isDocumentDisplayed(document)