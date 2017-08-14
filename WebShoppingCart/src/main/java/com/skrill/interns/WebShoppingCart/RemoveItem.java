package com.skrill.interns.WebShoppingCart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class RemoveItem extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final IFileManager fileManager = new FileManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Cookie[] cookies = req.getCookies();
        String SID = new String();

        if (cookies == null) {
            Cookie c = (Cookie) req.getSession().getAttribute("SampleCookie");
            SID = c.getValue();
            req.getSession().invalidate();
            resp.setHeader("Set-Cookie", "SID=" + SID);
        } else {
            for (Cookie cookie : cookies) {
                if ("SID".equals(cookie.getName())) {
                    SID = cookie.getValue();
                }
            }
        }
        try {
            System.out.println(req.getParameter("itemName") + " was removed with cookie " + SID + " at " + System.currentTimeMillis());
            fileManager.appendToFile(SID, ("-" + req.getParameter("itemName")));
        } catch (FileNotFoundException exc) {
            resp.sendError(400);
        }

    }

    /**
     * Extracts the parameters in the request and concatenates them consequently.
     *
     * @param req
     *            HttpServletRequest to extract the parameter from.
     * @return The item which is implied in the request as a parameter
     *
     */
    private String extractItemFromRequest(HttpServletRequest req) {
        String item = "";
        Enumeration<String> parameters = req.getParameterNames();
        while (parameters.hasMoreElements()) {
            item = req.getParameter(parameters.nextElement());
        }
        return item;
    }

    /**
     * Extracts the cookies from the request as a single string.
     *
     * @param req
     *            HttpServletRequest to extract the cookies from.
     * @return A composite string from the cookies in the request.
     */
    private String extractCookiesFromRequest(HttpServletRequest req) {
        StringBuilder cookiesBuilder = new StringBuilder();
        Cookie[] cookies = req.getCookies();
        String cookieForFile;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookiesBuilder.append(cookies[i].getName()).append(cookies[i].getValue());
            }
            cookieForFile = cookiesBuilder.toString();
        } else {
            return null;
        }
        return cookieForFile;
    }

}
