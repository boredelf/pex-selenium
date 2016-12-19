package br.com.poupex.testing.selenium;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public abstract class WebDriverManager {

	private static File driverDir = new File(System.getProperty("user.dir"), "target/selenium-drivers");
	private static File driverExecutable = new File(driverDir, "chromedriver-mac");

	private static DriverService driverService = setupDriverService();

	private WebDriverManager() {
	}

	private static DriverService setupDriverService() {
		exposeDriverExecutable();
		return new ChromeDriverService.Builder()
			.usingDriverExecutable(driverExecutable)
			.usingAnyFreePort()
			.build();
	}

	private static void exposeDriverExecutable() {
		if (!driverExecutable.exists()) {
			try {
				copyInputStreamToFile(ClassLoader.getSystemResourceAsStream("chromedriver-mac"), driverExecutable);
				driverExecutable.setExecutable(true);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void usingDriver(Consumer<WebDriver> webDriverConsumer) {
		WebDriver webDriver = new RemoteWebDriver(driverService.getUrl(), DesiredCapabilities.chrome());
		try {
			webDriverConsumer.accept(webDriver);
		} finally {
			webDriver.quit();
		}
	}

	public static synchronized void startDriverService() {
		if (!driverService.isRunning()) {
			try {
				driverService.start();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static synchronized void stopDriverService() {
		if (driverService.isRunning()) {
			driverService.stop();
		}
	}

}
