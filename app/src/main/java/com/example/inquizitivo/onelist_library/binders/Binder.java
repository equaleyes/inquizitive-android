package com.example.inquizitivo.onelist_library.binders;

import android.content.Context;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class Binder<T> {

    protected Object object;
    protected Context context;
    protected Field field;
    protected Object view;
    protected int position;
    Class<T> fieldType;

    public void set(Object view, Field field, Class<T> fieldType, Context context) {
        this.view = view;
        this.field = field;
        this.context = context;
        this.fieldType = fieldType;
        setup();
    }

    public void setup() {}

    public void bind(Object object, int position) {
        this.position = position;
        this.object = object;

        try {
            bindDataToView(fieldType.cast(field.get(object)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            Log.e("Wrong field type", field.getName());
        }
    }

    public abstract Class<? extends Annotation> getAnnotationType();
    protected abstract void bindDataToView(T data);
}