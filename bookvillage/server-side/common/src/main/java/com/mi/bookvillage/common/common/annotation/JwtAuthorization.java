package com.mi.bookvillage.common.common.annotation;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomAnnotation( 커스텀 어노테이션 )
 * : Meta Annotation 을 사용하는 구조
 * : Field Type (enum / String / 기본 자료형 / 기존 자료형의 배열 ... )
 */

/**
 * @Target : Annotation 이 적용 될 위치를 선택
 * - ElementType.PACKAGE : 패키지 선언
 * - ElementType.TYPE : 타입 선언
 * - ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
 * - ElementType.CONSTRUCTOR : 생성자 선언
 * - ElementType.FIELD : 멤버 변수 선언
 * - ElementType.LOCAL_VARIABLE : 지역 변수 선언
 * - ElementType.METHOD : 메소드 선언
 * - ElementType.PARAMETER : 파라미터( 전달인자 ) 선언
 * - ElementType.TYPE_PARAMETER : 파라미터 타입 선언
 * - ElementType.TYPE_USE : 타입 선언
 */

/**
 * @Retention : 컴파일러가 어노테이션을 다루는 방법, 어느 시점까지 영향을 미칠 건지 결정하는 역할
 * - RetentionPolicy.SOURCE : 컴파일 전까지만 유효
 * - RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 때까지 유효
 * - RetentionPolicy.RUNTIME : 컴파일 이후에 런타임 시기에도 참조 가능 !
 */

@Target({ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtAuthorization {

}
