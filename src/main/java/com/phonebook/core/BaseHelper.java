package com.phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseHelper {
    Logger logger = LoggerFactory.getLogger(BaseHelper.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isElementPresent(By locator) { //создан и перенесен из CreateAccountPositiveTest
        //logger.info("Проверка есть ли элемент [" + locator +"] на странице");
        return driver.findElements(locator).size() > 0; //если элементов на странице больше чем 0, тогда возвращаем
    }

    //эти методы, с помощью refactor > pull members up были перенесены сюда из класса CreateAccountPositiveTests
    protected void type(By locator, String text) { //это инпут email
        if (text != null) { //если один из инпутов не заполнен, код выполнится без введения данных (метод sendKeys не может передавать пустоту)
            click(locator);
            driver.findElement(locator).clear(); //очистить элемент (если там уже есть символы)
            driver.findElement(locator).sendKeys(text); //sendKeys - вводит данные в инпут
        }
    }

    protected void click(By locator) { //это Login link
        driver.findElement(locator).click();
        //logger.info(" [" + locator + "] is pressed");
    }

    public boolean isAlertPresent() { //isAlertPresent метод для проверки наличия алертов (всплывающих окон)
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert == null){
            return false;
        }else {
            logger.warn("Alert has text: [" + alert.getText() + "]");
            alert.accept();
            return true;
        }
    }

    public String alertTextPresent() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    //Объект File для сохранения скриншота
    public String takeScreenshot() {
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //временный файл скрина
            Files.copy(tmp.toPath(), screenshot.toPath()); //копируем временный File в ранее созданный файл test_screenshot
        } catch (NoSuchSessionException e) {
            logger.error("WebDriver session is closed, cannot take screenshot", e);
            return ""; //возвращаем пустую строку, чтобы тест не падал
        } catch (IOException e) {
            //логируем ошибку при сохранении скрина и выбрасываем исключение RuntimeException
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath(); //возвращаем путь к сохраненному скрину
    }
}
