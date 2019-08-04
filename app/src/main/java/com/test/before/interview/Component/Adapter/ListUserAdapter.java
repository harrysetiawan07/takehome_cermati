package com.test.before.interview.Component.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.before.interview.Component.Base.BaseActivity;
import com.test.before.interview.Component.Utils;
import com.test.before.interview.Database.Model.User;
import com.test.before.interview.R;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.Holder> {
    List<User> itemList;
    BaseActivity ctx;
    private LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    private int previous = 0;

    private itemClickCallback itemClickCallback;

    public ListUserAdapter(List<User> data, BaseActivity ctx){
        this.itemList = data;
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
        mSelectedItemsIds = new SparseBooleanArray();
    }

    public List<User> getItemList() {
        return itemList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);

        Holder viewHolder = new Holder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Context context = holder.icon.getContext();
        Picasso.with(context).load(itemList.get(position).getAvatarURL()).into(holder.icon);
        holder.username.setText(itemList.get(position).getUsername());
        holder.username.setTypeface(Utils.typefaceBold());
        if(itemList.get(position).getUsername().equals("harrysetiawan07")){
            holder.me.setVisibility(View.VISIBLE);
        }else{
            holder.me.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void toggleSelection(int position) {
        selectView(position, false);
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void selectView(int position, boolean value) {
        if (value) {
            mSelectedItemsIds.put(position, value);
        }else {
            itemList.remove(position);
        }
        notifyDataSetChanged();
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public LinearLayout itemLayout;
        public ImageView icon;
        public TextView username;
        public TextView me;

        public Holder(View itemView) {
            super(itemView);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.item);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            username = (TextView) itemView.findViewById(R.id.tvUsername);
            me = (TextView) itemView.findViewById(R.id.tvME);
            itemLayout.setOnClickListener(this);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            switch (itemView.getId()){
                case R.id.item:
                    itemClickCallback.onItemClick(getAdapterPosition());
                    break;
                case R.id.icon:
                    itemClickCallback.onItemClick(getAdapterPosition());
                    break;
            }
        }
    }

    public interface itemClickCallback{
        void onItemClick(int p);
    }

    public void setItemClickCallback(final itemClickCallback itemClickCallback){
        this.itemClickCallback = itemClickCallback;
    }
}