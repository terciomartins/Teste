package com.tricentis.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tricentis.core.Propriedades.TipoExecucao;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};	
	
	private DriverFactory() {}
	
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public static WebDriver initDriver() {
		WebDriver driver = null;
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: driver = new ChromeDriver(); break;
				case IE: driver = new InternetExplorerDriver(); break;
				case EDGE: driver = new EdgeDriver(); break;
			}
		}
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
				case IE: cap = DesiredCapabilities.internetExplorer(); break;
				case EDGE: cap = DesiredCapabilities.edge();break;
			}
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.15.17:4444/wd/hub/"), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.err.println("Falha de Conexao com o GRID");
				e.printStackTrace();
			}			
		}
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
				case IE: cap = DesiredCapabilities.internetExplorer();
				 cap.setCapability("platform", "Windows 10"); // para achar uma versao especifica, usar a url https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
				 cap.setCapability("version", "11.285"); 
				break;
				case EDGE: cap = DesiredCapabilities.edge();break;
			}
			try {
				driver = new RemoteWebDriver(new URL("http://tercio:82f1e895-94b2-4c27-9a5d-aa220be9235e@ondemand.saucelabs.com:80/wd/hub/"), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.err.println("Falha de Conexao com o GRID");
				e.printStackTrace();
			}			
		}		
		driver.manage().window().maximize(); // definir browser maximizado			
		return driver;
	}
	
	public static void KillDriver() {
		WebDriver driver = getDriver();
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		if(threadDriver != null) 
		{ // limpa a thread apos a execucao da mesma
			threadDriver.remove();
		}
	}
	

}

