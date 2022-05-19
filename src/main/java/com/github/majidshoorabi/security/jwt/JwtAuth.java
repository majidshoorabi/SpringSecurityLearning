package com.github.majidshoorabi.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author majid.shoorabi
 * @created 2022-19-May
 * @project peysaz
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuth {

    private String username;
    private String password;

}
