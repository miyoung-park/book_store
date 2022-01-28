package com.mi.bookvillage.user.domain.point;

import com.mi.bookvillage.common.common.exceptions.ApiException;
import com.mi.bookvillage.common.common.exceptions.ApiServiceErrorCode;
import com.mi.bookvillage.common.common.util.string.StringUtil;
import com.mi.bookvillage.common.domain.Point.PointMapper;
import com.mi.bookvillage.common.domain.Point.PointVO;
import com.mi.bookvillage.common.domain.User.UserVO;
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

    public void transactionPoint(PointVO point, UserVO user){
        // 포인트 유효성 검사
        if( !StringUtil.checkPositiveNumber( point.getPointTransaction() ) ){
            throw new ApiException(ApiServiceErrorCode.NOT_POSITIVE_POINT, "포인트는 양수만 입력해주세요.");
        }
        // 유저 아이디 / 이전 포인트 SET
        int previousPoint = getPreviousTotalPoint(user.getUserSeq());
        point.setPreviousPoint(previousPoint);
        point.setUserSeq(user.getUserSeq());

        pointMapper.transactionPoint(point);
    }






}
