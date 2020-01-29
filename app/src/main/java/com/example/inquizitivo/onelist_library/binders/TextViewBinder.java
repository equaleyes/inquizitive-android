package com.example.inquizitivo.onelist_library.binders;

import android.widget.TextView;
import com.example.inquizitivo.onelist_library.annotations.Text;
import java.lang.annotation.Annotation;

class TextViewBinder extends Binder<String> {

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return Text.class;
    }

    @Override
    public void bindDataToView(String data) {
        ((TextView)view).setText(data);
    }
}