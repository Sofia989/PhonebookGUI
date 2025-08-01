package com.phonebook.tests;

import com.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {



    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getHomePage().isHomeComponentPresent()){
            app.getUser().clickOnHomeLink();
        }
    }

    @Test

    public void isHomeComponentPresentTest(){
       //System.out.println("Home Component is " + isHomeComponentPresent());
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());

                ////div[2]/div/div/h1
    }

}
