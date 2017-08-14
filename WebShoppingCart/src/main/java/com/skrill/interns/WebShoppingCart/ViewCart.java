package com.skrill.interns.WebShoppingCart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewCart extends HttpServlet {

    /**
	 *
	 */
    private static final long serialVersionUID = -1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IFileManager fileManager = new FileManager();
        StringBuilder theCookie = new StringBuilder();
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                if ("SID".equals(cookie[i].getName())) {
                    theCookie.append(cookie[i].getValue());
                }
                else {
                    theCookie.append("");
                }
            }
            try {
                String content = fileManager.readFile(theCookie.toString());
                if ((content == null) || ("".equals(content))) {
                    response.getOutputStream().println("Your shopping cart is empty :)");
                } else {
                    String result = parseResult(content);
                    response.getOutputStream().println(result);
                }
            } catch (FileNotFoundException exc) {
                response.sendError(400);
            }
        } else {
            Cookie c = (Cookie) request.getSession().getAttribute("SampleCookie");
            response.addCookie(new Cookie("SID", c.getValue()));
            response.getOutputStream().print("Empty shopping cart.");
        }
    }

    /**
     * method parseResult makes Map with elements searches for repeated elements
     * count them
     *
     * @param String
     *            that contains unordered and unnumbered strings
     * @return String with all elements
     */
    private String parseResult(String content) {
        HashMap<String, Integer> productMap = new HashMap<String, Integer>();

        String[] products = content.split(", ");
        StringBuilder result = new StringBuilder();
        Integer count;
        productMap.put(products[0].substring(1), 1);
        for (int i = 1; i < products.length; i++) {
            String tempProductFirst = products[i].substring(1);
            if (products[i].startsWith("+")) {
                count = productMap.get(tempProductFirst);
                productMap.put(tempProductFirst, (count == null) ? 1
                        : count + 1);
            } else if (products[i].startsWith("-")) {
                count = productMap.get(tempProductFirst);
                productMap.put(tempProductFirst, ((count == null) || (count == 0)) ? 0
                        : count - 1);
            }
        }
        List<String> keys = new ArrayList<String>(productMap.keySet());
        if (keys != null) {
            for (String key : keys) {
                result.append(key);
                result.append(":");
                result.append(productMap.get(key));
                result.append("; ");
            }
        }
        return result.toString();
    }
}
