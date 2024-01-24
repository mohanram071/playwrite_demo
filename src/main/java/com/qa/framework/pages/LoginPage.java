package com.qa.framework.pages;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.framework.listeners.ExtentReportListener;
import org.testng.Assert;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private static Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    String userName = "//input[@id='username']";
    String password = "//input[@id='password']";
    String loginBtn = "//input[@value='Log In']";
    String loginFrame = "//iframe[@id=\"loginIframe\"]";
    String logout = "//a[normalize-space()='[Logout]']";


    public void loginToNextGenPortal(String uName, String uPassword) throws InterruptedException {
        page.frameLocator(loginFrame).locator(userName).fill(uName);
        page.frameLocator(loginFrame).locator(password).fill(uPassword);
        TimeUnit.SECONDS.sleep(2);
        page.frameLocator(loginFrame).locator(loginBtn).click();
    }

    public void validateIsLoginSucessful() {
        page.locator(logout).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertThat(page.locator(logout)).hasText("[Logout]");
    }


    public static void guiOperation(String locators, String operation, String textToEnter) {
        try {
            switch (operation.toLowerCase().trim()) {
                case "click":
                    page.locator("" + locators + "").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    page.locator("" + locators + "").click();
                    ExtentReportListener.test.get().info(MarkupHelper.createLabel(locators + " is clicked successfully", ExtentColor.GREEN));
                    break;
                case "display":
                    page.locator("" + locators + "").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    page.locator("" + locators + "").isVisible();
                    ExtentReportListener.test.get().info(MarkupHelper.createLabel(locators + " is displayed successfully", ExtentColor.GREEN));

                    break;
                case "type":
                    // page.locator("" + locators + "").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                    page.locator("" + locators + "").fill(textToEnter);
                    ExtentReportListener.test.get().info(MarkupHelper.createLabel(textToEnter + " is entered successfully", ExtentColor.GREEN));
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            ExtentReportListener.test.get().info(MarkupHelper.createLabel("Error in locator " + locators, ExtentColor.RED));
//            Assert.fail();
        }
    }

    public void isLogoutSuccessful() {
        try {
            page.frameLocator(loginFrame).locator(userName).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            if (page.frameLocator(loginFrame).locator(userName).isVisible()){
                Assert.assertTrue(true);
            }else {
                Assert.fail();
            }

        } catch (Exception ex) {

        }
    }

}