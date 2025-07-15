package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.MyDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class AddContactTests extends TestBase {

    //precondition:login in
    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("star@gmail.com")
                .setPassword("Star123!$"));
        //fill in Login form
        //click Login button
        app.getUser().clickOnLoginButton();

    }

    @Test
    public void addContactPositiveTest() {
        //click ADD link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact().setName("Olga")
                .setLastName("Fischer")
                .setPhone("1234567890")
                .setEmail("star@gmail.com")
                .setAddress("Sonnenstrasse 2")
                .setDescription("goalkeeper1"));

        //click on Save button
        app.getContact().clickOnSaveButton();
        //check Contact exist
        Assert.assertTrue(app.getContact().isContactCreatedByName("Olga"));
    }

    @AfterMethod
    public void postCondition() {
        //click on the cart
        app.getContact().removeContact();
    }

    //int i = (int)(System.currentTimeMillis()/1000)%3600;


    @DataProvider
    public Iterator<Object[]> addContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Olga", "Fischer", "1234567890", "star@gmail.com", "Sonnenstrasse 2", "goalkeeper"});
        list.add(new Object[]{"Olga1", "Fischer", "1234567890", "star@gmail.com", "Sonnenstrasse 2", "goalkeeper"});
        list.add(new Object[]{"Olga2", "Fischer", "1234567890", "star@gmail.com", "Sonnenstrasse 2", "goalkeeper"});
        return list.iterator();
    }

    @Test(dataProvider = "addContact",dataProviderClass = MyDataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String lastName, String phone, String email, String address, String desc) {
        //click ADD link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact().setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(desc));

        //click on Save button
        app.getContact().clickOnSaveButton();
        //check Contact exist
        Assert.assertTrue(app.getContact().isContactCreatedByName(name));
    }

    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0]).setLastName(split[1]).setPhone(split[2]).setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line= reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "addContactFromCsv",dataProviderClass = MyDataProviders.class)
    public void addContactPositiveFromDataProviderWithCsvTest(Contact contact) {
        //click ADD link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(contact);

        //click on Save button
        app.getContact().clickOnSaveButton();
        //check Contact exist
        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhone()));
    }


}