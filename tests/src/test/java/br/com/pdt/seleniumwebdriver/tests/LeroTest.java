package br.com.pdt.seleniumwebdriver.tests;

import br.com.poupex.testing.selenium.WebDriverManager;
import org.testng.annotations.Test;

@Test
public class LeroTest {

	public void userDirTest() {
		WebDriverManager.usingDriver(webDriver -> {
			webDriver.get("http://localhost:9000/");
		});
	}

	public void lero2() {
		WebDriverManager.usingDriver(webDriver -> {
			webDriver.get("http://localhost:9001/");
		});
	}

}
