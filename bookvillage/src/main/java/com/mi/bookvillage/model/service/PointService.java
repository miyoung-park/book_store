package com.mi.bookvillage.model.service;

import com.mi.bookvillage.model.dao.PointDAO;
import com.mi.bookvillage.model.vo.PointVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    private PointDAO pointDAO;

    public PointService(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    public List<PointVO> getPointListById(String userId){
        List<PointVO> pointList = pointDAO.getPointListById(userId);
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
