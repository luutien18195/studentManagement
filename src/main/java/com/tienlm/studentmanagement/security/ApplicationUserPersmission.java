package com.tienlm.studentmanagement.security;

public enum ApplicationUserPersmission {
    STUDENT_CREATE("student:create"),
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_DELETE("student:delete"),
    STUDENT_MODULE_ACCESS("student:access");

    private final String persmission;

    ApplicationUserPersmission(String persmission) {
        this.persmission = persmission;
    }

    public String getPersmission() {
        return persmission;
    }
}
