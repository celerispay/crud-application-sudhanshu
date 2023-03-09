package com.crud.customer_crud.Enum;

import java.util.Optional;

public enum AccountType {
	CURRENT("current"), SAVING("saving");

	

    private String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return name;
    }
	
}
