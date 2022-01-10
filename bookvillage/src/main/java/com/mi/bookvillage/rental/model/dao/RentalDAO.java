package com.mi.bookvillage.rental.model.dao;

import com.mi.bookvillage.rental.model.vo.RentalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentalDAO {

    List<RentalVO> getRentalList(int userSeq);
    void rentalBook(RentalVO rentalVO);
}
