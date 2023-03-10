package org.mortal.mtool.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2023/3/1 10:34
 * @description 1
 */
@Slf4j
public class NumberUtils {
    public static void main(String[] args) {
        Integer a = null;
        int b = 10;

        log.info("a.equals(b):{}", a.equals(b));
        log.info("a==b:{}", a == b);
        log.info("a.intValue() == b:{}", a.intValue() == b);
    }
}
