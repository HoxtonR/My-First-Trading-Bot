package hoxtonr.project.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.project.index.model.JinShiNewsModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JinShiNews {
    private String getJinShiNewsInternal(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://www.jin10.com/flash_newest.js?t="+System.currentTimeMillis());
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public List<JinShiNewsModel> getJinShiNews() throws IOException {
        String c = getJinShiNewsInternal().replace("var newest = ","");
        List<JinShiNewsModel> jinShiNews = new ArrayList<>();
        if(c.contains("important")) {
            JSONArray array = JSONObject.parseArray(c.replace(c.substring(c.lastIndexOf(";")), ""));
            for (int i = 0; i < array.size(); i++) {
                JinShiNewsModel j = new JinShiNewsModel();
                JinShiNewsModel.DataDTO d = new JinShiNewsModel.DataDTO();
                d.setContent(array.getJSONObject(i).getJSONObject("data").getString("content"));
                d.setPic(array.getJSONObject(i).getJSONObject("data").getString("pic"));
                j.setData(d);
                j.setId(array.getJSONObject(i).getString("id"));
                j.setTime(array.getJSONObject(i).getString("time"));
                j.setType(array.getJSONObject(i).getInteger("type"));
                j.setImportant(array.getJSONObject(i).getInteger("important"));
                jinShiNews.add(j);
            }
        }else{
            throw new IOException();
        }
        return jinShiNews;
    }
    public static void main(String[] args) throws IOException {
        JinShiNews news = new JinShiNews();
        System.out.println(news.getJinShiNews());
    }
}
