package com.qa.test.listner;

import com.qa.test.base.BaseTest;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyser implements IRetryAnalyzer {

    int counter = 1;
    int retryMaxLimit = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retryMaxLimit) {
            counter++;
            return true;
        }
        return false;
    }
}
