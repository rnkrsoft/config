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
     */
    public synchronized static ConfigProvider getPropertiesInstance(String name){
        return getInstance(PropertiesConfigProvider.class.getName(), name);
    }

    /**
     * 构建指定实现配置提供者的实例
     * @param clazz 配置提供者实现类
     * @param name 配置文件名称
     * @return 配置提供者
     */
    public synchronized static ConfigProvider getInstance(Class clazz, String name){
        if (INSTANCES.containsKey(name)) {
            return INSTANCES.get(name);
        } else {
            ConfigProvider instance = null;
            try {
                Constructor constructor = clazz.getConstructor(String.class);
                instance = (ConfigProvider) constructor.newInstance(name);
            } catch (Exception e) {
                throw new RuntimeException("构建配置提供者发生异常", e);
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
     */
    public synchronized static ConfigProvider getInstance(String className, String name){
        Class  clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("构建配置提供者发生异常", e);
        }
        return getInstance(clazz, name);
    }
}
