package com.ai.page.testcase.impl;

import com.ai.page.common.CommonSelenium;
import com.ai.page.common.SeleniumDriver;
import com.ai.page.testcase.ITestCase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public class TC02_open extends CommonSelenium implements ITestCase {
    Logger log = Logger.getLogger(TC02_open.class);

    public void initTestData() {

    }

    public void runScript(Map<String, String> excelData) {
        log.info("tc02线程"+Thread.currentThread().getName());
        SeleniumDriver.openBrowser(SeleniumDriver.Browser.CHROME);
        openURL("https://www.jdwl.com/#/");
        String xpath1 = "//a[@class=\"main-a\"]/span[text()=\"物流服务\"]";
        String xpath2 = "//span[contains(text(),\"京东冷链\")]";
        WebElement element = getElement(By.xpath(xpath1));
        hoverMouse(element);
        click(getElement(By.xpath(xpath2)));
        closeBrowser();

    }

    public void destroyTestData() {

    }
}
