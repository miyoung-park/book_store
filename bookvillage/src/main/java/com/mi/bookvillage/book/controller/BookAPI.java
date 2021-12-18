package com.mi.bookvillage.book.controller;

import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookAPI {

    private BookService bookService;

    public BookAPI(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookList(){
        List<BookVO> bookList = bookService.getBookList();
        return ResponseEntity.ok(bookList);
    }





}
