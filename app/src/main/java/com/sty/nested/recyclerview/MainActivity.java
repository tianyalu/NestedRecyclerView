package com.sty.nested.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sty.nested.recyclerview.adapter.NestedRcvAdapter;
import com.sty.nested.recyclerview.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvParent;
    private List<Data> dataList;
    private NestedRcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData(){
        dataList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Data data = new Data();
            data.setTitle("title" + (i + 1));
            List<Data.ChildBean> childBeanList = new ArrayList<>();
            for(int j = 0; j < i + 3; j++){
                Data.ChildBean childBean = new Data.ChildBean();
                childBean.setContent("child " + (j+1) + " content");
                childBean.setChildValue("child " + (j+1) + " value");
                childBeanList.add(childBean);
            }
            data.setChildBeanList(childBeanList);
            dataList.add(data);
        }
    }

    private void initView(){
        rcvParent = findViewById(R.id.rcv_parent);

        adapter = new NestedRcvAdapter(dataList, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rcvParent.setLayoutManager(linearLayoutManager);
        rcvParent.setAdapter(adapter);
    }
}
