package com.github.majidshoorabi.security.config;

import com.github.majidshoorabi.security.user.models.Authority;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author majid.shoorabi
 * @created 2022-12-May
 * @project peysaz
 */

public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().contains(Authority.OP_ACCESS_ADMIN))
            defaultRedirectStrategy.sendRedirect(request, response, "/admin");
        else if (authentication.getAuthorities().contains(Authority.OP_ACCESS_USER))
            defaultRedirectStrategy.sendRedirect(request, response, "/user");
        else
            defaultRedirectStrategy.sendRedirect(request, response, "/");
    }
}
