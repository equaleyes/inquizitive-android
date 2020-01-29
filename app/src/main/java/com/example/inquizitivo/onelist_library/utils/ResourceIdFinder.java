package com.example.inquizitivo.onelist_library.utils;

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ResourceIdFinder {

    public static int getResourceId(Field field, Class<? extends Annotation> annotationType, Context context) {
        String resourceName = UserSetResourceName.getUserSetResourceName(field, annotationType);
        String psotfixName = UserSetResourceName.getUserSetResourceName(field, annotationType);
        String prefixName = UserSetResourceName.getUserSetResourceName(field, annotationType);

        if (resourceName == null) {
            resourceName = field.getName();
        }

        return getResourceIdFromString(resourceName, context);
    }

    private static int getResourceIdFromString(String resourceName, Context context) {
        return context.getResources().getIdentifier(resourceName, "id", context.getPackageName());
    }
}
