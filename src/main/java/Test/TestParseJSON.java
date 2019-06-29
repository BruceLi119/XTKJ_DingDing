package Test;

import com.alibaba.fastjson.JSONObject;

public class TestParseJSON {
    public static void  main(String[] args){
        String params="{\"channelCode\":\"bbb\",\"accountNo\":\"121300000932\",\"message\":\"字符信息解密成功\",\"status\":\"1\"}";

        JSONObject pa=JSONObject.parseObject(params);

        //可以使用parseObject(params，Class<T> clazz)直接转换成需要的Bean
        System.out.println(pa.getString("params"));
    }
}
