package wu.com.rxjavalibrary;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @author:kwg
 * @date:2022/7/6,17:09
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                  Stetho.newInitializerBuilder(this)
                 .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                 .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                 .build());

    }
}
