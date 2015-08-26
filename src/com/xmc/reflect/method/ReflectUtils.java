package com.xmc.reflect.method;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/8/26.
 */
public class ReflectUtils {

    private ReflectUtils(){

    }

    /**
     * 根据字段名通过反射获得一个对象的私有变量
     * @param o
     * @param fieldName
     * @return
     */
    public static Object getValue(Object o, String fieldName) throws Exception {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.getName().equals(fieldName)){
                field.setAccessible(true);
                return field.get(o);
            }
        }

        throw new Exception("没有找到指定的字段！");

    }

    /**
     * 根据方法签名获得一个类的某个方法
     * @param clazz
     * @param methodName
     * @param classTypes
     * @return
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] classTypes) {

        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classTypes);
        } catch (NoSuchMethodException e) {
            getMethod(clazz.getSuperclass(), methodName, classTypes);      //如果该类中不存在该方法 那么可能存在于它的父类中
        }
        return method;
    }

    /**
     * 根据方法签名获得一个对象的某个方法
     * @param o
     * @param methodName
     * @param classTypes
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Object o, String methodName, Class<?>[] classTypes) throws NoSuchMethodException {
        Class<?> clazz = o.getClass();
        return getMethod(clazz, methodName, classTypes);
    }

    /**
     * 通过方法名和参数列表调用一个对象的某个方法（包括私有方法
     * @param o
     * @param methodName
     * @param args
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invokeMethod(Object o, String  methodName, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] classTypes = null;
        /**获得参数类型列表*/
        if(args != null){
            classTypes = new Class<?>[args.length];
            for(int i = 0; i < args.length; i++){
                classTypes[i] = args[i].getClass();
            }
        }
        /**调用方法*/
        Method method = getMethod(o, methodName, classTypes);
        method.setAccessible(true);
        return  method.invoke(o, args);
    }

    /**
     *  通过方法名和参数列表调用一个对象的某个方法（当参数是一个的时候
     * @param o
     * @param methodName
     * @param arg
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static  Object invokeMethod(Object o, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args = new Object[1];
        args[0] = arg;
        return  invokeMethod(o, methodName, args);
    }

    /**
     * 调用没有参数的方法
     * @param o
     * @param methodName
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static  Object invokeMethod(Object o, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] args = new Object[0];
        return  invokeMethod(o, methodName, args);
    }
}
