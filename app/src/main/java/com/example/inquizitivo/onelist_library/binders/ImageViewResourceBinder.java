package com.example.inquizitivo.onelist_library.binders;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.inquizitivo.onelist_library.annotations.ImageResource;

import java.lang.annotation.Annotation;

class ImageViewResourceBinder extends Binder<Integer> {

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ImageResource.class;
    }

    @Override
    protected void bindDataToView(Integer data) {
        ((ImageView) view).setImageResource(data);
    }
}
