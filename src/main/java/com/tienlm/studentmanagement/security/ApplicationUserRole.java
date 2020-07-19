package com.tienlm.studentmanagement.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.tienlm.studentmanagement.security.ApplicationUserPersmission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(STUDENT_MODULE_ACCESS, STUDENT_READ)),
    ADMIN(Sets.newHashSet(STUDENT_MODULE_ACCESS, STUDENT_CREATE, STUDENT_READ, STUDENT_UPDATE, STUDENT_DELETE));

    private final Set<ApplicationUserPersmission> persmissions;

    ApplicationUserRole(Set<ApplicationUserPersmission> persmissions) {
        this.persmissions = persmissions;
    }

    public Set<ApplicationUserPersmission> getPersmissions() {
        return persmissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> persmissions =  getPersmissions()
                .stream()
                .map(persmission -> new SimpleGrantedAuthority(persmission.getPersmission()))
                .collect(Collectors.toSet());

        persmissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return persmissions;
    }

}
