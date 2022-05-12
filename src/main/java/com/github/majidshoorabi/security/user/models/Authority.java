package com.github.majidshoorabi.security.user.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author majid.shoorabi
 * @created 2022-12-May
 * @project peysaz
 */

public enum Authority implements GrantedAuthority {

    OP_ACCESS_ADMIN,
    OP_NEW_USER,
    OP_EDIT_USER,
    OP_DELETE_USER,
    OP_ACCESS_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
