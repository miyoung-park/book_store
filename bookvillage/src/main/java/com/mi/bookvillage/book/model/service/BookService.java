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

    public void addBook(BookVO bookVO , List<FileVO> files){
        bookDao.addBook(bookVO);
        System.out.println(bookVO);
        for(int i = 0; i < files.size(); i ++) {
            files.get(i).setBookSeq(bookVO.getBookSeq());
            bookDao.addFile(files.get(i));
        }
    }

    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookDao.getBookFile(bookSeq);
        return fileList;
    }

    public void deleteBook(int bookSeq) {
        bookDao.deleteBook(bookSeq);
    }
}
