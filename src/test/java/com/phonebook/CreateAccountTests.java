package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {
    @Test
    public void CreateAccountPositiveTest1() { //есть дубликат в com.phonebook.TestBase
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User() //объединили метод email и password
                .setEmail("dfge1345f@gmail.com") //добавил 1
                .setPassword( "Password123@"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent()); //после успешной регистрации юзера, нажать выход (Sign Out)
    }

    @Test
    public void CreateAccountPositiveTest2() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com"; //dfge345f@gmail.com
        String password = "Password123@";
        app.getUserHelper().register(email, password);
    }

    @Test
    public void CreateAccountAndLoginPositiveTest() { //проверяем регистрацию пользователя и возможность залогиниться
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com"; //dfge345f@gmail.com
        String password = "Password123@";
        app.getUserHelper().register(email, password);
        //метод лог аут находится в ТestBase (нужно предварительно выйти с аккаунта, чтобы иметь возможность создать новый и залогиниться там)
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }

    @Test
    public void CreateAccountNegativeTest() { //есть дубликат в com.phonebook.TestBase
        SoftAssert softAssert = new SoftAssert(); //тест не упадет, даже при наличии ошибки
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User() //объединили метод email и password
                .setEmail("dfge345f@gmail.com")
                .setPassword( "Password123@"));
        app.getUserHelper().clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent()); //после успешной регистрации юзера, нажать выход (Sign Out)
        //Assert.assertTrue(isAlertPresent()); //isAlertPresent метод для проверки наличия алертов (всплывающих окон)
        //Assert.assertTrue(isError409Present()); //проверяем - имеется ли в алёрте уведомление об ошибке
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll(); //собирает все полученные ошибки в ходе выполнения теста и выводит их в конце, после выполнения теста
    }

}

