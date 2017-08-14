// 2011-10-10 - frank  timestamp/sessionid added
// 2011-08-18 - frank

package com.skrill.viewpoint.jobs.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> data;

    public Setting() {
        data = new HashMap<String, Object>();

        data.put("settingKey", null);
        data.put("description", null);
        data.put("stringValue", null);
        data.put("dateValue", null);
        data.put("doubleValue", null);
        data.put("intValue", null);
        data.put("boolValue", null);
    }

    public Setting(String settingKey, String description, String stringValue, Timestamp dateValue, Double doubleValue, Integer intValue, Boolean boolValue) {
        data = new HashMap<String, Object>();

        data.put("settingKey", settingKey);
        data.put("description", description);
        data.put("stringValue", stringValue);
        data.put("dateValue", dateValue);
        data.put("doubleValue", doubleValue);
        data.put("intValue", intValue);
        data.put("boolValue", boolValue);
    }

    public String getSettingKey() {
        return (String) data.get("settingKey");
    }

    public void setSettingKey(String settingKey) {
        data.put("settingKey", settingKey);
    }

    public String getDescription() {
        return (String) data.get("description");
    }

    public void setDescription(String description) {
        data.put("description", description);
    }

    public String getStringValue() {
        return (String) data.get("stringValue");
    }

    public void setStringValue(String stringValue) {
        data.put("stringValue", stringValue);
    }

    public Timestamp getDateValue() {
        return (Timestamp) data.get("dateValue");
    }

    public void setDateValue(Timestamp dateValue) {
        data.put("dateValue", dateValue);
    }

    public Double getDoubleValue() {
        return (Double) data.get("doubleValue");
    }

    public void setDoubleValue(double doubleValue) {
        data.put("doubleValue", doubleValue);
    }

    public Integer getIntValue() {
        return (Integer) data.get("intValue");
    }

    public void setIntValue(int intValue) {
        data.put("intValue", intValue);
    }

    public Boolean getBoolValue() {
        return (Boolean) data.get("boolValue");
    }

    public void setBoolValue(Boolean boolValue) {
        data.put("boolValue", boolValue);
    }

}
