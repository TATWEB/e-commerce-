package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    /* utility class that initializes and quits the WebDriver and uses singleton pattern to prevent multiple initializations or quitting
      of the WebDriver
     */

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();


    private Driver(){}


    public static synchronized WebDriver getDriver(){
        if(drivers.get() == null){

            String browser =  System.getProperty("browser");
            if(browser == null){
                browser = ConfigReader.getProperty("browser");
            }



            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case "safari":
                    drivers.set(new SafariDriver());
                    break;
                case "headlessChrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    drivers.set(new ChromeDriver(chromeOptions));
                    break;
                case "headlessFirefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    drivers.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "headlessEdge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    drivers.set(new EdgeDriver(edgeOptions));
                    break;
                default:
                    System.out.println("Invalid browser");
                    break;
            }


        }


           return  drivers.get();
    }



    public static synchronized void quitDriver(){
        if(drivers.get() != null){
            drivers.get().quit();
            drivers.remove();
        }

    }



}
