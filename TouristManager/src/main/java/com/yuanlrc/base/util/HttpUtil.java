package com.yuanlrc.base.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https请求
 * @author SiruiYao
 *
 */
public class HttpUtil {

    public static String sendPost(String curl, Map<String, String> headerPara,String param) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        try {
            //创建连接
            URL url = new URL(curl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true); //是否打开outputStream 相对于程序，即我们向远程服务器写入数据，默认为false，不打开
            connection.setDoInput(true);  //输入流，获取到返回的响应内容， 默认为true，所以get请求时可以不设置这个连接信息 
            connection.setRequestMethod("POST"); //发送请求的方式
            connection.setUseCaches(false); //不使用缓存
            connection.setInstanceFollowRedirects(true); //重定向，一般浏览器才需要
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8"); //设置服务器解析数据的方式
            if(headerPara != null){
                for(Entry<String, String> entry : headerPara.entrySet()){
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.connect();

            //POST请求
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            out.write(param);
            out.flush();
            out.close();

            //读取响应
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Http请求方法内部问题");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /** 编码格式。发送编码格式统一用UTF-8 */
    public static String ENCODING = "UTF-8";

    /**
     *
     * @Title httpPostMap
     * @Description 基于HttpClient 4.3的通用POST方法,参数为map对象
     * @param url 请求url
     * @param param 参数，map对象
     * @return
     * @version
     */
    public static String httpPostMap(String url, Map<String, String> param) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 1.封装查询参数
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> iter = param.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                paramList.add(new BasicNameValuePair(me.getKey(), me.getValue()));
            }

            // 2.获取链接
            HttpPost method = new HttpPost(url);
            // 设置连接超时，连接超时指的是连接一个url的连接等待时间。，时间为3秒
            // 设置读取数据超时，读取超时指的是连接上一个url，获取response的返回等待时间
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
            method.setConfig(requestConfig);
            method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));

            // 执行请求
            response = client.execute(method);
            // 获取返回结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, ENCODING);
                }
            }

        } catch (HttpHostConnectException e) {
            result = "{\"status\":\"500\",\"info\":\"找不到访问地址\"}";
        } catch (UnknownHostException e) {
            result = "{\"status\":\"500\",\"info\":\"找不到访问地址\"}";
        } catch (SocketTimeoutException e) {
            result = "{\"status\":\"500\",\"info\":\"连接超时\"}";
        } catch (ConnectTimeoutException e) {
            result = "{\"status\":\"500\",\"info\":\"连接超时\"}";
        } catch (Exception e) {
            result = "{\"status\":\"500\",\"info\":\"连接失败\"}";
        } finally {
            try {
                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * 基于HttpClient 4.3的通用GET方法
     *
     * @Title httpGetMap
     * @Description 基于HttpClient 4.3的通用GET方法
     * @param url 请求url
     * @param param 参数，map对象
     * @return
     * @version
     */
    public static String httpGetMap(String url, Map<String, String> param) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 1.封装参数
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iter = param.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                paramList.add(new BasicNameValuePair(me.getKey(), me.getValue()));
            }
            // 转换为键值对
            String param_url = EntityUtils.toString(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
            // 2.获取链接
            HttpGet httpget = new HttpGet(url + "?" + param_url);
            // 设置连接超时，连接超时指的是连接一个url的连接等待时间。，时间为15秒
            // 设置读取数据超时，读取超时指的是连接上一个url，获取response的返回等待时间
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, ENCODING);
            }
        } catch (HttpHostConnectException e) {
            result = "{\"status\":\"500\",\"info\":\"找不到访问地址\"}";
        } catch (UnknownHostException e) {
            result = "{\"status\":\"500\",\"info\":\"找不到访问地址\"}";
        } catch (SocketTimeoutException e) {
            result = "{\"status\":\"500\",\"info\":\"连接超时\"}";
        } catch (ConnectTimeoutException e) {
            result = "{\"status\":\"500\",\"info\":\"连接超时\"}";
        } catch (Exception e) {
            result = "{\"status\":\"500\",\"info\":\"连接失败\"}";
        } finally {
            try {
                if (response != null) {
                    response.close();
                }

            } catch (Exception e) {
            }
        }
        return result;
    }

}
