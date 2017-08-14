package com.skrill.interns.converter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestFormFilter {

    FormFilter filter;
    HttpServletRequest request;
    HttpServletResponse response;
    String attName;
    FilterChain chain;

    @BeforeClass
    public void setup() {
        filter = new FormFilter();
        chain = mock(FilterChain.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        attName = "ConverterCookie";

    }

    @Test
    public void givenNoCookiesInRequestWhenFormServletHitThenBakeACookie()
            throws IOException, ServletException {
        // GIVEN
        when(request.getCookies()).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/converter/form");
        // WHEN
        filter.doFilter(request, response, chain);
        // THEN
        assertNotNull(filter.session);

    }

}
