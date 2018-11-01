package com.sty.nested.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sty.nested.recyclerview.R;
import com.sty.nested.recyclerview.model.Data;

import java.util.List;

/**
 * Created by tian on 2018/11/1.
 */

public class NestedRcvAdapter extends RecyclerView.Adapter<NestedRcvAdapter.ViewHolder>{
    private List<Data> dataList;
    public Context mContext;

    public NestedRcvAdapter(List<Data> dataList, Context mContext){
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_parent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.tvTitle.setText(data.getTitle());

        ChildAdapter childAdapter = (ChildAdapter) holder.rcvChild.getAdapter();
        //适配器复用可能导致子数据错乱
//        if(childAdapter == null){
            RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
            manager.setAutoMeasureEnabled(true);
            holder.rcvChild.setLayoutManager(manager);
            holder.rcvChild.setAdapter(new ChildAdapter(data.getChildBeanList(), position));
//        }else{
//            childAdapter.setData(data.getChildBeanList()); //重新设置数据
//            childAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public RecyclerView rcvChild;

        public ViewHolder(View itemView){
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            rcvChild = (RecyclerView) itemView.findViewById(R.id.rcv_child);
        }
    }

    public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder>{
        public List<Data.ChildBean> childList;
        public int parentIndex;

        public ChildAdapter(List<Data.ChildBean> childList, int parentIndex){
            this.childList = childList;
            this.parentIndex = parentIndex;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_child, parent, false);
            return new ChildViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder, final int position) {
            Data.ChildBean childBean = childList.get(position);
            holder.tvContent.setText(childBean.getContent());
            holder.tvIndex.setText((position + 1) + ".");
            holder.tvChildValue.setText(childBean.getChildValue());
            if(position == 0){
                holder.tvTopLine.setVisibility(View.INVISIBLE);
            }
            if(position == childList.size() - 1){
                holder.tvBottomLine.setVisibility(View.INVISIBLE);
            }
            holder.mContentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "parent " + parentIndex + " child item " + position + " is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return childList == null ? 0 : childList.size();
        }

        public void setData(List<Data.ChildBean> childList) {
            this.childList = childList;
        }

        public class ChildViewHolder extends RecyclerView.ViewHolder{
            public TextView tvIndex;
            public TextView tvContent;
            public TextView tvChildValue;
            public TextView tvTopLine;
            public TextView tvPoint;
            public TextView tvBottomLine;
            public View mContentView;

            public ChildViewHolder(View itemView) {
                super(itemView);
                tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
                tvContent = (TextView) itemView.findViewById(R.id.tv_content);
                tvChildValue = (TextView) itemView.findViewById(R.id.tv_child_value);
                tvTopLine = (TextView) itemView.findViewById(R.id.tv_top_line);
                tvPoint = (TextView) itemView.findViewById(R.id.tv_point);
                tvBottomLine = (TextView) itemView.findViewById(R.id.tv_bottom_line);
                mContentView = itemView;
            }
        }
    }
}
