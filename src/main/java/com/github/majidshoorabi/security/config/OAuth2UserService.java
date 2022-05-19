package com.github.majidshoorabi.security.config;

import com.github.majidshoorabi.security.user.models.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @author majid.shoorabi
 * @created 2022-19-May
 * @project peysaz
 */

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =super.loadUser(userRequest);

//        for (String key : oAuth2User.getAttributes().keySet()) {
//            System.out.println(key + " : "+oAuth2User.getAttribute(key));
//        }

        User user = new User();
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setName(oAuth2User.getAttribute("name"));
        user.setPicture(oAuth2User.getAttribute("picture"));

        return user;
    }
}
