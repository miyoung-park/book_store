package com.mi.bookvillage.user.domain.book;


import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.common.util.file.FileVO;
import com.mi.bookvillage.common.domain.book.BookMapper;
import com.mi.bookvillage.common.domain.book.BookVO;
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
        BookVO book = bookMapper.getBookDetail(bookSeq);
        if( book == null ) {
            throw new ApiException(ApiServiceErrorCode.DATA_NOT_FOUND, "도서정보를 확인할 수 없습니다.");
        }
        return book;
    }


    public List<FileVO> getBookFile( int bookSeq ){
        List<FileVO> fileList = bookMapper.getBookFile(bookSeq);
        return fileList;
    }


}
