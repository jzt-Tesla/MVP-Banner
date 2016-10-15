
##准备工作：
1.  利用github第三方添加轮播图
2. 基于MVP模式搭建架构
3. 调试

###添加轮播图：
可参考：[github轮播图Banner](https://github.com/youth5201314/banner)

###添加依赖：

```
//添加Retrofit的依赖和Glide的依赖和banner的依赖
compile 'com.youth.banner:banner:1.4.1' 
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'
compile 'com.github.bumptech.glide:glide:3.7.0'
```

###在AndroidManifest.xml中添加用户权限：
```
<!-- 添加网络访问权限 -->
<uses-permission android:name="android.permission.INTERNET" /> 

<!-- 从网络加载图片的权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
###banner在Xml文件的布局：
```

//头部的地方添加：
 xmlns:banner="http://schemas.android.com/apk/res-auto"
 //Banner布局页面
<com.youth.banner.Banner
android:id="@+id/banner"
android:layout_height="高度自己设置"
android:layout_width="match_parent"
banner:indicator_margin="3dp"
banner:indicator_width="6dp"
banner:indicator_height="6dp"
banner:indicator_drawable_unselected="@android:color/white"
banner:indicator_drawable_selected="@android:color/holo_orange_dark"              

 />
```
##MVP逻辑分析：
  <font color=##FF0000 size=4>**简单的的布局和设置就准备就绪了，那么接下来就开始写逻辑代码了，以下是我的代码结构图：**</font>

![](http://ww4.sinaimg.cn/large/a8b2d4dfjw1f8t0p1qwhlj208q09twfg.jpg)


###MVP的结构图示：

![](http://upload-images.jianshu.io/upload_images/685394-b73ad82d1aa8597c.png?imageMogr2/auto-orient/strip%7CimageView2/2)

###代码具体分析
**在一个包里面创建上面的九个类：**


 <font color=#707070 size=4>**按道理来说，应该只有M、V、P三个类的，为什么出现了九个类呢？下面我就一 一 解答，其实很简单，主要的是M、V、P这三个类，其它 的类之是起了辅助作用，要么是封装方法，要么是适配器。**</font>


-  **Adapter :**
适配器

- **Banner：**
实体类，可以用GsonFormat自生成，也可以手写。
-  **Contract :**
分别将M(Model)、V（View）、P（presenter）里面的方法抽象分离，放到Contract这个公共接口中来，这样设计的好处是利于修改高内聚，低耦合。
-  **HttpUtils:**
看以看我的代码注释，讲解的很清楚，其实简单点来说，就是将retrofit里面的网络加载和数据解析的方法封装了起来，这样增强代码的可移植性，也简单方便，不用你每次都用以下五步来使用retrofit，直接将2-5步封装了起来：

1. 定义一个接口（封装URL地址和数据请求）

2.  实例化Retrofit

3. 通过retrofit实例创建接口服务对象

4. 接口服务对象调用接口中方法，获得Call对象

5. Call对象执行请求（异步、同步）

-  **View（MainActivity）:**
Fragment也就是MVP中的V，同样的Activity也属于View。
用于调用presenter的方法来传递拼接的`Map<String，String> params`。最终presenter将View层得到的params传给model，解析JSON。
-  **Model（MainModel） :**
Model简单点讲：作用就是加载网络数据，得到，然后将得到的网络解析的bean实体数据，以接口回调的方式返还给Presenter层。
-  **Presenter（MainPresenter） :**
Presenter接口回调，得到实体bean的list集合，然后将list集合传给View层，因为View才能操作数据。然后加载适配器得到最终的页面显示。
- **RetrofitService :**
接口回调的方法 参见 [三分钟教你学会Retrofit解析JSON](http://blog.csdn.net/u010312949/article/details/52776368) 
- **UrlConfig:**
将JSON字符拆分，具体原理参考 [三分钟教你学会Retrofit解析JSON](http://blog.csdn.net/u010312949/article/details/52776368) 

     ***同样的学习MVP,可参考大神鸿洋的CSDN:*** [ *浅谈 MVP in Android*](http://blog.csdn.net/lmj623565791/article/details/46596109)

分析就到这，如果有啥具体不懂的可以留言，我会一 一帮你解决，闲话不多讲，直接上代码。



##代码示例：
我是利用插件GsonFormat来直接生成的实例对象，有兴趣的同学可以自行Google。所以在实体bean的Banner类中：
```
//构建bean类对象
public class Banner {
    private Object searchHotKey;
    private List<AdListBean> adList;
    public Object getSearchHotKey() {
        return searchHotKey;
    }
    public void setSearchHotKey(Object searchHotKey) {
        this.searchHotKey = searchHotKey;
    }
    public List<AdListBean> getAdList() {
        return adList;
    }
    public void setAdList(List<AdListBean> adList) {
        this.adList = adList;
    }
    public static class AdListBean {
        private int UrlClass;
        private String address;
        private String cName;
        private int cStatus;
        private String imgUrl;
        
        public int getUrlClass() {
            return UrlClass;
        }
        public void setUrlClass(int UrlClass) {
            this.UrlClass = UrlClass;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCName() {
            return cName;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public int getCStatus() {
            return cStatus;
        }

        public void setCStatus(int cStatus) {
            this.cStatus = cStatus;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
```

在Constract类中：

```
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
```

在View类中：

```
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

```
在Presenter类中：

```
/*MainPresenter为Presenter，能够与Model和View两个之间相互沟通，
在Presenter必须实例化View和Model*/
public class MainPresenter implements  Contract.Presenter {
    private List<Banner.AdListBean> listBeen;
    private  Contract.View mView=new MainActivity();
    private  Contract.Model mMoedl=new MainModel();

    public MainPresenter(Contract.View mView) {
        //利用构造函数，传入View
        this.mView = mView;
    }


    @Override

    //调用方法，传入params的拼接字符
    public void putBannerData(Map<String, String> params) {
        //调用方法，传入params，接口回调得到response实体类，完成Presenter和Model之间的交流。
        mMoedl.getBannerData(params, new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                //具体得到实体类中的list集合
                listBeen=response.body().getAdList();
                //调用View中的方法，传入list集合。完成Presenter和View之间的交流。
                mView.getDataSuccessful(listBeen);
            }

            @Override
            //加载数据失败
            public void onFailure(Call<Banner> call, Throwable t) {
                mView.getDataFail(t.getMessage());
            }
        });
    }
}

```

在Model类中：

```
//MainModel为Model，只能与Presenter之间相互沟通。
public class MainModel implements Contract.Model {
    @Override
    public Void getBannerData(Map<String, String> params, Callback<Banner> callback) {
        //调用封装好的Retrofit工具类中的网络解析方法，传入各个参数，利用接口回调得到解析后的实体bean
        HttpUtils.getSingletion().getBannerData(UrlConfig.Path.BASEURL,params,callback);
        return null;
    }
}

```
在封装的工具类HttpUtil中：

```
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
```

在RetrofitService接口中：

```

//定义接口回调方法，字符串拼接规则如下图
public interface RetrofitService {
    @GET(UrlConfig.Path.BannerURL)
    Call<Banner> getData(@QueryMap Map<String,String> params);

}
```
具体参考我的上篇Blog : [三分钟教你学会Retrofit解析JSON](http://blog.csdn.net/u010312949/article/details/52776368) 
在UrlConfig类中：

```

//JSON字符的拆分>http://blog.csdn.net/u010312949/article/details/52776368
public class UrlConfig {

    //http://www.syby8.com/apptools/indexad.aspx?v=34

    public static class Path{

        public  static  final  String BASEURL="http://www.syby8.com/";

        public  static  final  String BannerURL="apptools/indexad.aspx?";

    }

    public static class Key{

        public  static  final  String V="v";
    }
    public static class  DefaultValue{

        public  static  final  String V="34";
    }


}

```
##效果图：
<iframe height=500 width=500 src="http://ww4.sinaimg.cn/mw690/e75a115bgw1f3rrbzv1m8g209v0diqv7.gif">

  <font color=#CD00CD size=4>**不对~ ~**</font>


是这张：

<iframe height=500 width=500 src="http://ww4.sinaimg.cn/large/a8b2d4dfjw1f8t1l72th6g20b40kr1l0.gif">

 <font  size=5>**[源码下载]()**</font>
 欢迎大家Star啦~啦~啦
