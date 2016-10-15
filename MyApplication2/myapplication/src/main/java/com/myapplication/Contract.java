package com.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;


/**
 * Created by zitaojiang on 2016/10/15.
 */

//简单来讲Contract就是一个MVP中M、V、P之间相互用数据交流的桥梁，其通过的是方法的实现。
public class Contract {
    public  interface  View{
        //访问网络数据成功
        void getDataSuccessful(List<Banner.AdListBean> adListBeen);
        //访问网络数据失败
        void getDataFail(String error);
                             }
    public  interface  Model{
        //传入参数，获得数据
        Void getBannerData(Map<String,String> params,Callback<Banner> callback);
                             }
    public  interface  Presenter{
        //将params传入到presenter中去，因为View与Model交互，所以只能
        //传给Presenter，然后再调用上面的那个方法，将params传入到上面的方法中去
        void  putBannerData(Map<String,String> params);
                                }
}
