package com.example.inquizitivo.onelist_library.utils;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class UserSetResourceName {

    //Any one line list annotations that need a method for user set resource name, must use this required method name
    private static final String RESOURCE_METHOD_NAME = "resourceName";
    private static final String RESOURCE_NAME_NOT_SET = "";

    public static String getUserSetResourceName(Field field, Class<? extends Annotation> annotationType) {
        Annotation annotation = field.getAnnotation(annotationType);
        Method method = getResourceNameMethod(annotationType);

        if (method == null) {
            return null;
        }

        String resourceName = RESOURCE_NAME_NOT_SET;

        try {
            resourceName = (String) method.invoke(annotation);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (resourceName.equals(RESOURCE_NAME_NOT_SET)) {
            return null;
        }

        return resourceName;
    }

    private static Method getResourceNameMethod(Class<? extends Annotation> annotationType) {
        Method[] methods = annotationType.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().equals(RESOURCE_METHOD_NAME)) {
                return method;
            }
        }

        Log.w("Annotation warning", "Resource method not found in Annotation -> " + annotationType.getName());
        return null;
    }
}
