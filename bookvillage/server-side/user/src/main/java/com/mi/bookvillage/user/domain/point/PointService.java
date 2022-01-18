package com.mi.bookvillage.user.domain.point;

import com.mi.bookvillage.common.mapper.PointMapper;
import com.mi.bookvillage.common.vo.PointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointMapper pointMapper;

    public List<PointVO> getPointListById(String userId){
        List<PointVO> pointList = pointMapper.getPointListById(userId);
        return pointList;
    }

    public List<PointVO> getPointListBySeq(int userSeq){
        List<PointVO> pointList = pointMapper.getPointListBySeq(userSeq);
        return pointList;
    }

    public PointVO getPointListByRentalSeq(int rentalSeq){
        PointVO pointVO = pointMapper.getPointListByRentalSeq(rentalSeq);
        return pointVO;
    }


    public int getPreviousTotalPoint(int userSeq){
        Integer totalPoint = pointMapper.getPreviousTotalPoint(userSeq);
        return (totalPoint == null || totalPoint == 0 ? 0 : totalPoint);
    }
    public void transactionPoint(PointVO pointVO){
        pointMapper.transactionPoint(pointVO);
    }






}
