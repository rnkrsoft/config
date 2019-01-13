package com.rnkrsoft.config;

/**
 * Created by rnkrsoft.com on 2019/1/6.
 */
public abstract class AbstractConfigProvider implements ConfigProvider {

    protected <T> T convert(Object value, Class<T> paramClass) {
        try {
            if (paramClass == String.class) {
                return (T) (value == null ? null : value.toString());
            }
            if (paramClass == Integer.class || paramClass == Integer.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Integer(value.toString());
                }
            }
            if (paramClass == Long.class || paramClass == Long.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Long(value.toString());
                }
            }
            if (paramClass == Double.class || paramClass == Double.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Double(value.toString());
                }
            }
            if (paramClass == Boolean.class || paramClass == Boolean.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Boolean(value.toString());
                }
            }
            throw new IllegalArgumentException("无效参数类型'" + paramClass + "'");
        } catch (Exception e) {
            return null;
        }
    }

    public String[] getArray(String paramName) {
        String value = getString(paramName, "");
        if (value.isEmpty()) {
            return new String[0];
        }
        String[] str = value.split(";");
        return str;
    }

    public String getString(String name, String defaultValue) {
        String value = getParam(name, String.class);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public Integer getInteger(String name, Integer defaultValue) {
        Integer value = getParam(name, Integer.class);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    @Override
    public Long getLong(String name, Long defaultValue) {
        Long value = getParam(name, Long.class);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    @Override
    public Double getDouble(String name, Double defaultValue) {
        Double value = getParam(name, Double.class);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    @Override
    public String getString(String name) {
        return getString(name, null);
    }

    @Override
    public Integer getInteger(String name) {
        return getInteger(name, 0);
    }

    @Override
    public Long getLong(String name) {
        return getLong(name, 0L);
    }

    @Override
    public Double getDouble(String name) {
        return getDouble(name, 0D);
    }

    @Override
    public Boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    public Boolean getBoolean(String name, Boolean defaultValue) {
        Boolean value = getParam(name, Boolean.class);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }
}
