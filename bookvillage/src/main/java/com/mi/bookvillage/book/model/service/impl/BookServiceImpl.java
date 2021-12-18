package com.mi.bookvillage.book.model.service.impl;

import com.mi.bookvillage.book.model.dao.BookDAO;
import com.mi.bookvillage.book.model.service.BookService;
import com.mi.bookvillage.book.model.vo.BookVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDao;

    public BookServiceImpl(BookDAO bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<BookVO> getBookList() {
        return bookDao.getBookList();
    }
}
