package com.mi.bookvillage.book.model.service;

import com.mi.bookvillage.book.model.dao.BookDAO;
import com.mi.bookvillage.book.model.vo.BookVO;
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
}
