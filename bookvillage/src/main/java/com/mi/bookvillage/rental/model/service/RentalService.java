package com.mi.bookvillage.rental.model.service;

import com.mi.bookvillage.rental.model.dao.RentalDAO;
import com.mi.bookvillage.rental.model.vo.RentalVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private RentalDAO rentalDAO;

    public RentalService(RentalDAO rentalDAO){
        this.rentalDAO = rentalDAO;
    }

    public List<RentalVO> getRentalList(int userSeq){
        List<RentalVO> rentalList = rentalDAO.getRentalList(userSeq);
        return rentalList;
    }

    public void rentalBook(RentalVO rentalVO){
        rentalDAO.rentalBook(rentalVO);
    }


}
