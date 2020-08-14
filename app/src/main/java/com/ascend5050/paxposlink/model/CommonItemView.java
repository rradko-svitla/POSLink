package com.ascend5050.paxposlink.model;

import android.view.View;

public interface CommonItemView<T extends RenderEntity> {

    void render(T renderEntity);

    View getView();
}