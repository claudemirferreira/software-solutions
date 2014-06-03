package br.com.ss.core.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

public class LoginRedirectInterceptor extends HandlerInterceptorAdapter {    

    private String[] loginPagePrefixes = new String[] { "/login.xhtml" };

    private String redirectUrl = "/index.xhtml";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        boolean logged = isInLoginPaths( urlPathHelper.getLookupPathForRequest(request) )
								                   && isAuthenticated();
		if (logged) {
            response.setContentType("text/plain");
            sendRedirect(request, response);
            return false;
        } else {
            return true;
        }
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    private void sendRedirect(HttpServletRequest request,  HttpServletResponse response) {

        String encodedRedirectURL = response.encodeRedirectURL( request.getContextPath() + redirectUrl);
        response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
        response.setHeader("Location", encodedRedirectURL);
    }

    private boolean isInLoginPaths(final String requestUrl) {   
        for (String login : this.loginPagePrefixes) {
            if (requestUrl.startsWith(login)) {
                return true;
            }
        }
        return false;
    }
}