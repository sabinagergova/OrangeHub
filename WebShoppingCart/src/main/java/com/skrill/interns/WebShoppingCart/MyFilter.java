package com.skrill.interns.WebShoppingCart;

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
public class MyFilter implements Filter {

    Random r = new Random();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest rawRequest, ServletResponse rawResponse, FilterChain chain) throws IOException, ServletException {
        if ((rawRequest instanceof HttpServletRequest) && (rawResponse instanceof HttpServletResponse)) {
            HttpServletRequest request = (HttpServletRequest) rawRequest;
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                String id = Long.toString((r.nextInt(100000)) + System.currentTimeMillis());
                request.getSession().setAttribute("SampleCookie", new Cookie("SID", id));
                chain.doFilter(request, rawResponse);
            }
            chain.doFilter(request, rawResponse);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
