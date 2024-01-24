package com.qa.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

public class HomePage {

    private static Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    String ehr = "//a[normalize-space()='EHR']";
    String logout = "//a[normalize-space()='[Logout]']";
    String patientTracker = "//span[@id='pgTitle']";


    public void validateThePresenceOfEHROption(){
        try {
            page.locator(ehr).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            if(page.locator(ehr).isVisible()){
                Assert.assertTrue(true);
            }else {
                Assert.fail();
            }
        }catch (Exception ex){

        }
    }

    public void clickOnLogOutButton(){
        try {
            page.locator(logout).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            page.locator(logout).click();
        }catch (Exception ex){

        }
    }

    public void clickOnEhrOption(){
        try {
            page.locator(ehr).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            page.locator(ehr).click();
        }catch (Exception ex){

        }
    }

    public void waitForPatientTrackerPageDisplayed(){
        page.locator(patientTracker).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


}
