package com.qa.test.base;

import java.util.Properties;

import com.qa.framework.pages.HomePage;
import org.testng.annotations.*;

import com.microsoft.playwright.Page;
import com.qa.framework.factory.PlaywrightFactory;
import com.qa.framework.pages.LoginPage;

public class BaseTest {

    PlaywrightFactory pf;
    Page page;
    protected Properties prop;

    protected LoginPage loginPage;
    protected HomePage homePage;


    @BeforeTest
    public void setup() throws InterruptedException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
    }
}
	
	
	
	
	

