package org.mortal.mtool.controllers;

import lombok.RequiredArgsConstructor;
import org.mortal.mtool.common.entity.R;
import org.mortal.mtool.common.services.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/assert/{bookId}")
    public R<String> getAssertBook(@PathVariable("bookId") String bookid) {
        return new R<>(service.queryLive(bookid));
    }

}
