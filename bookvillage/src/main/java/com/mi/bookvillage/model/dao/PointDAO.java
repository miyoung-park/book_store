package com.mi.bookvillage.model.dao;

import com.mi.bookvillage.model.vo.PointVO;
import com.mi.bookvillage.model.vo.RentalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointDAO {

    List<PointVO> getPointListById(String userId);
    List<PointVO> getPointListBySeq(int userSeq);
    PointVO getPointListByRentalSeq(int rentalSeq);
    Integer getPreviousTotalPoint(int userSeq);
    void transactionPoint(PointVO pointVO);
}
