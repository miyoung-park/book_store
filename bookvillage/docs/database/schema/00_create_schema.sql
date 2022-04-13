#20220406 테이블 생성

create table admin
(
    user_seq  int auto_increment comment '관리자SEQ'
        primary key,
    user_id   varchar(2000)                 not null comment '아이디',
    user_pw   varchar(2000)                 not null comment '비밀번호',
    user_name varchar(2000)                 not null
);

create table book
(
    book_seq        int auto_increment comment '도서SEQ'
        primary key,
    book_title      varchar(2000)                       not null comment '책 제목',
    book_price      varchar(2000)                       not null comment '책 가격',
    book_rental_fee varchar(2000)                       not null comment '책 대여비',
    book_memo       varchar(2000)                       null comment '책 메모',
    book_reg_dt     datetime                            not null comment '등록날짜',
    book_update_dt  datetime                           null comment '수정날짜'
);

create table book_file
(
    file_seq         int auto_increment comment '파일SEQ'
        primary key,
    book_seq         int                                 not null comment '책 고유번호',
    origin_file_name varchar(2000)                       not null comment '원본 파일명',
    rename_file_name varchar(2000)                       not null comment '저장 파일명',
    save_path        varchar(2000)                       not null comment '저장 경로',
    reg_date         datetime                            not null comment '파일등록날짜',
    IMAGE_WEB_PATH   varchar(2000)                       null
);

create table customer
(
    user_seq       int auto_increment comment '유저SEQ'
        primary key,
    user_name      varchar(2000)                           not null comment '이름',
    user_id        varchar(2000)                           not null comment '아이디',
    user_pw        varchar(2000)                           not null comment '비밀번호',
    user_tell      varchar(2000)                           not null comment '전화번호',
    user_reg_dt    datetime                                not null comment '등록날짜',
    user_update_dt datetime                                null comment '수정날짜',
    user_role      varchar(2000) default 'customer'        not null,
    USER_BIRTH     varchar(2000)                           null
);

create table point
(
    point_seq          int auto_increment comment '포인트거래SEQ'
        primary key,
    user_seq           int                                     not null comment '유저번호',
    rental_seq         int                                     null comment '대여번호',
    previous_point     int           default 0                 not null comment '이전포인트',
    point_transaction  int                                     not null comment '거래금액',
    total_point        int                                     null comment '남은포인트',
    transaction_reg_dt datetime                                not null comment '등록날짜',
    point_status       varchar(2000)                           null comment '00:적립/ 01:차감/ 03:연체차감/ 04:취소',
    STATUS_REASON      varchar(2000) default '충전'              not null
);

create table rental
(
    rental_seq        int auto_increment comment '대여SEQ'
        primary key,
    book_seq          int                                     not null comment '책 고유번호',
    user_seq          int                                     not null comment '유저번호',
    rental_update_dt  datetime                               null comment '수정날짜',
    RENTAL_REG_DT     datetime                               null comment '대여일',
    PREDICT_RETURN_DT datetime                               null comment '반납예정일',
    RETURN_DT         datetime                               null comment '반납일',
    RENTAL_DT         datetime                               null comment '대여일',
    RENTAL_STATUS     varchar(2000) default '00'              null comment '00: 대여신청 /01: 대여중 /02: 반납완료 /03: 연체 /04: 대여취소'
);

