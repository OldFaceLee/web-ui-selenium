package com.ai.page.support.listener;

import com.ai.page.common.CommonSelenium;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author: lixuejun
 * @date: Create in 2019/10/30 下午5:20
 * @description:
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private Logger log = Logger.getLogger(RetryAnalyzer.class);
    private int retryCount = 1;
    private int maxRetryCount=2; // 仅retry1次

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (CommonSelenium.driver == null) {
            log.info("driver为空，不需要截图，不需要重新retry");
        }else{
            String screenshotName = iTestResult.getName()+"_RetryAnalyzer监听异常";
            log.info(String.format("RetryAnalyzer监听异常，自动触发截图[%s]",screenshotName));
            CommonSelenium.snapShotFullScreen(screenshotName);
            if (CommonSelenium.driver != null) {
                log.info("RetryAnalyzer::浏览器driver["+CommonSelenium.driver.toString()+"]!=null,RetryAnalyzer将关闭浏览器");
                CommonSelenium.closeBrowser();
            }
            if(retryCount < maxRetryCount){
                log.info("方法<"+iTestResult.getName()+">执行失败，重试第"+retryCount+"次");
                retryCount++;
                return true;
            }
        }
        return false;
    }
}
