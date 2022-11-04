package burp.yaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LoadConfig {
    private static final Yaml yaml = new Yaml();
    private static String ParentConfigPath = String.format("%s", System.getProperty("user.home"));
    private static String ConfigPath =  String.format("%s/%s", ParentConfigPath, "Dast_Proxy_Config.yml");
    public LoadConfig() {
        // 构造函数，初始化配置
//        File ParentConfigPathFile = new File(ParentConfigPath);
//        if (!(ParentConfigPathFile.exists() && ParentConfigPathFile.isDirectory())) {
//            ParentConfigPathFile.mkdirs();
//        }

        File settingPathFile = new File(ConfigPath);
        if (!(settingPathFile.exists() && settingPathFile.isFile())) {
            initRules();
        }
    }
    // 初始化规则配置
    public void initRules() {
        Rule rule = new Rule();
        rule.setDOMAIN_REGX("");
        rule.setInclude_SUFFIX_REGX("");
        rule.setPROXY_HOST("10.129.112.8");
        rule.setPROXY_PORT(80);
        rule.setPROXY_TIMEOUT(5000);
        rule.setINTERVAL_TIME(5000);
        rule.setSUFFIX_REGX("");
        rule.setTaskID("");

        DumperOptions dop = new DumperOptions();
        dop.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
//        Representer representer = new Representer();
//        representer.addClassTag(Rule.class, Tag.MAP);


        Yaml yaml = new Yaml(dop);
        File f = new File(ConfigPath);
        Map r =rule.getRuleObjMap();
        try{
            Writer ws = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
            yaml.dump(r,ws);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static String getConfigPath(){
        return ConfigPath;

    }
    public static Map loadRule(String ConfigPathFile){
        Yaml yaml = new Yaml();

        try {
            File settingPathFile = new File(ConfigPathFile);
            return yaml.load(new FileInputStream(settingPathFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return null;
        }



    }
}
