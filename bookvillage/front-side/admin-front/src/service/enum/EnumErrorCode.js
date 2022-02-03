
const ErrorCode = {

    /**
     * 토큰이 없음
     */
    TOKEN_NOT_EXIST : '610',

    /**
     * 토큰이 만료됨
     */
    TOKEN_EXPIRED : "620",

    /**
     * 토큰이 무효함
     */
    TOKEN_NOT_VALID : "630",

    /**
     * 회원정보 없음
     */
    INVALID_USER : "U10001",

    /**
     * 잘못된 비밀번호
     */
    INVALID_PASSWORD : "U10002",

    /**
     * 이미 가입한 회원
     */
    JOINED_USER : "U10003",

    /**
     * 포인트 부족
     */
    NOT_ENOUGH_POINT : "P50001",

    /**
     * 데이터 정보 없음
     */
    DATA_NOT_FOUND : "E40001",

};


export default ErrorCode;
