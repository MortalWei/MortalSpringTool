package org.mortal.mtool.controllers;

import lombok.RequiredArgsConstructor;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.core.annotations.ApiIntro;
import org.mortal.mtool.common.core.annotations.BaseIntro;
import org.mortal.mtool.common.entity.MortalEntity;
import org.mortal.mtool.common.entity.R;
import org.mortal.mtool.common.services.IBookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

}
