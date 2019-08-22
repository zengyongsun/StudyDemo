package com.example.zengyong.studydemo.fourcomponents;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2019/4/17 16:03
 * desc   :
 * version: 1.0
 */
public class ServiceTest extends Service {

    /**
     * start方式开启
     * <p>
     * onCreate() -- > onStartCommand() -- > onDestory()
     * 注意：如果服务已经开启，不会重复回调onCreate()方法，
     * 如果再次调用context.startService()方法，service而是会调用onStart()或者onStartCommand()方法。
     * 停止服务需要调用context.stopService()方法，服务停止的时候回调onDestory被销毁。
     *
     * 特点：
     * 一旦服务开启就跟调用者（开启者）没有任何关系了。开启者退出了，开启者挂了，
     * 服务还在后台长期的运行，开启者不能调用服务里面的方法。
     */

    /**
     * bind方式开启
     * <p>
     * onCreate() -- > onBind() --> onUnbind() -- > onDestory()
     * <p>
     * 注意：绑定服务不会调用onStart()或者onStartCommand()方法
     * <p>
     * 特点：
     * bind的方式开启服务，绑定服务，调用者挂了，服务也会跟着挂掉。绑定者可以调用服务里面的方法
     */

    public static final String TAG = ServiceTest.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }
}
