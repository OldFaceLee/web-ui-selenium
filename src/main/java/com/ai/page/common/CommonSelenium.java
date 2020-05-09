package com.ai.page.common;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: lixuejun
 * @date: Create in 2019/10/23 上午11:36
 * @description: selenium共用类
 */
public  class CommonSelenium {
    private static org.apache.log4j.Logger log = Logger.getLogger(CommonSelenium.class);
    public static WebDriver driver;
    private static final int TIME_OUT_SENCONDS = 30;

    /**
     * alert点击接受
     */
    public static void alertAccept(){
        Alert alert = driver.switchTo().alert();
        waitingThread(0.5);
        alert.accept();
        log.info("点击alert弹框接受button");
    }

    /**
     * alert点击拒绝
     */
    public static void alertDismiss(){
        Alert alert = driver.switchTo().alert();
        waitingThread(0.5);
        alert.dismiss();
        log.info("点击alert弹框拒绝button");
    }

    /**
     *获取alert的text
     */
    public static String alertGetText(){
        Alert alert = driver.switchTo().alert();
        waitingThread(0.5);
        String alertText = alert.getText();
        log.info("获取的alertText【"+alertText+"】");
        return alertText;
    }

    /**
     *向alert弹框中的输入框输入值
     */
    public static void alertSendKeys(String strs){
        Alert alert = driver.switchTo().alert();
        waitingThread(0.5);
        alert.sendKeys(strs);
        log.info("向alert弹框中的输入框输入值【"+strs+"】");
    }

    /**
     * 清空浏览器缓存
     */
    public static void deleteAllCookies() {
        driver.manage().deleteAllCookies();
        log.info("清空浏览器缓存");
    }

    /**
     * 打开网址
     */
    public static void openURL(String url) {
        driver.get(url);
        log.info("打开URL【" + url + "】");
    }

    /**
     * 鼠标点击元素
     *
     */
    public static void click(WebElement element) {
        if(isElementDisplay(element)){
            element.click();
            log.info("鼠标左键点击元素" + element.toString());
        }
    }

    /**
     * 通过Actions(driver)点击页面元素
     */
    public static void clickByActions(WebElement element) {
        if(isElementDisplay(element)){
            Actions actions = new Actions(driver);
            actions.click(element).build().perform();
            log.info("通过Actions对象进行点击页面元素" + element.toString());
        }
    }

    /**
     * 通过javaScript方法点击元素
     */
    public static void clickByJs(WebElement element) {
        if(isElementDisplay(element)){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
            log.info("通过js方法点击元素 " + element.toString());
        }
    }

    /**
     * 清空元素
     */
    public static void clear(WebElement element){
        element.clear();
        log.info("清空输入框元素的内容");
    }

    /**
     * 向输入框输入数据
     *
     */
    public static void sendKeys(WebElement element, String strs) {
        click(element);
        clear(element);
        element.sendKeys(strs);
        log.info("向元素" + element.toString() + "输入参数【" + strs + "】");
    }

    /**
     * 通过actions方法输入数据
     *
     */
    public static void sendKeysByActions(WebElement element, String strs) {
        clickByActions(element);
        Actions actions = new Actions(driver);
        actions.sendKeys(strs).build().perform();
        log.info("通过Actions方法向输入框元素" + element.toString() + "输入参数【" + strs + "】");
    }

    /**
     * 显示等待获取元素
     */
    public static WebElement getElement(final By by) {
        WebElement element = new WebDriverWait(driver, TIME_OUT_SENCONDS)
                .until(new ExpectedCondition<WebElement>() {
                    @Nullable
                    public WebElement apply(@Nullable WebDriver webDriver) {
                        return driver.findElement(by);
                    }
                });
        log.info("显示等待获取元素" + element.toString() + ", timeout时长为【" + TIME_OUT_SENCONDS + "秒】");
        return element;
    }

