package com.mi.bookvillage.book.api;

import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.response.APIResponseBuilder;
import com.mi.bookvillage.common.response.APIResponseBuilderFactory;
import com.mi.bookvillage.common.util.file.FileUtil;
import com.mi.bookvillage.common.util.file.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
                // APIResponseBuilder 인스턴스 생성    // 데이터 세팅       // build
        return apiResponseBuilderFactory.success().setData(bookList).build();
    }

    /**
     * 도서 조회
     * @param bookSeq
     * @return
     */
    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public APIResponse getBookDetail(@PathVariable int bookSeq) {
        BookVO book = bookService.getBookDetail(bookSeq);
        if( book == null) {
            throw new RuntimeException("Cannot find book");
        }
        List<FileVO> files = bookService.getBookFile(bookSeq);

        return apiResponseBuilderFactory.success()
                                        .putValue("bookInfo" , book)
                                        .putValue("files", files)
                                        .build();
    }


    /**
     * 도서 등록
     * @param book
     * @return
     */
    @RequestMapping(value = "/book/add" , method = RequestMethod.POST)
    public APIResponse addBook( @ModelAttribute BookVO book
                               ,@RequestParam(required = false) List<MultipartFile> files ) {
        try {
            List<FileVO> fileList = FileUtil.uploadFiles(files, book.getBookSeq());
            bookService.addBook(book);
            bookService.addFile(fileList);
            return apiResponseBuilderFactory.success().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // error 로 바꿔야 함
        return apiResponseBuilderFactory.success().build();
    }

    /**
     * 도서 정보 업데이트
     * @param book
     * @return
     */
    @RequestMapping(value = "/book/update/{bookSeq}" , method = RequestMethod.PUT)
    public APIResponse updateBook( @PathVariable("bookSeq") int bookSeq
                                 , @ModelAttribute BookVO book
                                 , @RequestParam(required = false) List<MultipartFile> files
                                 , @RequestParam(required = false) List<Integer> deleteFiles) {
        try {
            // book 정보 업데이트
            bookService.updateBook(book);
            // new image 추가
            List<FileVO> fileList = FileUtil.uploadFiles(files, bookSeq);
            bookService.addFile(fileList);
            // 기존 image 삭제
            bookService.deleteFiles(deleteFiles);
            return apiResponseBuilderFactory.success().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // error 로 바꿔야 함
        return apiResponseBuilderFactory.success().build();

    }


    /**
     * 도서 삭제 + 도서 이미지 삭제
     * @param bookSeq
     * @return
     */
    @RequestMapping(value = "/book/delete/{bookSeq}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable int bookSeq){
        bookService.deleteBook(bookSeq); // 도서정보 + 도서 이미지 삭제
        return ResponseEntity.ok().build();
    }




}
