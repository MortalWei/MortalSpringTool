package org.mortal.mtool.common.services;

import org.mortal.mtool.common.entity.MortalEntity;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 17:01
 * @description 测试
 */
public interface IBookService {
    /**
     * 查询可本信息
     *
     * @param bookId
     * @return
     */
    String queryBook(String bookId);

    /**
     * 测试异常枚举
     *
     * @param bookId
     * @return
     */
    String queryLive(String bookId);

    /**
     * 测试参数校验
     *
     * @param id
     * @return
     */
    String queryMortal(MortalEntity id);
}
