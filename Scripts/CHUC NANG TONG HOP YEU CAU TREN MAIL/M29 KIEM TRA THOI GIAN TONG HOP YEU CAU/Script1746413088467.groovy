import entities.Document
import ipaper.IPaper
import screens.OutLook_HomeScreen.EmailNoti
import utilities.DataTest
import utilities.Utilities

def auto6 = DataTest.getUserTest('auto6')

String TO_DAY = new Date().format("dd/MM/yyyy")

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableApproveByEmail(true)

IPaper.settingScreen.backToHome()

Utilities.closeCurentApp()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.searchEmail("$EmailNoti.SUMMARY_DOCUMENT $TO_DAY")

IPaper.outlook_homeScreen.goToFirstEmail()

IPaper.outlook_mailScreen.checkEmailReceivedTime(1)

