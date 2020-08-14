package com.ascend5050.paxposlink.model;

import android.view.View;
import android.widget.Button;

import com.ascend5050.paxposlink.R;

public class SingleButtonView implements CommonItemView<SingleButtonEntity>{

    private final Button button;
    private View view;

    public SingleButtonView(View view) {
        this.view = view;
        button = (Button) view.findViewById(R.id.button_single);
    }

    @Override
    public void render(final SingleButtonEntity renderEntity) {
        button.setText(renderEntity.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderEntity.getClickCallback().onClick(v, renderEntity);
            }
        });

    }

    @Override
    public View getView() {
        return view;
    }
}
