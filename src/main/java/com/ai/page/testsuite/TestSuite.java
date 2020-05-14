package com.ai.page.testsuite;

import com.ai.page.testcase.impl.TC01_open;
import com.ai.page.testcase.impl.TC02_open;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public class TestSuite {

    @Test()
    public void tc01_open(){
        TC01_open tc01_open = new TC01_open();
        tc01_open.runScript(new HashMap<String, String>());
    }

    @Test()
    public void tc02_open(){
        TC02_open tc02_open = new TC02_open();
        tc02_open.runScript(new HashMap<String, String>());

    }
}
