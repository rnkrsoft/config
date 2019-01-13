package com.rnkrsoft.config.properties;

import com.rnkrsoft.config.AbstractConfigProvider;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by rnkrsoft.com on 2019/1/6.
 */
public class PropertiesConfigProvider extends AbstractConfigProvider implements Runnable {

    final Properties properties = new Properties();
    String name;
    long lastModified = 0L;
    File file;
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    public PropertiesConfigProvider(String name) {
        this.name = name;
    }

    /**
     * 初始化配置
     *
     * @param configDir      配置目录
     * @param reloadInterval 监控配置变换间隔
     */
    public void init(String configDir, int reloadInterval) {
        this.file = new File(configDir, this.name + ".properties");
        init(reloadInterval);
    }

    @Override
    public void init(int reloadInterval) {
        reload();
        scheduledThreadPool.scheduleWithFixedDelay(this, reloadInterval, reloadInterval, TimeUnit.SECONDS);
    }

    @Override
    public void reload() {
        InputStream is = null;
        try {
            System.out.println("reload config : " + file.getCanonicalPath());
            if (!file.exists()) {
                System.err.println(file.getCanonicalPath() + " is not found!");
                save();
                lastModified = file.lastModified();
                return;
            }
            is = new FileInputStream(file);
            properties.load(is);
            lastModified = file.lastModified();
        } catch (IOException e) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save() {
        FileOutputStream fos = null;
        try {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fos = new FileOutputStream(file);
            properties.store(fos, name);
        } catch (Exception e) {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void param(String name, String value) {
        properties.setProperty(name, value);
    }


    public <T> T getParam(String paramName, Class<T> paramClass) {
        Object value = properties.getProperty(paramName);
        return convert(value, paramClass);
    }

    @Override
    public void run() {
        if (file.lastModified() > lastModified){
            reload();
        }
    }
}
