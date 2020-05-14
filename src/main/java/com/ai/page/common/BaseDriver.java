package com.ai.page.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description: WebDriver解决多线程隔离
 */
public abstract class BaseDriver {

    private static final Logger log = Logger.getLogger(BaseDriver.class);
    private static ChromeDriverService service = null;
    private static ChromeOptions option = null;
    private static String browser = "";

    public static WebDriver getDriver(){
        option = new ChromeOptions();
        List<String> optionListString = new ArrayList<String>();
        if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
            log.info("当前运行的环境为" + System.getProperty("os.name"));
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File(System.getProperty("user.dir")+"/driver/chromedriver"))
                    .build();
//                    optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
        } else if (System.getProperty("os.name").contains("win")) {
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
//                            .usingDriverExecutable(new File(BrowserConfig.CHROME_DRIVER_PATH_WIN.getValue()))
                    .build();
            optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
        } else {
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
//                            .usingDriverExecutable(new File(BrowserConfig.CHROME_DRIVER_PATH_LINUX.getValue()))
                    .build();
            optionListString.add("--headless");
            optionListString.add("--disable-gpu");//谷歌文档提到需要加上这个属性来规避bug
            optionListString.add("--no-sandbox"); //让Chrome在root权限下跑
            optionListString.add("--disable-dev-shm-usage");
            optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
            log.info("设置了chrome-headerless模式");
        }
        option.addArguments(optionListString);
        return new ChromeDriver(service, option);
    }

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue(){
            option = new ChromeOptions();
            List<String> optionListString = new ArrayList<String>();
            if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
                log.info("当前运行的环境为" + System.getProperty("os.name"));
                service = new ChromeDriverService.Builder()
                        .usingAnyFreePort()
                        .usingDriverExecutable(new File(System.getProperty("user.dir")+"/driver/chromedriver"))
                        .build();
//                    optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
            } else if (System.getProperty("os.name").contains("win")) {
                service = new ChromeDriverService.Builder()
                        .usingAnyFreePort()
//                            .usingDriverExecutable(new File(BrowserConfig.CHROME_DRIVER_PATH_WIN.getValue()))
                        .build();
                optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
            } else {
                service = new ChromeDriverService.Builder()
                        .usingAnyFreePort()
//                            .usingDriverExecutable(new File(BrowserConfig.CHROME_DRIVER_PATH_LINUX.getValue()))
                        .build();
                optionListString.add("--headless");
                optionListString.add("--disable-gpu");//谷歌文档提到需要加上这个属性来规避bug
                optionListString.add("--no-sandbox"); //让Chrome在root权限下跑
                optionListString.add("--disable-dev-shm-usage");
                optionListString.add("blink-settings=imagesEnabled=false");//不加载图片, 提升速度"
                log.info("设置了chrome-headerless模式");
            }
            option.addArguments(optionListString);
            return new ChromeDriver(service, option);
        }
    };

}
