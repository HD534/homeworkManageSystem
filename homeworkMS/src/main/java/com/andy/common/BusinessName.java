package com.andy.common;

public enum BusinessName {
	
	
	
	USERCODE("学号","usercode"),
	USERNAME("姓名","username"),
	TBLCLASS("班级","tblclass"),
	INSTITUTE("学院","institute"),
	SEX("性别","sex");
	

	private final String descChiness;
	private final String descEnglish;

	BusinessName(String descChiness, String descEnglish) {
		this.descChiness = descChiness;
		this.descEnglish = descEnglish;
	}

	public String getDescChiness() {
		return descChiness;
	}

	public String getDescEnglish() {
		return descEnglish;
	}
	
	public static BusinessName getByDescChiness(String descChiness) {
		for (BusinessName businessName : BusinessName.values()) {  
            if (businessName.getDescChiness() .equals(descChiness) ) {  
                return businessName;  
            }  
        }  
        return null;  
	}


}
