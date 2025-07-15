package com.phonebook.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger= LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    //@BeforeMethod
    @BeforeSuite(alwaysRun = true)
    public void  setUp(){
        System.err.close();
        app.init();

    }
    @BeforeGroups("demo")
    public void beforeGroups(){
        System.out.println("BeforeGroups");
    }

   //@AfterMethod(enabled = true)
    @AfterSuite(alwaysRun = true)
    public void tearDown(){

        app.stop();
    }

//    @BeforeTest
//    public void startBeforeTest(){
//        System.out.println("BeforeTest");

    @BeforeMethod
    public void startTest(Method method,Object[]p){
        logger.info("Start test{} with data:{}",method.getName(), Arrays.asList(p));
    }
    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result){


        if(result.isSuccess()){
            logger.info("Passed:"+result.getMethod().getMethodName());
            logger.info(("Stop test"));

        }else{
            logger.error("FAILED:"+result.getMethod().getMethodName()+"Screenshot:"+
                    app.getUser().takeScreenShot());
        }
        logger.info("Stop test");
        logger.info("==================================================");
    }
    }





