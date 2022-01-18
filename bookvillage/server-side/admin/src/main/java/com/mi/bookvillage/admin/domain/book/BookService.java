package com.mi.bookvillage.admin.domain.book;


import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.mapper.BookMapper;
import com.mi.bookvillage.common.vo.BookVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    public List<BookVO> getBookList() {
        return bookMapper.getBookList();
    }


    public BookVO getBookDetail(int bookSeq){
        return bookMapper.getBookDetail(bookSeq);
    }


    public void addBook(BookVO bookVO){
        bookMapper.addBook(bookVO);
    }


    public void addFiles(List<FileVO> files){
        for(int i = 0; i < files.size(); i ++) {
            bookMapper.addFiles(files.get(i));
        }
    }


    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookMapper.getBookFile(bookSeq);
        return fileList;
    }

    public void updateBook(BookVO bookVO){
        bookMapper.updateBook(bookVO);
    }


    public void deleteBook(int bookSeq) {
        // 해당 도서정보 삭제
        bookMapper.deleteBook(bookSeq);
        // 해당 도서이미지 삭제
        bookMapper.deleteFilesWithBook(bookSeq);
    }


    public void deleteFiles(List<Integer> deleteFiles){
        if( deleteFiles != null && deleteFiles.size() > 0 ){
            for( int i = 0; i < deleteFiles.size(); i++) {
                bookMapper.deleteFiles(deleteFiles.get(i));
            }
        }
    }


}
