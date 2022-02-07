package com.mi.bookvillage.admin.domain.point;

import com.mi.bookvillage.common.common.annotation.JwtAuthorization;
import com.mi.bookvillage.common.common.response.ApiResponse;
import com.mi.bookvillage.common.common.response.ApiResponseBuilderFactory;
import com.mi.bookvillage.common.domain.point.PointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Admin_PointAPI
 */
@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;
    private final ApiResponseBuilderFactory apiResponseBuilderFactory;


    /**
     * 포인트 목록 조회
     */
    @JwtAuthorization
    @RequestMapping(value = "/point/list/{userSeq}", method = RequestMethod.GET)
    public ApiResponse getPointListBySeq(@PathVariable int userSeq,
                                          HttpServletRequest request ){

        List<PointVO> pointList = pointService.getPointListBySeq(userSeq);

        return apiResponseBuilderFactory.success().setData(pointList).build();
    }




}
