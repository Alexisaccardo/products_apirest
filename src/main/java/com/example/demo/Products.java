package com.example.demo;

public class Products {
    public String code;
    public String name;
    public String amount;
    public String base_value;
    public String base_public;
    public String supplier;

    public Products(String code, String name, String amount, String base_value, String base_public, String supplier) {
        this.code = code;
        this.name = name;
        this.amount = amount;
        this.base_value = base_value;
        this.base_public = base_public;
        this.supplier = supplier;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBase_value() {
        return base_value;
    }

    public void setBase_value(String base_value) {
        this.base_value = base_value;
    }

    public String getBase_public() {
        return base_public;
    }

    public void setBase_public(String base_public) {
        this.base_public = base_public;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}