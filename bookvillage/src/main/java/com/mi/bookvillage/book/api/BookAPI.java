package com.mi.bookvillage.book.api;

import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilder;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BookAPI
 */
@RestController
@Slf4j
public class BookAPI {

    private BookService bookService;
    private APIResponseBuilderFactory apiResponseBuilderFactory;

    public BookAPI(BookService bookService, APIResponseBuilderFactory apiResponseBuilderFactory){
        this.bookService = bookService;
        this.apiResponseBuilderFactory = apiResponseBuilderFactory;
    }

    /**
     * 도서 목록 조회
     * @return
     */
    @RequestMapping(value = "/book/list" , method = RequestMethod.GET)
    public APIResponse getBookList(){

        List<BookVO> bookList = bookService.getBookList();

        return apiResponseBuilderFactory.success().setData(bookList).build();
    }

    /**
     * 도서 내용 조회
     * @param bookSeq
     * @return
     */
    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookDetail(@PathVariable int bookSeq){
        BookVO bookVo = bookService.getBookDetail(bookSeq);

        return ResponseEntity.ok(bookVo);
    }

    /**
     * 도서 삭제
     * @param bookSeq
     * @return
     */
    @RequestMapping(value = "/admin/book/delete/{bookSeq}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable int bookSeq){
        bookService.deleteBook(bookSeq);
        return ResponseEntity.ok().build();
    }




}
