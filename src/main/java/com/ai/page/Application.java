package com.ai.page;

import com.ai.page.common.CommonSelenium;
import com.ai.page.common.SeleniumDriver;
import org.openqa.selenium.By;
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
        openURL("https://www.jdwl.com/#/");
        waitingThread(5);
        String xpath = "//span[contains(text(),\"京东冷链\")]";
        String xpath1 = "//a[@class=\"main-a\"]/span[text()=\"物流服务\"]";
        String css = "div.nav-pc-local-link-item:nth-children(2)>a.main-a>span";
//        WebElement wlSv = driver.findElement(By.xpath(xpath1));

        List<WebElement> wlSvCss = driver.findElements(By.cssSelector(css));
        System.out.println(wlSvCss.get(0).getText());
//        hoverMouse(wlSvCss);

    }
}
