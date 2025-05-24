import entities.User as User
import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest

User auto3 = DataTest.getUserA4NVTest()

IPaper.loginScreen.login(auto3)

IPaper.homeScreen.goToNotificationScreen()

IPaper.notificationScreen.isNotificationUnRead()

IPaper.notificationScreen.areNotificationsSortedByTime()

IPaper.notificationScreen.readAllNotification()

IPaper.notificationScreen.isNotificationRead()

IPaper.notificationScreen.deleteAllNotification()

IPaper.notificationScreen.isNotificationEmpty()