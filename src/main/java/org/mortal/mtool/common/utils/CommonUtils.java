package org.mortal.mtool.common.utils;

//import com.mg.vms.core.consts.RegexConstants;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2021/11/11 19:29
 * @description 常用工具
 */
@Slf4j
public final class CommonUtils {


    /**
     * 异常信息转换成字符串输出
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return StringUtils.EMPTY;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }
    }

    /**
     * 获取 MDC context 内容
     * 默认值：StringUtils.EMPTY
     *
     * @param key
     * @return
     */
    public static String getMDC(String key) {
        String result = MDC.get(key);
        return StringUtils.isNotBlank(result) ? result : StringUtils.EMPTY;
    }

    /**
     * 获取 MDC context 内容
     *
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static String getMDC(String key, String defaultValue) {
        String result = MDC.get(key);
        return StringUtils.isNotBlank(result) ? result : defaultValue;
    }

    /**
     * 设置 MDC context 内容
     *
     * @param key
     * @param value
     * @return
     */
    public static void setMDC(String key, String value) {
        MDC.put(key, value);
    }

    /**
     * 清空 MDC context 内容
     */
    public static void clearMDC() {
        MDC.clear();
    }

    /**
     * 获取本机IP
     *
     * @return
     */
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("getLocalIp Error", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取 StringBuilder 新实例
     *
     * @return
     */
    public static StringBuilder getStringBuffer() {
        return new StringBuilder();
    }


    /**
     * 集合处理
     *
     * @param arr         集合
     * @param action      非空集合操作
     * @param emptyAction 空集合操作
     * @param <T>         集合数据类型
     * @param <R>         返回类型
     * @return {@link R}
     */
    public static <T, R> R isArrayEmpty(List<T> arr, Function<List<T>, R> action, Function<List<T>, R> emptyAction) {
        if (arr == null || arr.isEmpty()) {
            return emptyAction.apply(arr);
        }
        return action.apply(arr);
    }

    /**
     * 集合处理
     *
     * @param arr    集合
     * @param action 非空集合操作
     * @param <T>    集合数据类型
     */
    public static <T> void isArrayEmpty(List<T> arr, Consumer<List<T>> action) {
        if (arr != null && !arr.isEmpty()) {
            action.accept(arr);
        }
    }

    /**
     * 集合处理
     *
     * @param arr
     * @param action
     * @param <T>
     */
    public static <T> void isArrayEmpty(List<T> arr, Runnable action) {
        if (arr != null && !arr.isEmpty()) {
            action.run();
        }
    }

    /**
     * 实体(对象)处理
     *
     * @param obj         实体数据
     * @param action      非空实体操作
     * @param emptyAction 空实体操作
     * @param <T>         数据类型
     * @param <R>         返回类型
     * @return {@link R}
     */
    public static <T, R> R isModelEmpty(T obj, Function<T, R> action, Function<T, R> emptyAction) {
        if (obj == null) {
            return emptyAction.apply(null);
        }
        return action.apply(obj);
    }

    /**
     * 实体(对象)处理
     *
     * @param obj    实体/对象数据
     * @param action 非空实体/对象操作
     * @param <T>    数据类型
     */
    public static <T> void isModelEmpty(T obj, Consumer<T> action) {
        if (obj != null) {
            action.accept(obj);
        }
    }

    /**
     * 实体(对象)处理
     *
     * @param obj
     * @param action
     * @param <T>
     */
    public static <T> void isModelEmpty(T obj, Runnable action) {
        if (obj != null) {
            action.run();
        }
    }

    /**
     * 获取当前系统时间
     *
     * @return {@link Date} 当前系统时间
     */
    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Long getNowMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间
     *
     * @param timeMillis
     * @return
     */
    public static Date getDate(Long timeMillis) {
        return new Date(timeMillis);
    }

    /**
     * 获取ISO标准时间字符串
     *
     * @param date
     * @return
     */
    public static String getISODateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CTT"));//CTT = Asia/Shanghai
        return dateFormat.format(date);
    }

    public static Date getISODate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CTT"));//CTT = Asia/Shanghai
        return dateFormat.parse(dateStr);
    }

    /**
     * 获取指定 Annotation
     *
     * @param args
     * @param clazz
     * @param <T>
     * @return {@link T}
     */
    public static <T extends Annotation> T isAnnotation(Method args, Class<T> clazz) {
        if (args.isAnnotationPresent(clazz)) {
            return args.getAnnotation(clazz);
        }
        if (args.getDeclaringClass().isAnnotationPresent(clazz)) {
            return args.getDeclaringClass().getAnnotation(clazz);
        }
        return null;
    }

    /**
     * 获取指定 Annotation
     *
     * @param args
     * @param clazz
     * @param <T>
     * @return {@link T}
     */
    public static <T extends Annotation> T isAnnotation(Class args, Class<T> clazz) {
        if (args.isAnnotationPresent(clazz)) {
            return (T) args.getAnnotation(clazz);
        }
        return null;
    }

    /**
     * 使用EMPTY填充Map
     *
     * @param map
     * @return
     */
    public static Map fillNullMap(Map map) {
        if (map == null) return map;
        for (Object obj :
                map.keySet()) {
            Object o = map.get(obj);
            if (o == null) {
                map.put(obj, StringUtils.EMPTY);
            }
        }
        return map;
    }

    public static String nullToString(String args) {
        if (StringUtils.isBlank(args)) {
            return StringUtils.EMPTY;
        }
        return args;
    }

    /**
     * 是否可以激活访客权限
     * 调用海康ISC平台访客-免预约登记接口
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isCanActive(Date start, Date end) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();

        cal1.setTime(new Date());
        cal2.setTime(start);
        cal3.setTime(end);

        return (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) ||
                (cal2.before(cal1) && cal3.after(cal1)));
    }

    /**
     * 根据正则表达式替换文本内容
     *
     * @param content     源内容
     * @param regex       正则表达式
     * @param replacement 替换的内容
     * @return
     */
    public static String replaceRegex(String content, String regex, String replacement) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(regex)) {
            return StringUtils.EMPTY;
        }

        return Pattern.compile(regex).matcher(content).replaceAll(replacement);
    }

//    /**
//     * 处理图片转换的base64字符串
//     * 去除image头
//     *
//     * @param content
//     * @return
//     */
//    public static String replaceImageRegex(String content) {
//        if (StringUtils.isEmpty(content)) {
//            return StringUtils.EMPTY;
//        }
//        return Pattern.compile(RegexConstants.IMAGE_BASE64_HEAD).matcher(content).replaceAll(StringUtils.EMPTY);
//    }
}
