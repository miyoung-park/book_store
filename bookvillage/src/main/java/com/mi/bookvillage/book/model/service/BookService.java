package com.mi.bookvillage.book.model.service;

import com.mi.bookvillage.book.model.dao.BookDAO;
import com.mi.bookvillage.book.model.vo.BookVO;
import com.mi.bookvillage.common.util.file.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookDAO bookDao;

    public BookService(BookDAO bookDao){
        this.bookDao = bookDao;
    }

    public List<BookVO> getBookList() {
        return bookDao.getBookList();
    }

    public BookVO getBookDetail(int bookSeq){
        return bookDao.getBookDetail(bookSeq);
    }

    public void addBook(BookVO bookVO){
        bookDao.addBook(bookVO);
    }

    public void addFile(List<FileVO> files){
        for(int i = 0; i < files.size(); i ++) {
            bookDao.addFile(files.get(i));
        }
    }

    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookDao.getBookFile(bookSeq);
        return fileList;
    }

    public void updateBook(BookVO bookVO){
        bookDao.updateBook(bookVO);
    }

    public void deleteBook(int bookSeq) {
        // 해당 도서정보 삭제
        bookDao.deleteBook(bookSeq);
        // 해당 도서이미지 삭제
        bookDao.deleteFilesWithBook(bookSeq);
    }

    public void deleteFiles(List<Integer> deleteFiles){
        if( deleteFiles != null && deleteFiles.size() > 0 ){
            for( int i = 0; i < deleteFiles.size(); i++) {
                bookDao.deleteFiles(deleteFiles.get(i));
            }
        }
    }


}
