package com.mi.bookvillage.user.domain.book;


import com.mi.bookvillage.common.common.response.APIResponse;
import com.mi.bookvillage.common.common.util.file.FileUtil;
import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.domain.Book.BookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor // TODO: 생성자 주입방식 + @RequiredArgsConstructor 공부해보기
public class BookController {

    private final BookService bookService;
    private final FileUtil fileUtil;

    /**
     * 도서 목록 조회
     */
    @RequestMapping(value = "/book/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookList(){
        List<BookVO> bookList = bookService.getBookList();
        return APIResponse.builder().success(bookList).build();
    }


    /**
     * 도서 조회
     */
    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookDetail(@PathVariable int bookSeq) {

        BookVO book = bookService.getBookDetail(bookSeq);
        // TODO:  StringUtil 을 직접 만들어서  UTIL 처리하기
        if( book == null ) {
            log.error("exception ::: Cannot find book");
            return APIResponse.builder().error("잘못된 도서정보 입니다.").build();
        }

        List<FileVO> fileList = bookService.getBookFile(bookSeq);
        return APIResponse.builder().success("bookInfo" , book)
                                    .success("files", fileList)
                                    .build();
    }





}
