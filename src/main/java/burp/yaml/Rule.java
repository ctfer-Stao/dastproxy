package burp.yaml;

import java.util.HashMap;
import java.util.Map;

public class Rule {

    private  Integer PROXY_TIMEOUT;
    private  String DOMAIN_REGX;
    private  String SUFFIX_REGX;
    private  String Include_SUFFIX_REGX;
    private  Integer INTERVAL_TIME;
    private  String PROXY_HOST;
    private  Integer PROXY_PORT;



    private String TaskID;


    public Map<String, Object> getRuleObjMap(){
        Map<String,Object> r = new HashMap<>();
        r.put("PROXY_TIMEOUT", PROXY_TIMEOUT);
        r.put("DOMAIN_REGX", DOMAIN_REGX);
        r.put("SUFFIX_REGX", SUFFIX_REGX);
        r.put("Include_SUFFIX_REGX", Include_SUFFIX_REGX);
        r.put("INTERVAL_TIME", INTERVAL_TIME);
        r.put("PROXY_HOST", PROXY_HOST);
        r.put("PROXY_PORT", PROXY_PORT);
        r.put("TaskID", TaskID);
        return r;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getPROXY_HOST() {
        return PROXY_HOST;
    }

    public void setPROXY_HOST(String PROXY_HOST) {
        this.PROXY_HOST = PROXY_HOST;
    }

    public Integer getPROXY_PORT() {
        return PROXY_PORT;
    }

    public void setPROXY_PORT(Integer PROXY_PORT) {
        this.PROXY_PORT = PROXY_PORT;
    }

    public Integer getPROXY_TIMEOUT() {
        return PROXY_TIMEOUT;
    }

    public void setPROXY_TIMEOUT(Integer PROXY_TIMEOUT) {
        this.PROXY_TIMEOUT = PROXY_TIMEOUT;
    }

    public String getDOMAIN_REGX() {
        return DOMAIN_REGX;
    }

    public void setDOMAIN_REGX(String DOMAIN_REGX) {
        this.DOMAIN_REGX = DOMAIN_REGX;
    }

    public String getSUFFIX_REGX() {
        return SUFFIX_REGX;
    }

    public void setSUFFIX_REGX(String SUFFIX_REGX) {
        this.SUFFIX_REGX = SUFFIX_REGX;
    }

    public String getInclude_SUFFIX_REGX() {
        return Include_SUFFIX_REGX;
    }

    public void setInclude_SUFFIX_REGX(String Include_SUFFIX_REGX) {
        this.Include_SUFFIX_REGX = Include_SUFFIX_REGX;
    }

    public Integer getINTERVAL_TIME() {
        return INTERVAL_TIME;
    }

    public void setINTERVAL_TIME(Integer INTERVAL_TIME) {
        this.INTERVAL_TIME = INTERVAL_TIME;
    }





}
