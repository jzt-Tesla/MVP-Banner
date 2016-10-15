package com.myapplication;

import java.util.List;

/**
 * Created by zitaojiang on 2016/10/15.
 */

//构建bean类对象
public class Banner {

    /**
     * adList : [{"UrlClass":2,"address":"intent:#Intent;launchFlags=0x4000000;component=com.syby8/.PromotionActivity;i.actid=16;end","cName":"变美只要1分钟","cStatus":1,"imgUrl":"http://img.syby8.com/upload/ad/201610121511256238.jpg"},{"UrlClass":2,"address":"intent:#Intent;launchFlags=0x4000000;component=com.syby8/.PromotionActivity;i.actid=10;end","cName":"鞋靴GO 新品来袭","cStatus":1,"imgUrl":"http://img.syby8.com/upload/ad/201610081025532441.jpg"},{"UrlClass":2,"address":"intent:#Intent;launchFlags=0x4000000;component=com.syby8/.PromotionActivity;i.actid=11;end","cName":"秋装疯抢趁现在","cStatus":1,"imgUrl":"http://img.syby8.com/upload/ad/201609210912004589.png"},{"UrlClass":2,"address":"intent:#Intent;launchFlags=0x4000000;component=com.syby8/.PromotionActivity;i.actid=54;end","cName":"小长假小目标","cStatus":1,"imgUrl":"http://img.syby8.com/upload/ad/201609261000378919.jpg"}]
     * searchHotKey : null
     */

    private Object searchHotKey;
    /**
     * UrlClass : 2
     * address : intent:#Intent;launchFlags=0x4000000;component=com.syby8/.PromotionActivity;i.actid=16;end
     * cName : 变美只要1分钟
     * cStatus : 1
     * imgUrl : http://img.syby8.com/upload/ad/201610121511256238.jpg
     */

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
