package com.ai.page.support.util;

/**
 * @author: lixuejun
 * @date: Create in 2020/5/14
 * @description:
 */
public class ThreadLocalUtil<T> {

    public void setThreadValue(ThreadLocal<T> threadLocal, T value) {
        if (threadLocal.get() == null) {
            threadLocal.set(value);
        }
    }

    /**
     * 获得当前线程变量的值
     *
     * @param threadLocal 线程名
     * @return 返回当前线程的值
     */
    public T getThreadValue(ThreadLocal<T> threadLocal) {
        return threadLocal.get();
    }

}
