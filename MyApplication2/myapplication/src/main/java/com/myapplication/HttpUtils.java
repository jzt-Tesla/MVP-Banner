package com.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zitaojiang on 2016/10/15.
 */

//利用单例模式，封装的Retrofit的网络请求和解析方法
public class HttpUtils {
    //创建HttpUtils对象
    private static volatile HttpUtils singletion;
    private Retrofit mRetrofit;
    private RetrofitService mRetrofitService;
    private HttpUtils(){

    }
    public static HttpUtils getSingletion(){
        if(singletion==null){
            synchronized (HttpUtils.class){
                if(singletion==null){
                    singletion=new HttpUtils();
                }
            }
        }
        return singletion;
    }
    //自定义retrofit实例
    private Retrofit createRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     *创建gson实例，用于retrofit解析返回的结果
     */
    private Gson createGson(){
        Gson gson=new GsonBuilder()
                .serializeNulls()
                .create();
        return gson;
    }

    /**
     * 创建网络接口的实例
     *
     * @return
     */
    private RetrofitService createPresent(){
        RetrofitService mRetrofitService=mRetrofit.create(RetrofitService.class);
        return mRetrofitService;
    }
            //创建你需要的解析的方法，传入参数，即可解析得到Call的实例对象
    public  void  getBannerData(String baseUrl, Map<String,String> params, Callback<Banner>callback){
        mRetrofit=createRetrofit(baseUrl);
        mRetrofitService=createPresent();
        Call<Banner > call=mRetrofitService.getData(params);
        call.enqueue(callback);
    }
}

