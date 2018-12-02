package com.besofty.firstproject.controller;

import com.besofty.firstproject.book.BookService;
import com.besofty.firstproject.book.dto.BookDTO;
import com.besofty.firstproject.book.dto.BookInsertDTO;
import com.besofty.firstproject.book.dto.BookQueryDTO;
import com.besofty.firstproject.common.dto.JsonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(value = "书籍接口")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询书籍", nickname = "queryBooks",httpMethod = "GET", notes = "查询书籍")
    @GetMapping("/books")
    public JsonResp queryBooks(@ApiParam Pageable pageable,
                               @Valid BookQueryDTO bookQueryDTO){
        Page<BookDTO> booksPage = bookService.queryBooks(pageable, bookQueryDTO);
        return JsonResp.dataPage(booksPage);
    }

    @ApiOperation(value = "新增书籍", nickname = "addBook", httpMethod = "POST", notes = "新增书籍")
    @PostMapping("/book")
    public JsonResp addBook(@Valid @RequestBody BookInsertDTO bookInsertDTO){
        BookDTO bookDTO = bookService.addBook(bookInsertDTO);
        return JsonResp.ok(bookDTO);
    }
}
