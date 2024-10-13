package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper extends BaseHelper {
    public final String CONTACT_LOCATOR="contact-item_card__2SOIM";

    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(textToFind)) return true;
        }
        return false;
    }

    public void addNewContactPositiveData(String name) {
        //click on Add link
        clickAddLink();
        fillInNewContactForm(
                new Contact()
                        .setName(name)
                        .setLastName("LastNameTest")
                        .setPhone("1234567890")
                        .setEmail("admin@gmail.com")
                        .setAddress("Germany, Berlin")
                        .setDescription("Some Description"));
        clickSaveContactButton();
    }

    public void addNewContactPositiveDataWODescription(String name) {
        clickAddLink();
        fillInNewContactForm(
                new Contact()
                        .setName(name)
                        .setLastName("LastNameTest")
                        .setPhone("1234567890")
                        .setEmail("admin@gmail.com")
                        .setAddress("Germany, Berlin"));
        clickSaveContactButton();
    }

    private void fillInNewContactForm(Contact contact) {
        // enter Name
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        //enter LastName
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        //enter Phone
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        //enter Email
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        //enter Address
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        //enter Description
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    private void clickSaveContactButton() {
        click(By.xpath("//b[.='Save']"));
    }

    private void clickAddLink() {
        click(By.xpath("//a[.='ADD']"));
    }

    public void deleteOneContact() { //метод для удаления контактов на странице
        click(By.className("contact-item-card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }

    public void deleteAllContacts() {
        try {
            while (hasContacts()) {
                int sizeBefore = actualSizeOfContacts();
                deleteOneContact();
                wait.until((WebDriver d)-> actualSizeOfContacts() < sizeBefore);
            }
        } catch (NoSuchElementException e) {
            System.out.println("All contacts were deleted");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int actualSizeOfContacts() {
        if(hasContacts()) {//если контакт на сранице есть
            //попадаем в общее поле див с контактами и возвращаем количество
            return driver.findElements(By.className("h2")).size();
        }
        return 0;
    }

    //метод для проверки наличия элемента на странице
    public boolean hasContacts() {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }
}
