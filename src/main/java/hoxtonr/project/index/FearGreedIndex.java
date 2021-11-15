package hoxtonr.project.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hoxtonr.project.index.model.fearGreedIndexModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FearGreedIndex {
    private final String endPoint = "https://api.alternative.me/fng/";
    private String limit;
    private String format;
    private String data_format;
    public FearGreedIndex(){
        this.limit = "";
        this.format = "";
        this.data_format = "";
    }
    public FearGreedIndex(int limit){
        this.limit = "?limit="+limit;
        this.format = "";
        this.data_format = "";
    }
    public FearGreedIndex(int limit, String format, String data_format){
        this.limit = "?limit="+limit;
        this.format = format;
        this.data_format = data_format;
    }
    private String getFearGreedIndexInternal(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(endPoint+limit+format+data_format);
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
    public List<fearGreedIndexModel> getFearGreedIndex(){
        String c = getFearGreedIndexInternal();
        List<fearGreedIndexModel> fgi = new ArrayList<>();
        JSONArray a = JSONObject.parseObject(c).getJSONArray("data");
        for(int i = 0; i<a.size(); i ++){
           fearGreedIndexModel m = new fearGreedIndexModel();
           m.setValue(a.getJSONObject(i).getString("value"));
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date d = new Date(Long.parseLong(a.getJSONObject(i).getString("timestamp"))*1000);
           m.setValueClassification(a.getJSONObject(i).getString("value_classification"));
           m.setTimestamp(df.format(d));
           m.setTimeUntilUpdate(a.getJSONObject(i).getString("time_until_update"));
           fgi.add(m);
        }
        return fgi;
    }
    public static void main(String[] args){
        FearGreedIndex f = new FearGreedIndex(10);
        System.out.println(f.getFearGreedIndex());
    }
}
