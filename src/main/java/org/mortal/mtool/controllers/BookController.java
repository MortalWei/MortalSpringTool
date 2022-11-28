package org.mortal.mtool.controllers;

import lombok.RequiredArgsConstructor;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.entity.R;
import org.mortal.mtool.common.services.IBookService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{bookId}")
    public R<String> getBooks(@PathVariable("bookId") String bookid) {
        return new R<>(service.queryBook(bookid));
    }

    @PostMapping("/assert")
    public R<String> getAssertBook(@RequestBody Map book) {

        BREnum.UNAUTHORIZED.assertNotNull(book);
//
//        if (!book.containsKey("bookId")) {
//        }

        return new R<>(service.queryLive(book.get("bookId").toString()));
    }

}
