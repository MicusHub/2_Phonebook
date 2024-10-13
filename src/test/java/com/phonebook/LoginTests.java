package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (app.getUserHelper().isSignOutButtonPresent()){
            app.getUserHelper().logout();
        }
        app.driver.get("https://telranedu.web.app/login");
    }


    @Test
    public void loginExistedUserPositiveTest1() { //есть дубликат в com.phonebook.TestBase
        //click on login link
        //click(By.xpath("//a[.='LOGIN']"));
        app.getUserHelper().clickLoginLink();

        //enter email
        //type(By.name("email"), "dfge345f@gmail.com"); //объединен с паролемfillInRegistrationForm

        //enter password
        //type(By.name("password"), "Password123@");
        //fillInRegistrationForm(new User("dfge345f@gmail.com", "Password123@")); //объединили в один метод email и password
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("dfge345f@gmail.com")
                .setPassword( "Password123@"));

        //click on login button
        //click(By.name("login"));
        app.getUserHelper().clickOnLoginButton();

        //assert that Sign Out button is present
        //Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']"))); //после успешной регистрации юзера, нажать выход (Sign Out)
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        String email = "dfge345f@gmail.com";
        String password = "Password123@";
        context.setAttribute("email", email);
        context.setAttribute("passsword", password);
        app.getUserHelper().login(email, password); //описан в com.phonebook.TestBase
    }

    @Test
    public void loginNegativeWithOutEmailTest() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                //.setEmail("dfge345f@gmail.com")
                .setPassword( "Password123@"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password", "Messages are not equals");
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
    }

    @AfterMethod(enabled = true)
    public void postCondition() {
        try { //пробует нажать кнопку logout в течении 5 сек
            app.getUserHelper().logout();
        } catch (Exception e) { //если не получится, то выведет Exception
            //throw new RuntimeException(e); //комментируем чтобы тест не падал после ошибки
        }
    }
}

