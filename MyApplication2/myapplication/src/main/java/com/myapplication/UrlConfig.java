package com.myapplication;

/**
 * Created by zitaojiang on 2016/10/15.
 */

//JSON字符的拆分，具体参考我的上篇Blog----->http://blog.csdn.net/u010312949/article/details/52776368
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
