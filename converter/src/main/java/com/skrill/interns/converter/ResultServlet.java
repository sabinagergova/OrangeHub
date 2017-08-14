package com.skrill.interns.converter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HashMap<String, StatHolder> clients = new HashMap<String, StatHolder>();
    String inputType = "";
    JSONToXMLConverter jsonToXml = new JSONToXMLConverter();
    XMLToJSONConverter xmlToJson = new XMLToJSONConverter();
    String resultPage = "";
    String input;
    String cookie = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            cookie = req.getCookies()[0].getValue();
        } catch (NullPointerException e) {
            cookie = "-1";
        }
        if (cookie != null) {
            if (clients.get(cookie) == null)
                clients.put(cookie, new StatHolder());
        }
        input = req.getParameter("form");
        if (input == null) {
            resp.getOutputStream().print("No input to be converted");
            return;
        }
        Enumeration<String> params = req.getParameterNames();
        useConverter(identifyInputType(params));
        resp.getOutputStream().print(resultPage);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> parameters = req.getParameterMap();
        boolean getStatistics = false;
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if ("stat".equalsIgnoreCase(entry.getKey()) && cookie != null) {
                getStatistics = true;
            }
        }
        if (getStatistics == true) {
            if (clients.get(cookie) != null && (clients.get(cookie).JSONtoXML_Counter > 0 || clients.get(cookie).XMLtoJSON_Counter > 0)) {

                String stats = "JSON -> XML:" + clients.get(cookie).JSONtoXML_Counter + "\r\nXML -> JSON:" + clients.get(cookie).XMLtoJSON_Counter;
                resp.getOutputStream().print(Content.getStatisticsPage(stats));
                cookie = null;
            } else
                resp.getOutputStream().print(Content.getStatisticsPage("You haven't converted anything yet"));
            cookie = null;
        } else if (cookie == null || getStatistics == false)
            resp.sendRedirect("http://localhost:8080/converter/form");
    }

    private void useConverter(String inputType) {
        if (inputType != null && "JSON".equals(inputType)) {
            try {
                resultPage = Content.getResultPage(jsonToXml
                        .convertJSONtoXml(input));
                clients.get(cookie).statistics.put(cookie + "JSON", clients.get(cookie).JSONtoXML_Counter++);
            } catch (Exception e) {
                resultPage = Content.getResultPage("You entered an invalid JSON string.");
            }
        } else if (inputType != null && "XML".equals(inputType)) {
            try {
                resultPage = Content.getResultPage(xmlToJson
                        .convertXMLtoJSON(input));
                clients.get(cookie).statistics.put(cookie + "XML", clients.get(cookie).XMLtoJSON_Counter++);
            } catch (JSONException e) {
                resultPage = Content.getResultPage("You entered an invalid XML string.");
            }
        }
    }

    private String identifyInputType(Enumeration<String> parameters) {

        while (parameters.hasMoreElements()) {
            String element = parameters.nextElement();
            if (element.equals("XML")) {
                inputType = "XML";

                break;
            } else if (element.equals("JSON")) {
                inputType = "JSON";
                break;
            }
        }
        return inputType;
    }
}
