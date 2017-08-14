package com.skrill.interns.converter;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		chain = mock(FilterChain.class);

	}

	// @Test
	// public void givenNoCookiesInRequestWhenFormServletHitThenBakeACookie()
	// throws IOException, ServletException {
	// // GIVEN
	// when(request.getCookies()).thenReturn(null);
	// when(request.getContextPath()).thenReturn("/form");
	// // WHEN
	// filter.doFilter(request, response, chain);
	// // THEN
	// assertNotNull(filter.session);
	//
	// }

}
