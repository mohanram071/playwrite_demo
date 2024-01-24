package com.qa.framework.factory;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) throws InterruptedException {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("browser name is : " + browserName);

        // playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                //browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;

            default:
                System.out.println("please pass the right browser name......");
                break;
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println(width + " " + height);
        tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions()));

      //  tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width, height)));
        //  tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("src/test/resources/videos"))));
          tlPage.set(getBrowserContext().newPage());
        getPage().setDefaultTimeout(60000);
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();

    }

    /**
     * this method is used to initialize the properties from config file
     */
    public Properties init_prop() {
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    /**
     * take screenshot
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "\\screenshot\\" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return path;
    }

}