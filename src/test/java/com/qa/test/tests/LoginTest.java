package com.qa.test.tests;

import com.qa.test.base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "validate the login functionality of curemd application")
    public void validate_the_login_functionality_for_nextgen() throws InterruptedException {
        loginPage.loginToNextGenPortal(prop.getProperty("username"), prop.getProperty("password"));
        loginPage.validateIsLoginSucessful();
    }

    @Test(priority = 2, description = "validate the presence of ehr option")
    public void validate_the_presence_of_ehr_option() throws InterruptedException {
        homePage.validateThePresenceOfEHROption();
    }

    @Test(priority = 3, description = "validate the document page displayed ")
    public void validate_the_document_page() throws InterruptedException {
        homePage.clickOnEhrOption();
        homePage.waitForPatientTrackerPageDisplayed();
    }

    @Test(priority = 4, description = "validate the logout functionality")
    public void validate_the_logout_functionality() {
        homePage.clickOnLogOutButton();
        loginPage.isLogoutSuccessful();
    }


}