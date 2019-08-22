package com.example.zengyong.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zengyong.studydemo.common.SwitchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClassBean bean = (ClassBean) adapter.getData().get(position);
                startActivity(new Intent(MainActivity.this, bean.aClass));
            }
        });

        adapter.setNewData(initDatas());
    }

    private List<ClassBean> initDatas() {
        List<ClassBean> list = new ArrayList<>();
        list.add(new ClassBean("switch控件的使用", SwitchActivity.class));
        return list;
    }


    class MainAdapter extends BaseQuickAdapter<ClassBean, BaseViewHolder> {

        public MainAdapter() {
            super(R.layout.adapter_main_layout);
        }

        @Override
        protected void convert(BaseViewHolder helper, ClassBean item) {
            helper.setText(R.id.title, item.desc);
        }
    }

}
