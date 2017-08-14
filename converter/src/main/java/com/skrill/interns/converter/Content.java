package com.skrill.interns.converter;

import java.io.InputStream;
import java.util.Scanner;

public class Content {

	static StringBuilder formPageBuilder = new StringBuilder();
	static StringBuilder resourcePageBuilder = new StringBuilder();
	static StringBuilder statisticPageBuilder = new StringBuilder();
	static StringBuilder cssBuilder = new StringBuilder();
    InputStream stream = null;
    Scanner sc = null;
    ClassLoader loader = this.getClass().getClassLoader();

    public Content() {
		try {
			formPageBuilder = construct("form.html", formPageBuilder);
			resourcePageBuilder = construct("result.html", resourcePageBuilder);
			statisticPageBuilder = construct("statistics.html",
					statisticPageBuilder);
			cssBuilder = construct("cherry_style.css", cssBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
				stream.close();
			} catch (Exception ex) {
				System.out.println("do not want to close");
			}
		}
	}

	public static String getFormPage() {
		return formPageBuilder.toString();
	}

	public static String getResultPage(String result) {
		String resultPage = resourcePageBuilder.toString();
		resultPage = String.format(resultPage, result);
		return resultPage;
	}

	public static String getStatisticsPage(String statistics) {
		String resultPage = statisticPageBuilder.toString();
		resultPage = String.format(resultPage, statistics);
		return resultPage;
	}

	public static String getCSS() {
		return cssBuilder.toString();
	}

    private StringBuilder construct(String file, StringBuilder temp) {
		stream = loader.getResourceAsStream(file);
		sc = new Scanner(stream);
		while (sc.hasNextLine()) {
			temp.append(sc.nextLine());
		}
		return temp;
	}
}
