package com.myapplication;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zitaojiang on 2016/10/15.
 */

//定义接口回调方法，字符串拼接规则如下图
public interface RetrofitService {
    @GET(UrlConfig.Path.BannerURL)
    Call<Banner> getData(@QueryMap Map<String,String> params);

}
