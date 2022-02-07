package com.mi.bookvillage.common.domain.book;

import com.mi.bookvillage.common.common.util.file.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {


    List<BookVO> getBookList();
    BookVO getBookDetail(int bookSeq);
    int addBook(BookVO bookVO);
    void addFiles(List<FileVO> files);
    List<FileVO> getBookFile(int bookSeq);
    void updateBook(BookVO book);
    void deleteBook(int bookSeq);
    void deleteFilesWithBook(int bookSeq);
    void deleteFiles(List<Integer> fileSeq);


}
