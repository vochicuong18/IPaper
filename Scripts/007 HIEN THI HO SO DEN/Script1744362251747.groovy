import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest

def auto1 = DataTest.getUserTest("auto1")

IPaper.loginScreen.login(auto1)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.checkItemInDocument()
