package data

public class Capabilities {
	public static final CAP = [
		iOS: [
			platformName                       : "iOS",
			platformVersion                    : "18.4.1",
			deviceName                         : "iPhone 13 Pro Max",
			udid                               : "00008110-000C10543443801E",
			automationName                     : "XCUITest",
			noReset                            : true,
			fullReset			                : false,
			//
			"appium:waitForQuiescence"        : false,
			"appium:reduceMotion"             : true,
			"appium:animationCoolOffTimeout"  : 0,
			"appium:customSnapshotTimeout"    : 2,
		],

		Android: [
			platformName         : "Android",
			deviceName           : "af0c5cf7",
			automationName       : "UiAutomator2",
			noReset              : true,
			waitForIdleTimeout   : 3000,
			serverStartTimeout   : 0
		]
	]
}
//			udidgold             : "00008110-000659C02107801E",
//			udid                               : "00008110-000C10543443801E",