    /**
     * 显示等待获取元素集合
     */
    public static List<WebElement> getElements(final By by){
        List<WebElement> elements = new WebDriverWait(driver, TIME_OUT_SENCONDS)
                .until(new ExpectedCondition<List<WebElement>>() {
                    @Nullable
                    public List<WebElement> apply(@Nullable WebDriver webDriver) {
                        List<WebElement> elements = driver.findElements(by);
                        return elements;
                    }
                });
        log.info("显示等待方式获取元素集合"+elements);
        return elements;
    }

    /**
     * 关闭浏览器
     */
    public static void closeBrowser(){
        driver.quit();
        log.info("关闭浏览器");
    }

    /**
     * 关闭浏览器的window
     */
    public static void closeWindow(){
        driver.close();
        log.info("关闭window");
    }

    /**
     * 最大化浏览器
     */
    public static void maximizeWindow(){
        driver.manage().window().maximize();
        log.info("最大化浏览器窗口");
    }

    /**
     * driver等待
     */
    public static void waitingDriver(long seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.info("driver等待【"+seconds+"秒】");
    }

    /**
     * java线程等待
     */
    public static void waitingThread(double seconds){
        try {
            Thread.sleep((long)(seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("java线程等待"+seconds+"秒");
    }

    /**
     * 鼠标滚动条操作一般设置value=100000
     */
    public static void scrollWindow(long pxValue){
        String js = "document.documentElement.scrollTop = " + pxValue;
        ((JavascriptExecutor)driver).executeScript(js);
        log.info("滚动网页的上下滚动条,滚动参数为" +pxValue);
    }

    /**
     * 枚举类，为scrollWindow提供滚动方向枚举
     */
    public enum ScrollWindowEnum{
        UP,DOWN
    }

    /**
     *滚动屏幕，direction为负数则屏幕向上滚动，如果为正数则向下滚动
     */
    public static void scrollWindow(ScrollWindowEnum direction, int pxValue){
        if (pxValue == 0) {
            log.info("pxValue不能为0，为0直接返回");
            return;
        }
        String js = "window.scrollBy(0,"+pxValue+")";
        ((JavascriptExecutor)driver).executeScript(js);
        if(pxValue > 0){
            log.info("屏幕向下滚动【"+pxValue+"像素】");
        }else if (pxValue < 0 ) {
            log.info("屏幕向上滚动【"+pxValue+"像素】");
        }
    }


    /**
     * 模拟键盘操作
     */
    public static void keyboardOperation(Keys key){
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
        log.info("模拟键盘操作" + key.toString()+" 键");
    }

    /**
     * 判断元素是否显示,如果不显示，就等待直到timeout
     * @param element
     * @return
     */
    public static boolean isElementDisplay(final WebElement element){
        boolean wait = false;
        if(element ==null){
            return wait;
        }
        try {
            wait = new WebDriverWait(driver,TIME_OUT_SENCONDS)
                    .until(new ExpectedCondition<Boolean>() {
                        @Nullable
                        public Boolean apply(@Nullable WebDriver webDriver) {
                            return element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("元素等待超时设置为【"+TIME_OUT_SENCONDS+"秒】判断元素"+element+"是否显示出来【"+wait+"】");
        return wait;
    }

    /**
     * 鼠标悬浮事件
     */
    public static void hoverMouse(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        log.info("鼠标悬浮"+element.toString());
    }

    /**
     * 通过js鼠标悬浮
     */
    public static void hoverMouseByJs(WebElement element){
        String jsScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript,element);
        log.info("通过JS方法鼠标悬浮元素： "+element.toString());
    }

    /**
     * 鼠标右键
     */
    public static void rightClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).contextClick().build().perform();
        log.info("右键元素： "+element.toString());
    }

    /**
     * 鼠标双击
     */
    public static void doubleClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
        log.info("双击元素： "+ element.toString());
    }

    /**
     * 鼠标拖拽元素
     * @param sourceElementPlace
     * @param targetElementPlace
     */
    public static void dragAndDropElement(WebElement sourceElementPlace, WebElement targetElementPlace){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElementPlace, targetElementPlace).perform();
        log.info("将元素从 "+sourceElementPlace.toString()+"位置拖拽至"+targetElementPlace.toString()+"位置");
    }

    /**
     *具体元素区域截图
     */
    public static void snapShotElement(WebElement element,String eleScreenShotName){
        InputStream is = null;
        OutputStream os = null;
        try {
            File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Rectangle rect = element.getRect();
            BufferedImage fullImg = ImageIO.read(screenShot);
            BufferedImage elementScreenShot = fullImg.getSubimage(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            ImageIO.write(elementScreenShot,"png",screenShot);

            String dailyPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String screenShotRootFolder = System.getProperty("user.dir")+ File.separator+"screenshot";
            if(!new File(screenShotRootFolder).isDirectory()){
                new File(screenShotRootFolder).mkdir();
            }
            String screenFileName = eleScreenShotName+"_"+new SimpleDateFormat("yyyMMdd_HH:mm:ss").format(new Date())+".png";
            String target = screenShotRootFolder + File.separator + dailyPath;
            if(!new File(target).isDirectory()){
                new File(target).mkdir();
            }
            is = new FileInputStream(screenShot);
            os = new FileOutputStream(target+File.separator+screenFileName);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = is.read(buff, 0, buff.length)) != -1) {
                os.write(buff, 0, len);
            }
            log.info("selenium进行截图指定元素区域操作：【"+ target+File.separator+screenFileName+"】");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


        /**
         * 整体屏幕截屏，生成截图至当前工程下根目录的/screenshot/yyyyMMdd/截屏.png,screenShotName一般都是传caseName
         */
    public static void snapShotFullScreen(String screenShotName){
        String dailyPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String screenShotRootFolder = System.getProperty("user.dir")+ File.separator+"screenshot";
        if(!new File(screenShotRootFolder).isDirectory()){
            new File(screenShotRootFolder).mkdir();
        }
        File sourceScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        InputStream is = null;
        OutputStream os = null;
        String screenFileName = screenShotName+"_"+new SimpleDateFormat("yyyMMdd_HH:mm:ss").format(new Date())+".png";
        String target = screenShotRootFolder + File.separator + dailyPath;
        if(!new File(target).isDirectory()){
            new File(target).mkdir();
        }
        try {
            is = new FileInputStream(sourceScreen);
            os = new FileOutputStream(target+File.separator+screenFileName);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = is.read(buff, 0, buff.length)) != -1) {
                os.write(buff, 0, len);
            }
            log.info("selenium进行截图全屏操作：【"+ target+File.separator+screenFileName+"】");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * 获取windows,私有方法，为getWindowList()提供服务
     */
    private static Set<String> rememberWindow() {
        log.info("存放所有window窗口");
        return driver.getWindowHandles();
    }

    /**
     *获取所有的window窗口ids
     */
    public static List<String> getWindows() {
        List<String> wins = new ArrayList<String>();
        for(String s : rememberWindow()){
            wins.add(s);
        }
        log.info("获取window的集合"+wins);
        return wins;
    }

    /**
     *获取window窗口id
     */
    public static String getWindow(){
        return driver.getWindowHandle();
    }

    /**
     * 移除readOnly属性，这个方法几乎要被废弃了，现在前端很少有用到readOnly属性了
     */
    public static void removeReadOnly(String tagName){
        String jQuery = "$('" + tagName + "').removeAttr('readonly');";
        ((JavascriptExecutor) driver).executeScript(jQuery);

    }

    /**
     *切换至指定的window，这个方法跟getWindowList()结合，传list的索引即可
     */
    public static void switchToWindow(int window){
        driver.switchTo().window(getWindows().get(window));
        log.info("切换window至"+getWindows().get(window));
    }

    /**
     *获取元素的内容
     */
    public static String getElementContent(WebElement element){
        String text = element.getText();
        log.info("获取元素"+element.toString()+"的内容【"+text+"】");
        return text;
    }

    /**
     *获取元素的属性值
     */
    public static String getElementAttribute(WebElement element,String attr){
        String attrEle = element.getAttribute(attr);
        log.info("获取元素"+element.toString()+"属性"+attr+"的值为【"+attrEle+"】");
        return attrEle;
    }



}
