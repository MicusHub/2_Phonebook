package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //данный метод был перенесен сюда из класа LoginPositiveTests (правой кнопкой по пустому месту > refactor > pull members up > выбираем из списка метод, который нужно перенести (метод! не тесты!))
    public void login(String email, String password) { //метод с общим порядком действий, для тестирования разных пользователей
        //click on login link
        click(By.xpath("//a[.='LOGIN']"));

        //enter email
        type(By.name("email"), email.toLowerCase()); //те же действия, что и в password (toLowerCase приводит символы к нижнему регистру)

        //enter password
        type(By.name("password"), password); //параметр который каждый раз получает новый имейл из инпута и подставляет его сюда (правой кнопкой по имейлу > refactor > introduce parameter)

        //click on login button
        click(By.name("login"));

        //assert that Sign Out button is present
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']"))); //после успешной регистрации юзера, нажать выход (Sign Out)
    }

    //данный метод был перенесен сюда из класа LoginPositiveTests (правой кнопкой по пустому месту > refactor > pull members up > выбираем из списка метод, который нужно перенести (метод! не тесты!))
    public void register(String email, String password) {
        click(By.xpath("//a[.='LOGIN']"));

//        driver.findElement(By.name("email")).click(); //нажать на элемент
        type(By.name("email"), email);

//        driver.findElement(By.name("password")).click();
//        click(By.name("password"));
//        driver.findElement(By.name("password")).clear();
//        driver.findElement(By.name("password")).sendKeys("Password123@");
        type(By.name("password"), password);

//        driver.findElement(By.name("registration")).click(); //кликнуть на элемент Регистрация
        click(By.name("registration"));

        //Assert that button //button[.='Sign Out']
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']"))); //после успешной регистрации юзера, нажать выход (Sign Out)
    }

    public void logout() {
        click(By.xpath("//button[.='Sign Out']")); //это лог аут
    }

    public void fillInRegistrationForm(User user) { //принимает объект user
//        driver.findElement(By.name("email")).click(); //нажать на элемент
        type(By.name("email"), user.getEmail());

//        driver.findElement(By.name("password")).click();
//        click(By.name("password"));
//        driver.findElement(By.name("password")).clear();
//        driver.findElement(By.name("password")).sendKeys("Password123@");
        type(By.name("password"), user.getPassword());
    }

    public void clickRegistrationButton() {
        click(By.name("registration"));
    }

    public void clickLoginLink() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public boolean isError409Present() { //проверяем - появляется ли на сайте ошибка
        return isElementPresent(By.xpath("//div[.='Registration failed with code 409']"));
    }
}
