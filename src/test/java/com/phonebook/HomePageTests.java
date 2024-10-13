package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get("https://telranedu.web.app/home");
    }

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent(), "Элемент на странице не найден");
        System.out.println("Элемент HomeComponent' найден на домашней странице");
    }
}
