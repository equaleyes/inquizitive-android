package com.example.inquizitivo.onelist_library.binders;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.inquizitivo.onelist_library.annotations.Image;

import java.lang.annotation.Annotation;

class ImageViewBinder extends Binder<String> {

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return Image.class;
    }

    @Override
    protected void bindDataToView(String data) {
        Glide.with(context)
                .load(data)
                .into((ImageView) view);
    }
}
