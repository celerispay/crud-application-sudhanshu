package com.crud.customercrud.enums;

public enum TransactionType {
	PURCHASE("purchase"), TRANSFER("transfer"), DEPOSITE("deposit"), WITHDRAWAL("withdrawal"), PAYMENT("payment");


    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getTransactionType() {
        return name;
    }

	



}
