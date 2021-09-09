package com.codecool.hogwartshouses.converters;

public class EnumToString {

    public static <T extends Enum<T>> String convert(T myEnum) {
        char[] name = myEnum.name().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        boolean big = true;
        for (char c : name) {
            if (big) {
                stringBuilder.append(c);
                big = false;
            } else {
                if (c == '_') {
                    stringBuilder.append(" ");
                    big = true;
                } else {
                    stringBuilder.append(String.valueOf(c).toLowerCase());
                }
            }
        }
        return stringBuilder.toString();
    }

}
