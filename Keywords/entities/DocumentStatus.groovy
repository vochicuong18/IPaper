package entities

enum DocumentStatus {
	APPROVED, REJECT, WAIT_GET_COMMENT, COMMENTED

	@Override
	String toString() {
		switch (this) {
			case APPROVED:
				return "Đã duyệt"
			case REJECT:
				return "Từ chối"
			case WAIT_GET_COMMENT:
				return "Chờ lấy ý kiến"
			case COMMENTED:
				return "Đã lấy ý kiến"
			default:
				return "please define"
		}
	}
}
