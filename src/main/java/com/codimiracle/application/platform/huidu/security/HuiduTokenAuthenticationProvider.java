package com.codimiracle.application.platform.huidu.security;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.service.UserTokenService;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
public class HuiduTokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Resource
    private UserTokenService userTokenService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserToken userToken = (UserToken) authentication.getDetails();
        Date now = new Date();
        if (now.after(userToken.getExpireTime())) {
            throw new CredentialsExpiredException("credentials expired token=" + userToken.getToken());
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Optional<String> tokenCredentials = Optional.ofNullable(authentication.getCredentials()).map(String::valueOf);
        UserToken userToken = tokenCredentials.map(userTokenService::findByToken).orElseThrow(() -> new UsernameNotFoundException("Invalid token authentication."));
        authentication.setDetails(userToken);
        return Optional.of(userToken).map(UserToken::getUser).orElseThrow(() -> new UsernameNotFoundException("User credentials not found."));
    }
}
