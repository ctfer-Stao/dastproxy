package burp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String getBanner(){
        String bannerInfo =
                "[+] " + BurpExtender.extensionName + " is loaded\n";
        return bannerInfo;
    }

    public static boolean isMathch(String regx,String str){
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);//正则判断
        Matcher mc= pat.matcher(str);//条件匹配
        if(mc.find()){
            return true;
        }else{
            return false;
        }
    }

    public static void updateSuccessCount(){
        synchronized(Config.FAIL_TOTAL){
            Config.REQUEST_TOTAL++;
            Config.SUCCESS_TOTAL++;
            GUI.lbRequestCount.setText(String.valueOf(Config.REQUEST_TOTAL));
            GUI.lbSuccesCount.setText(String.valueOf(Config.SUCCESS_TOTAL));
        }
    }

    public static void updateFailCount(){
        synchronized(Config.SUCCESS_TOTAL){
            Config.REQUEST_TOTAL++;
            Config.FAIL_TOTAL++;
            GUI.lbRequestCount.setText(String.valueOf(Config.REQUEST_TOTAL));
            GUI.lbFailCount.setText(String.valueOf(Config.FAIL_TOTAL));
        }
    }
}
