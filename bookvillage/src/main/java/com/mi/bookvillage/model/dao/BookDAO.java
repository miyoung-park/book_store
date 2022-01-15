package com.mi.bookvillage.model.dao;

import com.mi.bookvillage.model.vo.BookVO;
import com.mi.bookvillage.common.util.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDAO {


    List<BookVO> getBookList();
    BookVO getBookDetail(int bookSeq);
    int addBook(BookVO bookVO);
    void addFiles(FileVO fileVO);
    List<FileVO> getBookFile(int bookSeq);
    void updateBook(BookVO book);
    void deleteBook(int bookSeq);
    void deleteFilesWithBook(int bookSeq);
    void deleteFiles(int fileSeq);


}
