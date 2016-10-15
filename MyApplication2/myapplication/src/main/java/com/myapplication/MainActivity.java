package com.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//MainActivity为View，只能与Presenter之间相互沟通。在View中必须实例化Presenter。

public class MainActivity extends AppCompatActivity implements Contract.View {
    private com.youth.banner.Banner  banner;
    private  Contract.Presenter mPresenter;
    private  Map<String,String> params=new HashMap<>();
    private List<String> bannerImgUrl=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner= (com.youth.banner.Banner) findViewById(R.id.banner);
        //向下转型，父类引用指向子类对象，实例化Presenter
        mPresenter=new MainPresenter(this);
        //传递拼接字符串地址到Presenter
        initPresenter();

    }

    //拼接字符，将拼接的结果传入Presenter，最终传入Model，利用Model网络请求和解析JSON网址。
    private void initPresenter() {
        params.put(UrlConfig.Key.V,UrlConfig.DefaultValue.V);
        mPresenter.putBannerData(params);
    }

    //对banner进行具体的设置，详情代码讲解请参考-----> github轮播图Banner(上面有我贴出的网址)
    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setDelayTime(3000);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(bannerImgUrl);
        banner.start();

       /* 因为我是在ViewPager里面的其中一个fragment中嵌套的Banner，所以需要对bannerImgUrl
        进行清除，不然会出现很多不必要的麻烦，比如我轮播图会多出很多小圆点。*/
        bannerImgUrl.clear();
    }

    @Override
    public void getDataSuccessful(List<Banner.AdListBean> adListBeen) {
        //将图片网址添加到bannerImgUrl集合中
            for (Banner.AdListBean list:adListBeen){
                bannerImgUrl.add(list.getImgUrl());
            }
        //调用轮播图方法，最终达到轮播的效果
        initBanner();
    }

    @Override
    public void getDataFail(String error) {
        Log.i("TAG","----错误是-----"+error);
    }


}
