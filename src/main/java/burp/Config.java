package burp;

public class Config {
    public static boolean IS_RUNNING = false;
    public static String PROXY_HOST = "10.12.78.221";
    public static Integer PROXY_PORT = 8899;
    public static String PROXY_USERNAME = null;
    public static String PROXY_PASSWORD = null;
    public static Integer PROXY_TIMEOUT = 5000;
    public static String DOMAIN_REGX = "";
    public static String SUFFIX_REGX = "";

    public static Integer REQUEST_TOTAL = 0;
    public static Integer SUCCESS_TOTAL = 0;
    public static Integer FAIL_TOTAL = 0;
    
    public static Integer INTERVAL_TIME = 5000;
}
