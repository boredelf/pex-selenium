package br.com.poupex.testing.selenium.testng;

import br.com.poupex.testing.selenium.WebDriverManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

	@Override
	public void onStart(ISuite iSuite) {
		WebDriverManager.startDriverService();
	}

	@Override
	public void onFinish(ISuite iSuite) {
		WebDriverManager.stopDriverService();
	}
}
