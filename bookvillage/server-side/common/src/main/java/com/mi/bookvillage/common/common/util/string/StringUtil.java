package com.mi.bookvillage.common.common.util.string;

// TODO: 내가 필요해서 쓸 수 있는 StringUtil 만들어보기
public class StringUtil {

    public static boolean isEmpty(Object value) {

        if(isEmpty(value) || value == null || value.equals("")){
            return false;
        }

        return true;
    }

    public static boolean checkNegativeNumber(String number){
        if( 0 > Integer.parseInt( number )){
            return false;
        }
        return true;
    }


}
