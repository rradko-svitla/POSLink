package com.ascend5050.paxposlink.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ascend5050.paxposlink.R;


public class NameValueStringEntity extends NameValueEntity<String> {
    /**
     * {@link android.text.InputType}
     */
    private int inputType;
    private String hint;
    private ClickCallback clickCallback;

    public NameValueStringEntity(String name, String value, int inputType, String hint) {
        this(name, value, inputType, hint, null);
    }

    public NameValueStringEntity(String name, String value, int inputType, String hint, ClickCallback clickCallback) {
        super(name, value);
        this.inputType = inputType;
        this.hint = hint;
        this.clickCallback = clickCallback;
    }

    public String getHint() {
        return hint;
    }

    public int getInputType() {
        return inputType;
    }

    public ClickCallback getClickCallback() {
        return clickCallback;
    }

    @Override
    public CommonItemView createView(ViewGroup parent) {
        return new NameValueItemStringView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_string_val, parent, false));
    }

    public interface ClickCallback {
        void onClick(View v, NameValueStringEntity entity);
    }
}
