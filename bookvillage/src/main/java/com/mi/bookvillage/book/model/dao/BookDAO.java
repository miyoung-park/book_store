package com.mi.bookvillage.book.model.dao;

import com.mi.bookvillage.book.model.vo.BookVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BookDAO {


    List<BookVO> getBookList();



}
