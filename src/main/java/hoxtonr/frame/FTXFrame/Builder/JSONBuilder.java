package hoxtonr.frame.FTXFrame.Builder;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;


public class JSONBuilder {
    public String buildJson(HashMap<String,String> OriginHash){
        StringBuilder build = new StringBuilder();
        build.append("{");
        for(String c : OriginHash.keySet()){
            if(StringUtils.isNumeric(OriginHash.get(c).replaceAll("\\.","")) || OriginHash.get(c).equals("true")||OriginHash.get(c).equals("false")|| OriginHash.get(c).equals("null")) {
                build.append("\"").append(c).append("\"").append(": ").append(OriginHash.get(c)).append(",");
            }else{
                build.append("\"").append(c).append("\"").append(": ").append("\"").append(OriginHash.get(c)).append("\"").append(",");
            }
        }
        if(build.substring(build.length()-1).equals(",")){
            build.deleteCharAt(build.length()-1);
        }
        build.append("}");
        return build.toString();
    }
    public static void main(String[] args){
        HashMap<String,String> hash = new HashMap<>();
        hash.put("name","张三");
        hash.put("name2","李四");
        hash.put("name3","true");
        hash.put("name4","56.568");
        JSONBuilder b = new JSONBuilder();
        System.out.println(b.buildJson(hash));
    }
}

