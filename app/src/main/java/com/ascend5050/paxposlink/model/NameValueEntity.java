package com.ascend5050.paxposlink.model;

public abstract class NameValueEntity<T> implements RenderEntity {
    private String name = "";
    private T value;

    public NameValueEntity(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

}
