package com.skrill.interns.converter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class FormFilter implements Filter {

    String uri;
    Cookie session = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest rawRequest,
            ServletResponse rawResponse, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) rawRequest;
        HttpServletResponse response = (HttpServletResponse) rawResponse;
        uri = request.getRequestURI();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            if ("/converter/form".equals(uri)) {
                String sessionID = generateSessionID();
                session = (new Cookie("ConverterCookie", sessionID));
                response.addCookie(session);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    String generateSessionID() {
        Random r = new Random();
        return String.valueOf(r.nextLong() + System.currentTimeMillis());
    }
}
