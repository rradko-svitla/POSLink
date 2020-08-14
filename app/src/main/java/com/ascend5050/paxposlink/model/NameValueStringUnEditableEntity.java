package com.ascend5050.paxposlink.model;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ascend5050.paxposlink.R;

public class NameValueStringUnEditableEntity extends NameValueStringEntity {

    private String value;

    public NameValueStringUnEditableEntity(String name, String value) {
        super(name, value, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE, "");
        this.value = value;
    }

    public NameValueStringUnEditableEntity(String name, String value, ClickCallback clickCallback) {
        super(name, value, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE, "", clickCallback);
        this.value = value;
    }

    @Override
    public CommonItemView createView(ViewGroup parent) {
        return new NameValueItemStringView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_string_uneditable, parent, false));
    }

    public String getValue() {
        return value;
    }
}
