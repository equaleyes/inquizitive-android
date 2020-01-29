package com.example.inquizitivo.onelist_library.utils;

import java.util.ArrayList;

public class ArrayListUtils {

    public static <T> void insertIfNotYetInList(ArrayList<T> arrayList, T objectToInsert) {
        boolean shouldInsert = true;

        for (T temp : arrayList) {
            if (temp == objectToInsert) {
                shouldInsert = false;
                break;
            }
        }

        if (shouldInsert) {
            arrayList.add(objectToInsert);
        }
    }

    public static <T> void removeFromList(ArrayList<T> arrayList, T objectToRemove) {
        int removeIndex = -1;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == objectToRemove) {
                removeIndex = i;
                break;
            }
        }

        if (removeIndex > -1) {
            arrayList.remove(removeIndex);
        }
    }
}