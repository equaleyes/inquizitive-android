package com.example.inquizitivo.onelist_library.binders;

import android.graphics.Typeface;
import android.widget.TextView;

import com.example.inquizitivo.onelist_library.annotations.TextBold;

import java.lang.annotation.Annotation;

class TextBoldBinder extends Binder<Boolean> {

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return TextBold.class;
    }

    @Override
    protected void bindDataToView(Boolean data) {

        boolean bold = data;

        if (bold) {
        ((TextView)view).setTypeface(null, Typeface.BOLD);}
        else {
            ((TextView)view).setTypeface(null, Typeface.NORMAL);
        }


    }
}
