package entities

import internal.GlobalVariable

enum LocatorType {
	XPATH,
	ID,

	@Override
	String toString() {
		switch (this) {
			case XPATH:
				return "xpath"
			case ID:
				return GlobalVariable.PLATFORM == "Android" ? "resource-id" : "name"
			default:
				return "please define"
		}
	}
}