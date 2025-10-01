package com.moyan.context;

/**
 * BaseContext类用于管理线程局部变量(ThreadLocal)的上下文信息
 * 主要用于在多线程环境下存储和获取当前线程的ID值
 */
public class BaseContext {

    // 使用ThreadLocal存储Long类型的ID值，确保每个线程都有自己的独立副本
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的ID值
     * @param id 要设置的ID值
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前线程的ID值
     * @return 当前线程存储的ID值，如果不存在则返回null
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 移除当前线程的ID值
     * 用于防止内存泄漏，在线程使用完毕后应调用此方法
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
