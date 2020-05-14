package com.ai.page.common;

import com.ai.page.support.util.ThreadLocalUtil;
import org.openqa.selenium.WebDriver;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public class DriverBase {

    private WebDriver driver;
    private static ThreadLocalUtil<WebDriver> driverThreadLocalUtil = new ThreadLocalUtil<WebDriver>();
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();


    public WebDriver getDriver(){
        return driverThreadLocalUtil.getThreadValue(driverThreadLocal);
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }
}
