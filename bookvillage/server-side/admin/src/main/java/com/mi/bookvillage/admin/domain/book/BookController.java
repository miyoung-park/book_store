package com.mi.bookvillage.admin.domain.book;


import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.common.util.file.FileUtil;
import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.common.util.string.StringUtil;
import com.mi.bookvillage.common.domain.Book.BookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Admin_BookAPI
 */
@Slf4j
@RestController
@RequiredArgsConstructor // TODO: 생성자 주입방식 + @RequiredArgsConstructor (완)
public class BookController {

    private final BookService bookService;
    private final FileUtil fileUtil;
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
     * 도서정보 조회
     */
    @RequestMapping(value = "/book/detail/{bookSeq}" , method = RequestMethod.GET)
    public ApiResponse getBookDetail(@PathVariable int bookSeq) {

        BookVO book = bookService.getBookDetail(bookSeq);
        List<FileVO> fileList = bookService.getBookFile(bookSeq);
        return apiResponseBuilderFactory.success()
                                        .putValue("bookInfo" , book)
                                        .putValue("files", fileList)
                                        .build();
    }



    /**
     * 도서 등록
     * TODO: @Transactional 에 대해서 알아보기 (완)
     * Transaction 의 속성을 지키게 된다.
     * 하지만 @Transactional 은 기본적으로 UncheckedException( 예상하지 못한 에러 ), Error 만을 Rollback 한다고 한다.
     * 모든 예외( CheckedException 예상되는 예외 포함 )에 대해서 Rollback 을 적용하고 싶을 때
     * (rollbackFor = Exception.class) 를 붙여주면 된다.
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/book/add" , method = RequestMethod.POST)
    public ApiResponse addBook( @ModelAttribute BookVO book, // TODO : 프론트에서 어떻게 보내느냐에 따라 @ModelAttribute / @RequestPart("bookObj") 다르게 적용 가능 (@RequestPart 정리 완)
                                @RequestParam(required = false) List<MultipartFile> files ) throws IOException {

        bookService.addBook(book);
        List<FileVO> fileList = fileUtil.uploadFiles(files, book.getBookSeq());
        bookService.addFiles(fileList);

        return apiResponseBuilderFactory.success().build();

    }

    /**
     * 도서 정보 업데이트
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/book/update/{bookSeq}" , method = RequestMethod.PUT)
    public ApiResponse updateBook( @PathVariable("bookSeq") int bookSeq
                                 , @ModelAttribute BookVO book
                                 , @RequestParam(required = false) List<MultipartFile> files
                                 , @RequestParam(required = false) List<Integer> deleteFiles) throws IOException {


        // book 정보 업데이트
        bookService.updateBook(book);

        // new image 추가
        List<FileVO> fileList = fileUtil.uploadFiles(files, bookSeq);
         bookService.addFiles(fileList);

        // 기존 image 삭제
        bookService.deleteFiles(deleteFiles);
        return apiResponseBuilderFactory.success().build();
    }


    /**
     * 도서 삭제 + 도서 이미지 삭제
     */
    @RequestMapping(value = "/book/delete/{bookSeq}", method = RequestMethod.DELETE)
    public ApiResponse deleteBook(@PathVariable int bookSeq){
        bookService.deleteBook(bookSeq);
        return apiResponseBuilderFactory.success().build();
    }




}
