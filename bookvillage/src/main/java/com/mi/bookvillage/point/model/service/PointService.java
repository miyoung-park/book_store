package com.mi.bookvillage.point.model.service;

import com.mi.bookvillage.customer.model.vo.CustomerVO;
import com.mi.bookvillage.point.model.dao.PointDAO;
import com.mi.bookvillage.point.model.vo.PointVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    private PointDAO pointDAO;

    public PointService(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    public List<PointVO> getPointList(String userId){
        List<PointVO> pointList = pointDAO.getPointListById(userId);
        return pointList;
    }

    public void transactionPoint(PointVO pointVO){
        pointDAO.transactionPoint(pointVO);
    }






}
