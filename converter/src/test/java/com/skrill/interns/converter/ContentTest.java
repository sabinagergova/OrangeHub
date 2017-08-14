package com.skrill.interns.converter;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ContentTest {
	Content content = new Content();
	String result = null;
	String testHtml = null;
	String testHtml2 = null;

	@Test
	public void givenSomeStringWhenCallingMethodGetResultPageWithThisStringThenResultStringContainsFirstString()
			throws Exception {
		// GIVEN
		String testov = "banan";
		// WHEN
		result = Content.getResultPage("banan");
		// THEN
		result.contains(testov);
	}

	@Test
	public void givenHtmlAsStringWhenCallingGetFormPageThenCompareTwoStrings() {
		// GIVEN
		String testov = "<!DOCTYPE html><!--form.html--><html>	<head>		<title>XML/JSON convertor</title>		<link rel=\"stylesheet\" type=\"text/css\" href=\"cherry_style.css\">	</head>	<body>		<h1>XML/JSON convertor</h1>		<form action=\"http://localhost:8080/converter/result\" method=\"post\">			<textarea name=\"form\" rows = \"20\" required class = \"textarea\"></textarea>			<br>			<input type=\"submit\" name=\"XML\" value=\"convert to JSON\" class=\"buttons\">			<input type=\"submit\" name=\"JSON\" value=\"convert to XML\" class=\"buttons\">		</form>		<form action=\"http://localhost:8080/converter/statistics\" method=\"get\">			<input type=\"submit\" value=\"Get Statistics\" class=\"buttons\">		</form>	</body></html>";
		// WHEN
		result = Content.getFormPage();
		// THEN
		assertEquals(result, testov);
	}
	
	@Test
	public void givenSomeStringWhenCallingGetStatisticsPageWithThisStringThenResultStringContainsFirstString() {
		// GIVEN
		String test = "some result";
		// WHEN
		result = Content.getStatisticsPage(test);
		// THEN
		result.contains(test);
	}
}
