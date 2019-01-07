package com.rnkrsoft.config;

import org.junit.Test;

/**
 * Created by rnkrsoft.com on 2019/1/6.
 */
public class ConfigProviderFactoryTest {

    @Test
    public void testGetPropertiesInstance1() throws Exception {
        ConfigProvider configProvider = ConfigProviderFactory.getPropertiesInstance("test");
        configProvider.param("MONITOR_APPLICATIONID", "default");
        configProvider.param("MONITOR_ENVIRONMENTID", "1");
        configProvider.init("src/test/resources", 60);
        configProvider.save();
    }

    @Test
    public void testGetPropertiesInstance2() throws Exception {
        final ConfigProvider configProvider = ConfigProviderFactory.getPropertiesInstance("test");
        configProvider.init("src/test/resources", 2);
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                    Long value = configProvider.getLong("MONITOR_ENVIRONMENTID", 0l);
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
    }
}