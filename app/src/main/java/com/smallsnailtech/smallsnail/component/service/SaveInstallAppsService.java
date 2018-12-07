package com.smallsnailtech.smallsnail.component.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author luzhaosheng
 * @date 2018年11月07日
 * 将包名存储在本地，上传服务器
 */
public class SaveInstallAppsService extends Service {

    private static final String PACKAGENAME = "packageName";
    public static final String ACTION_INSTALL_APP = "android.intent.action.service.INSTALL_APP";
    public static final String ACTION_UNINSTALL_APP = "android.intent.action.service.UNINSTALL_APP";

    public static Intent newIntent(Context context, String packageName, String action) {
        return new Intent().putExtra(PACKAGENAME, packageName).setAction(action)
                .setPackage(context.getApplicationContext().getPackageName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (ACTION_INSTALL_APP.equals(intent.getAction())) {
                String packageName = intent.getStringExtra(PACKAGENAME);
                Log.d("SaveInstallAppsService", "packageName==>" + packageName);
                if (packageName != null && !"".equals(packageName)) {
                    // todo 保存到数据库，或者发送给服务器
                }
            } else if (ACTION_UNINSTALL_APP.equals(intent.getAction())) {
                String packageName = intent.getStringExtra(PACKAGENAME);
                Log.d("SaveInstallAppsService", "ACTION_UNINSTALL_APP_packageName==>" + packageName);
                if (packageName != null && !"".equals(packageName)) {
                    // todo 发送给服务器
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
