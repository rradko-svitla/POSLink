package com.ascend5050.paxposlink.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ascend5050.paxposlink.R;

public class SingleButtonEntity implements RenderEntity {

    private String name;
    private ClickCallback clickCallback;

    public SingleButtonEntity(String name, ClickCallback clickCallback) {
        this.name = name;
        this.clickCallback = clickCallback;
    }

    public String getName() {
        return name;
    }

    public ClickCallback getClickCallback() {
        return clickCallback;
    }

    @Override
    public CommonItemView createView(ViewGroup parent) {
        return new SingleButtonView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_button, parent, false));
    }

    public interface ClickCallback {
        void onClick(View v, SingleButtonEntity renderEntity);
    }
}
