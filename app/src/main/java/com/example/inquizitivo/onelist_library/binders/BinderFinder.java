package com.example.inquizitivo.onelist_library.binders;


import java.lang.annotation.Annotation;
import com.example.inquizitivo.onelist_library.utils.ArrayListUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class BinderFinder {

    ArrayList<Binder> binders = new ArrayList<>();

    public BinderFinder() {
        binders.add(new TextViewBinder());
        binders.add(new TextViewTextColorBinder());
        binders.add(new BoldOnEvenBinder());
        binders.add(new ImageViewBinder());
        binders.add(new ImageViewResourceBinder());
        binders.add(new CheckBoxBinder());
        binders.add(new TextViewTextColorBinder());
    }

    public ArrayList<Binder> findBindersForField(Field field) {
        ArrayList<Binder> bindersForField = new ArrayList<>();

        for (Binder binder : binders) {

            Class<? extends Annotation> annotationTypeForBinder = binder.getAnnotationType();

            if (annotationTypeForBinder != null) {
                if (field.isAnnotationPresent(annotationTypeForBinder)) {
                    try {
                        bindersForField.add(binder.getClass().newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return bindersForField;
    }

    public void addBinder(Binder binder) {
        ArrayListUtils.insertIfNotYetInList(binders, binder);
    }
}
