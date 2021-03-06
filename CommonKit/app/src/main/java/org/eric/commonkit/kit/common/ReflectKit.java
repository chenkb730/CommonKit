package org.eric.commonkit.kit.common;

import java.lang.reflect.Field;

public class ReflectKit {
    /**
     * @param obj       Field object
     * @param fieldName Field of name
     * @return Field of value
     */
    public static Object getValue(Object obj, String fieldName) {
        Class clazz = obj.getClass();

        Object result = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(obj);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 获取子类 _
     *
     * @param clazz
     * @return
     */
    public static Class getSubClass(Class clazz) {
        try {
            return Class.forName(clazz.getName() + "_");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param obj       Field object
     * @param fieldName Field of name
     * @return Field of value and parent class
     */
    public static Object getValueAndParent(Object obj, String fieldName) {
        Class clazz = obj.getClass();

        Object result = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(obj);

        } catch (NoSuchFieldException e) {
            try {
                if (result == null) {
                    Class pclazz = clazz.getSuperclass();
                    Field field;
                    field = pclazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    result = field.get(obj);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;

    }

}
