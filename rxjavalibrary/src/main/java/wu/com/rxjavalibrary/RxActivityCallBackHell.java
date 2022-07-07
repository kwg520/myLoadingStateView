package wu.com.rxjavalibrary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import wu.com.rxjavalibrary.api.HttpUtil;
import wu.com.rxjavalibrary.api.ProjectBean;
import wu.com.rxjavalibrary.api.ProjectItem;
import wu.com.rxjavalibrary.api.WandroidApi;

// 核心思想：响应式编程  观察者设计模式 ==
//rxjava 配合retrofit
//防抖
//网络嵌套  flatMap 解决网络请求嵌套
//doNext运用 频繁的异步线程主线程切换  注册请求数据（io)  - 注册响应更新ui(mianthread) - 注册的返回数据去请求登录(io)--登录成功更新ui(mainthread)
/**
 * obserable.compose().doOnnext().obserOn(io).flatmap{}obserOn(mainthread).subscrber{}
 */
public class RxActivityCallBackHell extends AppCompatActivity {

    private  WandroidApi api;
    private Button button ;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_callbac_hell);
        api = HttpUtil.getRetrofit().create(WandroidApi.class);
        button = findViewById(R.id.button);

         RxView.clicks(button).throttleFirst(2, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return api.getProject();
                    }
                }).flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.DataBean>>() {
                    @Override
                    public ObservableSource<ProjectBean.DataBean> apply(ProjectBean projectBean) throws Exception {
                        return Observable.fromIterable(projectBean.getData());
                    }
                })
                .flatMap(new Function<ProjectBean.DataBean, ObservableSource<ProjectItem>>() {
                    @Override
                    public ObservableSource<ProjectItem> apply(ProjectBean.DataBean dataBean) throws Exception {
                        return api.getItem(1,dataBean.getId());
                    }
                }).subscribe(new Consumer<ProjectItem>() {
                     @Override
                     public void accept(ProjectItem projectItem) throws Exception {
                         Log.e("item",projectItem.toString());
                     }
                 });


//
//        getProjectData();
//        getItemData();


    }

    private void getItemData() {
        api.getItem(1,295)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        Log.e("projectBean==",projectItem.toString());
                    }
                });
    }

    private void getProjectData() {
        api.getProject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Exception {
                        Log.e("projectBean==",projectBean.toString());
                    }
                });
    }


}