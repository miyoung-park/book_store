package com.mi.bookvillage.common.service;

import com.mi.bookvillage.common.mapper.PointDAO;
import com.mi.bookvillage.common.vo.PointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointDAO pointDAO;

    public List<PointVO> getPointListById(String userId){
        List<PointVO> pointList = pointDAO.getPointListById(userId);
        System.out.println("pointList :  " + pointList);
        return pointList;
    }

    public List<PointVO> getPointListBySeq(int userSeq){
        List<PointVO> pointList = pointDAO.getPointListBySeq(userSeq);
        return pointList;
    }

    public PointVO getPointListByRentalSeq(int rentalSeq){
        PointVO pointVO = pointDAO.getPointListByRentalSeq(rentalSeq);
        return pointVO;
    }


    public int getPreviousTotalPoint(int userSeq){
        Integer totalPoint = pointDAO.getPreviousTotalPoint(userSeq);
        return (totalPoint == null || totalPoint == 0 ? 0 : totalPoint);
    }
    public void transactionPoint(PointVO pointVO){
        pointDAO.transactionPoint(pointVO);
    }






}
