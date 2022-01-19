package com.mi.bookvillage.admin.domain.book;


import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.domain.Book.BookMapper;
import com.mi.bookvillage.common.domain.Book.BookVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    /**
     * 도서목록 조회
     */
    public List<BookVO> getBookList() {
        return bookMapper.getBookList();
    }


    /**
     * 도서정보 조회
     */
    public BookVO getBookDetail(int bookSeq){
        return bookMapper.getBookDetail(bookSeq);
    }


    /**
     * 도서 파일 정보 조회
     */
    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookMapper.getBookFile(bookSeq);
        return fileList;
    }


    /**
     * 도서 등록
     */
    public void addBook(BookVO bookVO){
        bookMapper.addBook(bookVO);
    }


    /**
     * 도서 파일 등록
     */
    public void addFiles(List<FileVO> files){
        System.out.println(files);
        for(int i = 0; i < files.size(); i ++) {
            bookMapper.addFiles(files.get(i));
        }
    }


    /**
     * 도서 정보 업데이트
     */
    public void updateBook(BookVO bookVO){
        bookMapper.updateBook(bookVO);
    }


    /**
     * 도서 정보 삭제
     */
    public void deleteBook(int bookSeq) {
        // 해당 도서정보 삭제
        bookMapper.deleteBook(bookSeq);
        // 해당 도서이미지 삭제
        bookMapper.deleteFilesWithBook(bookSeq);
    }


    /**
     * 도서 파일 정보 삭제
     */
    // TODO: 실제로도 삭제되게끔 !
    public void deleteFiles(List<Integer> deleteFiles){
        if( deleteFiles != null && deleteFiles.size() > 0 ){
            for( int i = 0; i < deleteFiles.size(); i++) {
                bookMapper.deleteFiles(deleteFiles.get(i));
            }
        }
    }


}
