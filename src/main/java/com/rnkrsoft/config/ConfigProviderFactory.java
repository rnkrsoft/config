package com.rnkrsoft.config;

import com.rnkrsoft.config.properties.PropertiesConfigProvider;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rnkrsoft.com on 2019/1/6.
 */
public abstract class ConfigProviderFactory {
    private ConfigProviderFactory(){

    }
    private final static Map<String, ConfigProvider> INSTANCES = new HashMap<String, ConfigProvider>();

    /**
     * 构建属性文件的实例
     * @param name 配置文件名称
     * @return 配置提供者
     * @throws Exception 异常
     */
    public synchronized static ConfigProvider getPropertiesInstance(String name) throws Exception{
        return getInstance(PropertiesConfigProvider.class.getName(), name);
    }

    /**
     * 构建指定实现配置提供者的实例
     * @param clazz 配置提供者实现类
     * @param name 配置文件名称
     * @return 配置提供者
     * @throws Exception 异常
     */
    public synchronized static ConfigProvider getInstance(Class clazz, String name) throws Exception{
        if (INSTANCES.containsKey(name)) {
            return INSTANCES.get(name);
        } else {
            ConfigProvider instance = null;
            try {
                Constructor constructor = clazz.getConstructor(String.class);
                instance = (ConfigProvider) constructor.newInstance(name);
            } catch (Exception e) {
                throw e;
            }
            INSTANCES.put(name, instance);
            return instance;
        }
    }
    /**
     * 构建指定实现配置提供者的实例
     * @param className 配置提供者实现类
     * @param name 配置文件名称
     * @return 配置提供者
     * @throws Exception 异常
     */
    public synchronized static ConfigProvider getInstance(String className, String name) throws Exception{
        Class  clazz = Class.forName(className);
        return getInstance(clazz, name);
    }
}
