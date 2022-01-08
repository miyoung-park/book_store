package com.mi.bookvillage.point.model.dao;

import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.point.model.vo.PointVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointDAO {

    List<PointVO> getPointListById(String userId);
    List<PointVO> getPointListBySeq(int userSeq);
    Integer getPreviousTotalPoint(int userSeq);
    void transactionPoint(PointVO pointVO);
}
