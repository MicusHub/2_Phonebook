package com.phonebook.core;
import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomeHelper;
import com.phonebook.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;

    UserHelper userHelper;
    HomeHelper homeHelper;
    ContactHelper contactHelper;

    private final String browser;
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        //driver = new ChromeDriver();
        if (browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("windows-size=1920,1080");
            options.addArguments("headless");
            driver = new ChromeDriver(); //(options)
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }else if (browser.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }

        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // явное ожидание

        userHelper = new UserHelper(driver,wait);
        homeHelper = new HomeHelper(driver,wait);
        contactHelper = new ContactHelper(driver,wait);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public HomeHelper getHomeHelper() {
        return homeHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void stop() {
        driver.quit();
    }
}
