package com.mi.bookvillage.user.domain.point;

import com.mi.bookvillage.common.domain.Point.PointMapper;
import com.mi.bookvillage.common.domain.Point.PointVO;
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

    public int getPreviousTotalPoint(int userSeq){
        Integer totalPoint = pointMapper.getPreviousTotalPoint(userSeq);
        return (totalPoint == null || totalPoint == 0 ? 0 : totalPoint);
    }
    public void transactionPoint(PointVO pointVO){
        pointMapper.transactionPoint(pointVO);
    }






}
