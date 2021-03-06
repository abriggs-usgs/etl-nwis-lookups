package gov.acwi.wqp.etl;

public class BasicLookup {
	private String code;
	private String name;
	private int sortOrder;
	private String description;
	private boolean validFlag;
	
	public BasicLookup() {
	}
	
	public BasicLookup(String code, String name, int sortOrder, String description, boolean validFlag) {
		this.code = code;
		this.name = name;
		this.sortOrder = sortOrder;
		this.description = description;
		this.validFlag = validFlag;
	}

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isValidFlag() {
		return validFlag;
	}
	public void setValidFlag(Boolean validFlag) {
		this.validFlag = validFlag;
	}
	
	@Override
	public String toString() {
		return "code: " + code + ", name: " + name;
	}
	
}
