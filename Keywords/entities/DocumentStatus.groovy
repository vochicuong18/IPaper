package entities

enum DocumentStatus {
	WAIT_APPROVE("Chờ giám sát phê duyệt"),       // Gửi duyệt thành công, hồ sơ chưa phê duyệt
	WAIT_PROCESS("Chờ xử lý"),                    // Rút hồ sơ
	APPROVED("Đã duyệt"),                         // Duyệt
	REJECT("Từ chối"),                            // Từ chối
	RETURNED("Chờ bổ sung hồ sơ"),                // Trả về
	WAIT_GET_COMMENT("Chờ lấy ý kiến"),           // Lấy ý kiến
	COMMENTED("Đã lấy ý kiến")                    // Gửi ý kiến

	private final String description

	DocumentStatus(String description) {
		this.description = description
	}

	@Override
	String toString() {
		return description
	}

	String getDescription() {
		return description
	}
}
