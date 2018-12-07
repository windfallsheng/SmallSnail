package com.smallsnailtech.smallsnail.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.smallsnailtech.smallsnail.component.service.SaveInstallAppsService;

/**
 * @author luzhaosheng
 * @date 2018年11月07日
 * 监听应用安装卸载，并且跳转到服务里，将包名存储在本地，上传服务器
 */
public class AppAddAndRemoveReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {// 应用安装
                String dataString = intent.getDataString();
                // dataString格式：dataString="package:com.xtuone.android.syllabus"
                Log.d("AppAddAndRemoveReceiver", "应用已安装完成_dataString=" + dataString);
                String packageName = dataString.substring(dataString.lastIndexOf(":") + 1, dataString.length());
                Log.d("AppAddAndRemoveReceiver", "应用已安装完成_packageName=" + packageName);
                context.startService(
                        SaveInstallAppsService.newIntent(context, packageName, SaveInstallAppsService.ACTION_INSTALL_APP));
            } else if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {// 应用卸载
                String dataString = intent.getDataString();
                // dataString格式：dataString="package:com.xtuone.android.syllabus"
                Log.d("AppAddAndRemoveReceiver", "应用已卸载完成_dataString=" + dataString);
                String packageName = dataString.substring(dataString.lastIndexOf(":") + 1, dataString.length());
                Log.d("AppAddAndRemoveReceiver", "应用已卸载完成_packageName=" + packageName);
                context.startService(
                        SaveInstallAppsService.newIntent(context, packageName, SaveInstallAppsService.ACTION_UNINSTALL_APP));
            }
        }
    }

}
