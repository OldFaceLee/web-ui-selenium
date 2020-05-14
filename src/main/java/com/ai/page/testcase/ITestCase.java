package com.ai.page.testcase;

import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public interface ITestCase {
    /**
     * 初始化跟用例相关的测试数据
     */
    public void initTestData();

    /**
     * case的具体脚本执行
     * @param excelData
     */
    public void runScript(Map<String,String> excelData);

    /**
     * 销毁与用例相关的测试数据
     */
    public void destroyTestData();
}
