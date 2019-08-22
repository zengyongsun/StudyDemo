package com.example.zengyong.studydemo.fourcomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2019/4/17 10:38
 * desc   :
 * version: 1.0
 */
public class ActivityComponents extends AppCompatActivity {

    public static final String TAG = ActivityComponents.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: 在 Activity 已停止并即将再次启动前调用。始终后接 onStart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    /**
     * 由于 onPause() 是这三个方法中的第一个，因此 Activity 创建后，
     * onPause() 必定成为最后调用的方法，然后才能终止进程 — 如果系统在紧急情况下必须恢复内存，
     * 则可能不会调用 onStop() 和 onDestroy()。
     * 因此，您应该使用 onPause() 向存储设备写入至关重要的持久性数据（例如用户编辑）。
     * 不过，您应该对 onPause() 调用期间必须保留的信息有所选择，
     * 因为该方法中的任何阻止过程都会妨碍向下一个 Activity 的转变并拖慢用户体验。
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 通常用于确认对持久性数据的未保存更改、" +
                "停止动画以及其他可能消耗 CPU 的内容，诸如此类。");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /*
     * 具体地讲，默认实现会为布局中的每个 View 调用相应的 onSaveInstanceState() 方法，
     * 让每个视图都能提供有关自身的应保存信息。Android 框架中几乎每个小部件都会根据需要实现此方法，
     * 以便在重建 Activity 时自动保存和恢复对 UI 所做的任何可见更改。
     * 例如，EditText 小部件保存用户输入的任何文本，CheckBox 小部件保存复选框的选中或未选中状态。
     * 您只需为想要保存其状态的每个小部件提供一个唯一的 ID（通过 android:id 属性）。
     * 如果小部件没有 ID，则系统无法保存其状态。
     */

    /**
     * 在这种情况下，您可以实现另一个回调方法对有关 Activity 状态的信息进行保存，
     * 以确保有关 Activity 状态的重要信息得到保留：onSaveInstanceState()。
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: 恢复界面 切换横竖屏");
    }
}
