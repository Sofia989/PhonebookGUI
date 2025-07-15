package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.phonebook.core.ApplicationManager.softAssert;

public class LoginTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondition(){

        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }
    @Parameters({"email","password"})
    @Test(priority = 1)
    public void loginRegisteredUserPositiveTest(String email,String password){

        //click Login link
        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(email)
                .setPassword(password));
        //fill in Login form
        //click Login button
        app.getUser().clickOnLoginButton();
        //Check Sign Out button is present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test(priority = 2,groups = "demo")
    public void loginRegisteredUserNegativeWithoutEmailTest(){

        //click Login link
        app.getUser().clickOnLoginLink();
        //fill in registration form
        app.getUser().fillLoginRegisterForm(new User()

                .setPassword("Star123!$"));
        //fill in Login form
        //click Login button
        app.getUser().clickOnLoginButton();
        //Check Sign Out button is present
        softAssert.assertTrue(app.getUser().isAlertPresent());
        softAssert.assertTrue(app.getUser().isErrorMessagePresent());
        softAssert.assertAll();
    }


    }


