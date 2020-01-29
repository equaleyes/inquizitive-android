package com.example.inquizitivo.onelist_library.binders;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.lang.annotation.Annotation;

class CheckBoxBinder extends Binder<Boolean> implements CompoundButton.OnCheckedChangeListener {

    @Override
    public void setup() {
        ((CheckBox)view).setOnCheckedChangeListener(this);
    }

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return com.example.inquizitivo.onelist_library.annotations.CheckBox.class;
    }

    @Override
    protected void bindDataToView(Boolean data) {
        ((CheckBox)view).setChecked(data);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {
            field.set(object, isChecked);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
