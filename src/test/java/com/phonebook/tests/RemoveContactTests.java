package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("star@gmail.com")
                .setPassword("Star123!$") );
        //fill in Login form
            //click Login button
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact().setName("Olga")
                             .setLastName("Fischer")
                               .setPhone( "1234567890")
                               .setEmail( "star@gmail.com")
                                .setAddress( "Sonnenstrasse 2")
                               .setDescription( "goalkeeper1"));

        //click on Save button
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactTest(){
        int sizeBefore= app.getContact().sizeOfContacts();

        app.getContact().removeContact();
        app.getContact().pause(1000);

        int sizeAfter= app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter,sizeBefore-1);
    }

}
