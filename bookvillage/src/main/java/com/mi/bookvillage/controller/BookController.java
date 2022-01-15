package com.mi.bookvillage.controller;

import com.mi.bookvillage.model.service.BookService;
import com.mi.bookvillage.model.vo.BookVO;
import com.mi.bookvillage.model.service.RentalService;
import com.mi.bookvillage.common.response.APIResponse;
import com.mi.bookvillage.common.util.file.FileUtil;
import com.mi.bookvillage.common.util.file.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * BookAPI
 */
@RestController
@Slf4j
@RequiredArgsConstructor // TODO: 생성자 주입방식 + @RequiredArgsConstructor 공부해보기
public class BookController {

    private final BookService bookService;
    private final RentalService rentalService;
    private final FileUtil fileUtil;

    /**
     * 도서 목록 조회 : common
     * @return
     */
    @RequestMapping(value = "/book/list" , method = RequestMethod.GET)
    public ResponseEntity<?> getBookList(){
        List<BookVO> bookList = bookService.getBookList();
        return APIResponse.builder().success(bookList).build();
    }


    /**
     * 도서 조회 : common
     * @param bookSeq
     * @return
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


    /* admin */

    /**
     * 도서 등록 : admin
     * @param book
     * @return
     */
    // TODO: @Transactional 에 대해서 알아보기
    @RequestMapping(value = "/book/add" , method = RequestMethod.POST)
    public ResponseEntity<?> addBook( @ModelAttribute BookVO book, // TODO : 프론트에서 어떻게 보내느냐에 따라 @ModelAttribute / @RequestPart("bookObj") 다르게 적용 가능
                                      @RequestParam(required = false) List<MultipartFile> files ) {
        try {
            bookService.addBook(book);
            List<FileVO> fileList = fileUtil.uploadFiles(files, book.getBookSeq());
            bookService.addFiles(fileList);
            log.info("Success ::: success insert book add files");
            return APIResponse.builder().success().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return APIResponse.builder().error("파일 등록에 실패했습니다.").build();
    }

    /**
     * 도서 정보 업데이트 : admin
     * @param book
     * @return
     */
    @RequestMapping(value = "/book/update/{bookSeq}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook( @PathVariable("bookSeq") int bookSeq
                                 , @ModelAttribute BookVO book
                                 , @RequestParam(required = false) List<MultipartFile> files
                                 , @RequestParam(required = false) List<Integer> deleteFiles) {
        try {
            // book 정보 업데이트
            bookService.updateBook(book);
            // new image 추가
            List<FileVO> fileList = fileUtil.uploadFiles(files, bookSeq);
            bookService.addFiles(fileList);
            // 기존 image 삭제
            bookService.deleteFiles(deleteFiles);
            log.info("Success ::: success update book and files");
            return APIResponse.builder().success().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return APIResponse.builder().error().build();

    }


    /**
     * 도서 삭제 + 도서 이미지 삭제 : admin
     * @param bookSeq
     * @return
     */
    @RequestMapping(value = "/book/delete/{bookSeq}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable int bookSeq){
        // 만약 해당 도서가 대여 중이라면 반납 후 삭제 할 수 있도록 Exception +

        bookService.deleteBook(bookSeq); // 도서정보 + 도서 이미지 삭제 ---- 삭제 log를 남기는 형태로 history
        log.info("Success ::: success delete book and files");
        return APIResponse.builder().success().build();
    }




}
