package com.mi.bookvillage.user.domain.book;


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

    public List<BookVO> getBookList() {
        return bookMapper.getBookList();
    }


    public BookVO getBookDetail(int bookSeq){
        return bookMapper.getBookDetail(bookSeq);
    }


    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookMapper.getBookFile(bookSeq);
        return fileList;
    }


}
