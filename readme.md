# 配置文件库

[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.rnkrsoft.config/config/badge.svg)](http://search.maven.org/#search|ga|1|g%3A%22com.rnkrsoft.config%22%20AND%20a%3A%22config%22)

```xml
<dependency>
    <groupId>com.rnkrsoft.config</groupId>
    <artifactId>config</artifactId>
    <version>最新版本号</version>
</dependency>
```



配置文件提供者

```java
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

```



通过工厂类构建实现

```java
//构建配置提供者test.properties
final ConfigProvider configProvider = ConfigProviderFactory.getPropertiesInstance("test");
configProvider.param("MONITOR_APPLICATIONID", "default");
configProvider.param("MONITOR_ENVIRONMENTID", "1");
//每隔2秒钟热加载一次
configProvider.init("src/test/resources", 2);
Thread t = new Thread(){
    @Override
    public void run() {
        while (true){
            Long value = configProvider.getLong("MONITOR_ENVIRONMENTID", 0L);
            System.out.println(value);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
};
t.start();
Thread.sleep(60 * 1000);
```

