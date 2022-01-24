package com.mi.bookvillage.common.domain.Rental;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RentalMapper {

    List<RentalVO> getAllRentalList();
    List<RentalVO> getRentalList(int userSeq);
    RentalVO getRentalDetail(int rentalSeq);
    void rentalBook(RentalVO rentalVO);
    void updateRentalStatus(RentalVO rentalVO);
    void returnBook(RentalVO rentalVO);
}
