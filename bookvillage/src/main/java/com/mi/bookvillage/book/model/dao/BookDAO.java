package com.mi.bookvillage.book.model.dao;

import com.mi.bookvillage.book.model.vo.BookVO;
import com.mi.bookvillage.common.util.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDAO {


    List<BookVO> getBookList();
    BookVO getBookDetail(int bookSeq);
    int addBook(BookVO bookVO);
    void addFile(FileVO fileVO);
    void deleteBook(int bookSeq);
    void deleteFile(int bookSeq);

}
