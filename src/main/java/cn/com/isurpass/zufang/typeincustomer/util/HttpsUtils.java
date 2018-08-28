package cn.com.isurpass.zufang.typeincustomer.util;

import cn.com.isurpass.zufang.typeincustomer.dao.SystemParameterDAO;
import cn.com.isurpass.zufang.typeincustomer.po.SystemParameterPO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HttpsUtils {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    private static String dbtoken = "";
    private static final String dbkey = "zufang_key";
    private static SystemParameterDAO spd;
    private static HttpsUtils instance = new HttpsUtils();

    public static String restUrl;
    public static String restCode;
    public static String restPassword;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * httpClient post请求
     * @param url    请求url
     * @param header 头部信息
     * @param param  请求参数 form提交适用
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     */
    public static String post(String url, Map<String, String> header, Map<String, String> param, HttpEntity entity){
        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // 设置头信息
            if (MapUtils.isNotEmpty(header)) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置请求参数
            if (MapUtils.isNotEmpty(param)) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    //给参数赋值
                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 设置实体 优先级高
            if (entity != null) {
                httpPost.setEntity(entity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                readHttpResponse(httpResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        return httpClient;
    }

    public static String readHttpResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        builder.append("status:" + httpResponse.getStatusLine());
        builder.append("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            builder.append("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append("response length:" + responseString.length());
            builder.append("response content:" + responseString.replace("\r\n", ""));
        }
        return builder.toString();
    }

    public static String readFingerpring(Integer zwavedeviceid,String url){
        String token = getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("zwavedeviceid", zwavedeviceid.toString());
        String post = HttpsUtils.post(url, null, map, null);
        JSONObject jo = JSONObject.parseObject(post);
        if (jo == null || jo.getInteger("resultCode") == 30300) {
            instance.login();
            SystemParameterPO sp = new SystemParameterPO(dbkey,AES.encrypt2Str(dbtoken));
            spd.save(sp);
            map.put("token", dbtoken);
            map.put("zwavedeviceid", zwavedeviceid.toString());
            return HttpsUtils.post(url, null, map, null);
        }
        return post;
    }
    public static String readFingerpring(Integer zwavedeviceid){
        String url = restUrl+"thirdpart/zufang/readfingerpring";
        return readFingerpring(zwavedeviceid,url);
    }
    public static String queryStatusOfReadFingerpring(Integer zwavedeviceid){
        String url = restUrl+"thirdpart/zufang/querystatusofreadfingerpring";
        return queryStatusofReadFingerpring(zwavedeviceid, url);
    }
    public static String queryStatusofReadFingerpring(Integer zwavedeviceid,String url){
        String token = getToken();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("zwavedeviceid", zwavedeviceid.toString());
        String post = HttpsUtils.post(url, null, map, null);
        JSONObject jo = JSONObject.parseObject(post);
        if (jo == null || jo.getInteger("resultCode") == 30300) {
            instance.login();
            SystemParameterPO sp = new SystemParameterPO(dbkey,AES.encrypt2Str(dbtoken));
            spd.save(sp);
            map.put("token", dbtoken);
            map.put("zwavedeviceid", zwavedeviceid.toString());
            return HttpsUtils.post(url, null, map, null);
        }
        return post;
    }

    public static String getToken(){
        SystemParameterPO systemparameter = spd.findByStrkey(dbkey);
        if(StringUtil.checkNull(systemparameter)||StringUtil.checkNull(systemparameter.getStrvalue())){
            instance.login();
        }else{
            dbtoken = AES.decrypt2Str(systemparameter.getStrvalue());
            if(dbtoken==null){
                instance.login();
            }
        }
        return HttpsUtils.dbtoken;
    }
    @Transactional
    public synchronized void login(){
        String code =restCode;
        String password = restPassword;

        Map<String , String> pmap = new HashMap<String , String>();
        pmap.put("code", code);
        pmap.put("password", password);
        String str = HttpsUtils.post(restUrl + "thirdpart/login",null, pmap,null);
        Map map = null;
        try {
            map = JSON.parseObject(str,HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Server connection failed");
        }
        if(StringUtil.checkNull(map.get("resultCode")) || StringUtil.checkNull(map.get("token"))){
            throw new RuntimeException("Login to third-party platform failed");
        }
        int resultCode = Integer.parseInt(map.get("resultCode").toString());
        if(resultCode != 0){
            throw new RuntimeException("Login to third-party platform failed");
        }
        dbtoken = map.get("token").toString();
        SystemParameterPO sp = new SystemParameterPO(dbkey,AES.encrypt2Str(dbtoken));
        spd.save(sp);
    }

    @Autowired
    public void setSpd(SystemParameterDAO spd) {
        HttpsUtils.spd = spd;
    }
    @Value("${restUrl}")
    public void setRestUrl(String restUrl) {
        HttpsUtils.restUrl = restUrl;
    }
    @Value("${restCode}")
    public void setRestCode(String restCode) {
        HttpsUtils.restCode = restCode;
    }
    @Value("${restPassword}")
    public void setRestPassword(String restPassword) {
        HttpsUtils.restPassword = restPassword;
    }
}