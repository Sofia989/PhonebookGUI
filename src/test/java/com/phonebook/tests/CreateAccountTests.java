package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondition(){

        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }
    @Test(enabled = false)
    public void newUserRegistrationPositiveTest() {
        //click on LOGIN link
        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm( new User()
                .setEmail("star@gmail.com")
                .setPassword("Star123!$"));
        //click Registration button
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
        //check SignOut button is present
    }

    @Test(groups = "demo")
    public void existedUserRegistrationNegativeTest() {
        //click on LOGIN link
        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("star@gmail.com")
                .setPassword("Star123!$") );
        //click Registration button
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());

    }

}