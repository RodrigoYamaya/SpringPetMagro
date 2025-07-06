package com.RodriSolution.SpringPetMagro.enums;




public enum RoleEnum {
    ADMIN("ADMIN"),
    TUTOR("TUTOR"),
    VETERINARIO("VETERINARIO");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
