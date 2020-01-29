package com.example.inquizitivo.onelist_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.inquizitivo.onelist_library.utils.ArrayListUtils;
import com.example.inquizitivo.onelist_library.listeners.OnItemClickListener;

import com.example.inquizitivo.onelist_library.binders.BinderFinder;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class QuickListAdapter<T> extends RecyclerView.Adapter<QuickListViewHolder<T>> implements View.OnClickListener {

    ArrayList<Integer> layoutIds;
    ArrayList<Class<? extends T>> classTypes;
    private Context context;
    private ArrayList<T> dataList;
    private ArrayList<OnItemClickListener> onItemClickListeners = new ArrayList<>();
    private BinderFinder binderFinder;

    QuickListAdapter(ArrayList<Class<? extends T>> classTypes, ArrayList<T> dataList, ArrayList<Integer> layoutIds, BinderFinder binderFinder, Context context) {
        this.layoutIds = layoutIds;
        this.dataList = dataList;
        this.classTypes = classTypes;
        this.context = context;
        this.binderFinder = binderFinder;
    }

    @Override
    public QuickListViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutIds.get(viewType), parent, false);
        view.setOnClickListener(this);

        return new QuickListViewHolder<>(view, classTypes.get(viewType), context, binderFinder);
    }

    @Override
    public int getItemViewType(int position) {

        Class currentClass = dataList.get(position).getClass();

        for (int i = 0; i < classTypes.size(); i++) {
            if (classTypes.get(i) == currentClass) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onBindViewHolder(QuickListViewHolder<T> holder, int position) {
        holder.setTagToView(position);
        holder.bind(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View v) {
        for (OnItemClickListener onItemClickedListener : onItemClickListeners) {
            int index = (int) v.getTag();
            onItemClickedListener.onItemClickListener(index);
        }
    }

    void reload(ArrayList<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        ArrayListUtils.insertIfNotYetInList(onItemClickListeners, onItemClickListener);
    }

    void removeOnItemClickListener(OnItemClickListener onItemClickListener) {
        ArrayListUtils.removeFromList(onItemClickListeners, onItemClickListener);
    }
}