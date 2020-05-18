package com.ai.page;

import com.ai.page.common.CommonSelenium;
import com.ai.page.common.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/2 下午1:54
 * @description:
 */
public class Application extends CommonSelenium {

    public static void main(String[] args) {
        SeleniumDriver.openBrowser(SeleniumDriver.Browser.CHROME);
        openURL("https://www.jdwl.com/#/");
        waitingThread(5);
        String xpath = "//span[contains(text(),\"京东冷链\")]";
        String xpath1 = "//a[@class=\"main-a\"]/span[text()=\"物流服务\"]";
        String css = "div.nav-pc-local-link-item:nth-child(2)>a.main-a>span";

        long  cssStart= System.currentTimeMillis();
        WebElement wlSv = driver.findElement(By.cssSelector(css));
        long  cssEnd= System.currentTimeMillis();
        System.out.println("css差"+(cssEnd - cssStart));


        long xpathStart = System.currentTimeMillis();
        WebElement wlSvX = driver.findElement(By.xpath(xpath1));
        long xpathEnd = System.currentTimeMillis();
        System.out.println("xpath差"+ (xpathEnd - xpathStart));
        hoverMouse(wlSv);
        closeBrowser();

    }
}
