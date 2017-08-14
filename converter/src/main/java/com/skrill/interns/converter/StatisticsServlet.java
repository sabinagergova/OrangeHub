package com.skrill.interns.converter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Link", "styles.css ;rel=stylesheet");
		resp.getOutputStream().print(Content.getStatisticsPage("future statistics"));
    }

}
