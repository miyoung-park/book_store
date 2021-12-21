package com.mi.bookvillage.book.api;

import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookAPI {

    private BookService bookService;

    public BookAPI(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/book/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookList(){
        List<BookVO> bookList = bookService.getBookList();
        return ResponseEntity.ok(bookList);
    }

    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookDetail(@PathVariable int bookSeq){
        BookVO bookVo = bookService.getBookDetail(bookSeq);

        return ResponseEntity.ok(bookVo);
    }

    @RequestMapping(value = "/admin/book/delete/{bookSeq}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable int bookSeq){
        bookService.deleteBook(bookSeq);
        return ResponseEntity.ok().build();
    }




}
