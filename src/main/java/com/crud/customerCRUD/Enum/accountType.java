package com.crud.customerCRUD.Enum;

import java.util.Optional;

public enum accountType {
	CURRENT("current"), SAVING("saving");

	

    private String name;

    accountType(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return name;
    }
	
}
