package com.rnkrsoft.config;


/**
 * Created by rnkrsoft.com on 2019/1/6.
 * 配置提供者
 */
public interface ConfigProvider {
    /**
     * 初始化配置提供者
     * @param configDir 配置文件目录
     * @param reloadInterval 重新加载时间间隔
     */
    void init(String configDir, int reloadInterval);

    /**
     * 初始化配置提供者
     * @param reloadInterval 重新加载时间间隔
     */
    void init(int reloadInterval);

    /**
     * 重新加载
     */
    void reload();

    /**
     * 保存配置
     */
    void save();

    /**
     * 设置参数值
     * @param name 参数名
     * @param value 参数值
     */
    void param(String name, String value);

    /**
     * 获取参数
     * @param name 参数名
     * @param paramClass 参数值类型
     * @param <T> 参数值类型
     * @return 参数值
     */
    <T> T getParam(String name, Class<T> paramClass);

    /**
     * 获取指定参数名的数组值
     * @param name 参数名
     * @return 参数值
     */
    String[] getArray(String name);

    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @return 参数值
     */
    String getString(String name);
    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @param defaultValue 如果为null时返回默认值
     * @return 参数值
     */
    String getString(String name, String defaultValue);

    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @return 参数值
     */
    Integer getInteger(String name);
    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @param defaultValue 如果为null时返回默认值
     * @return 参数值
     */
    Integer getInteger(String name, Integer defaultValue);

    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @return 参数值
     */
    Long getLong(String name);
    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @param defaultValue 如果为null时返回默认值
     * @return 参数值
     */
    Long getLong(String name, Long defaultValue);

    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @return 参数值
     */
    Double getDouble(String name);
    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @param defaultValue 如果为null时返回默认值
     * @return 参数值
     */
    Double getDouble(String name, Double defaultValue);

    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @return 参数值
     */
    Boolean getBoolean(String name);
    /**
     * 获取指定参数名的字符串值
     * @param name 参数名
     * @param defaultValue 如果为null时返回默认值
     * @return 参数值
     */
    Boolean getBoolean(String name, Boolean defaultValue);
}
