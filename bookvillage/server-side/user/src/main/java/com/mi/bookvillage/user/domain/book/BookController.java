package com.mi.bookvillage.user.domain.book;


import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.domain.Book.BookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;

    /**
     * 도서 목록 조회
     */
    @RequestMapping(value = "/book/list" , method = RequestMethod.GET)
    public ApiResponse getBookList(){
        List<BookVO> bookList = bookService.getBookList();
        return apiResponseBuilderFactory.success().setData(bookList).build();
    }


    /**
     * 도서 조회
     */
    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public ApiResponse getBookDetail(@PathVariable int bookSeq) {

        BookVO book = bookService.getBookDetail(bookSeq);
        List<FileVO> fileList = bookService.getBookFile(bookSeq);
        return apiResponseBuilderFactory.success().putValue("bookInfo" , book ).putValue("files" , fileList ).build();

    }





}
