package org.mortal.mtool.common.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.core.annotations.BusinessService;
import org.mortal.mtool.common.entity.MortalEntity;
import org.mortal.mtool.common.externals.IShenService;
import org.springframework.stereotype.Service;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 17:04
 * @description 测试
 */
@Service
@BusinessService
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    final IShenService shenService;

    @Override
    public String queryBook(String bookId) {
        JSONObject object = new JSONObject();
        object.put("bookId", bookId);
        object.put("bookName", "defaultName");
        object.put("intro", shenService.regular());
        return JSON.toJSONString(object);
    }

    @Override
    public String queryLive(String bookId) {
        JSONObject object = new JSONObject();
        object.put("bookId", bookId);
        object.put("bookName", "defaultName");
        object.put("intro", shenService.anomaly());

        return JSON.toJSONString(object);
    }

    @Override
    public String queryMortal(MortalEntity id) {
        JSONObject object = new JSONObject();
        object.put("bookId", id);
        object.put("bookName", "defaultName");


        MortalEntity entity = new MortalEntity();
        entity.setAge(id.getAge());
        entity.setGender(id.getGender());
        entity.setAge(id.getAge());


        BREnum.PARAM_VALIDATE_ERROR.assertProperty(entity);

        return JSON.toJSONString(object);
    }
}
