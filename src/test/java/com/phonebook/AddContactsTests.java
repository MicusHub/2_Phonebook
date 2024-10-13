package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    private final String CONTACT_NAME = "TestName"; //здесь будет храниться имя нового контакта (это нужно для isContactAdded)

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("dfge345f@gmail.com", "Password123@");
    }

    @Test(invocationCount = 1, priority = 1) //выполнит этот тест 5 раз (как цикл)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME)); //метод проверяет по инпуту name, добавился ли новый контакт
    }

    @Test(priority = 2)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME)); //метод проверяет по инпуту name, добавился ли новый контакт
    }

    @AfterMethod(enabled = false)
    public void postCondition() { //метод для удаления контактов
        app.getContactHelper().deleteOneContact();
    }
}