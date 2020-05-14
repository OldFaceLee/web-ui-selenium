package com.ai.page.testsuite;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public class BaseSuite {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setParallel(XmlSuite.ParallelMode.METHODS);
        testNG.setThreadCount(2);
        testNG.setTestClasses(new Class[]{TestSuite.class});
        testNG.setPreserveOrder(true);
        testNG.run();

    }
}
