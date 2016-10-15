package com.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zitaojiang on 2016/10/15.
 */

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
