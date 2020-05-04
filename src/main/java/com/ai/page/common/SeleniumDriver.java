package com.ai.page.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2019/10/23 上午11:38
 * @description:
 */

public class SeleniumDriver extends CommonSelenium {

    private static Logger log = Logger.getLogger(SeleniumDriver.class);

    public enum Browser {
        CHROME,FIREFOX
    }

    public SeleniumDriver() {
    }

    public static void openBrowser(Browser browser) {
        ChromeDriverService service = null;
        switch (browser) {
            case CHROME:
                ChromeOptions option = new ChromeOptions();
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
                driver = new ChromeDriver(service, option);
                log.info("打开chrome浏览器");
                log.info("Driver的地址： " + driver.toString());
                deleteAllCookies();
                maximizeWindow();
                break;
            case FIREFOX:
                GeckoDriverService firefoxService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("火狐driver的地址"))
                        .usingAnyFreePort()
                        .build();
                FirefoxOptions options = new FirefoxOptions()
                        .setBinary("火狐浏览器的path")
                        .setProfile(new FirefoxProfile());
//                WebDriver driver = new FirefoxDriver(service,options);
                break;

        }
    }
}
