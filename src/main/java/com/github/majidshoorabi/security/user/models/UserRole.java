package com.github.majidshoorabi.security.user.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */

public enum UserRole implements GrantedAuthority {

    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
