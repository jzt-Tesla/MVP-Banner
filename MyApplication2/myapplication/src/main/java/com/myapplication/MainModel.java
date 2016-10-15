package com.myapplication;

import java.util.Map;

import retrofit2.Callback;

/**
 * Created by zitaojiang on 2016/10/15.
 */

//MainModel为Model，只能与Presenter之间相互沟通。
public class MainModel implements Contract.Model {
    @Override
    public Void getBannerData(Map<String, String> params, Callback<Banner> callback) {
        //调用封装好的Retrofit工具类中的网络解析方法，传入各个参数，利用接口回调得到解析后的实体bean
        HttpUtils.getSingletion().getBannerData(UrlConfig.Path.BASEURL,params,callback);
        return null;
    }
}

