package org.mortal.mtool.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.core.annotations.ApiIntro;
import org.mortal.mtool.common.entity.MortalEntity;
import org.mortal.mtool.common.entity.R;
import org.mortal.mtool.common.services.IBookService;
import org.mortal.mtool.common.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:03
 * @description 测试
 */
@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    final IBookService service;

    final RedisUtils redisUtils;

    @GetMapping("/b/{bookId}")
    @ApiIntro(value = "[测试方法一]", tags = "测试")
    public R<String> getBooks(@PathVariable("bookId") String bookid) {
        return new R<>(service.queryBook(bookid));
    }

    @PostMapping("/assert")
    @ApiIntro(value = "[测试方法二]", tags = "测试")
    public R<String> getAssertBook(@RequestBody Map book) {
        BREnum.CONF_UNAUTHORIZED_ERROR.assertNotNull(book);
        return new R<>(service.queryLive(book.get("bookId").toString()));
    }

    @PostMapping("/mortal")
    @ApiIntro(value = "[测试方法三]", tags = "测试")
    public R<String> postMortal(@RequestBody @Valid MortalEntity data) {
        return new R<>(service.queryMortal(data));
    }

    @GetMapping("/redis/take")
    @ApiIntro(value = "[测试获取缓存]", tags = "测试")
    public R<Object> getRedisValue(@PathParam("redisKey") String redisKey) {
        Object redisValue = redisUtils.get(redisKey);
        return new R<>(redisValue);
    }

    @PostMapping("/redis/put")
    @ApiIntro(value = "[测试设置缓存]", tags = "测试")
    public R<Boolean> setRedisValue(@PathParam("redisKey") String redisKey, @RequestBody String body) {
        JSONObject object = JSON.parseObject(body);

        boolean flag = redisUtils.set(redisKey, object, 3600);
        return new R<>(flag);
    }

    @GetMapping("/redis/test")
    public R<Object> getRedisTest(@PathParam("redisTest") String redisTest) {
        String[] arr = new String[]{"a", "b", "c", "d"};

        redisUtils.set("testArr", arr, 3600);

        Object arrResult = redisUtils.get("testArr");

        return new R<>(arrResult);
    }
}
