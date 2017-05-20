package com.maksym.model;

public enum UserRole {
    DOCTOR("doctor"),
    OPERATOR("operator"),
    ADMIN("admin");

    String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public UserRole setValue(String str) {
        for (UserRole p : values()) {
            if (p.value.equals(str)) {
                return p;
            }
        }
        return null;
    }
}
