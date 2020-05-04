package com.ai.page;

import com.ai.page.common.CommonSelenium;
import com.ai.page.common.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/2 下午1:54
 * @description:
 */
public class Application extends CommonSelenium {

    public static void main(String[] args) {
        SeleniumDriver.openBrowser(SeleniumDriver.Browser.CHROME);
        openURL("https://www.jdwl.com/#/");
        String xpath = "//div[@class='modal-dialog']";
        waitingThread(5);
        WebElement el = driver.findElement(By.xpath(xpath));
        snapShotFullScreen("全屏截图");
        snapShotElement(el,"具体元素截图");
        closeBrowser();

    }
}
