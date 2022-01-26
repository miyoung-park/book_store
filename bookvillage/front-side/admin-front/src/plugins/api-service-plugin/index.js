// import Vue from "vue";
// import axios from 'axios';
// import _ from 'lodash';
// import ApiServiceFactory from "./api-service-factory";
import { store } from '@/store/index';
import ApiServiceError from "./api-service-error";
import {ApiServiceErrorEventBus} from './api-service-error'

const ApiServices = {
    install(Vue , options) {

        /** Event emitter **/
        const serviceErrorEventBus = new ApiServiceErrorEventBus();  // 만들어 놓은 이벤트에 관심있으면 알려주는 용도


        /** Default error handler
         TODO: 한번에 처리되길 원하는, 공통되는 에러로직은 전역적으로 처리해주기( 토큰문제로 인한 로그아웃 처리 ) (완)
         : 에러처리 로직이 무조건 이 위치에 있을 필요는 없지만 그렇다고 여기 저기서 사용되서도 안된다. 반드시 의도한 케이스로 되게끔 책임자 한 명이 박아두고 써야 한다.
        **/
        serviceErrorEventBus.setDefaultErrorHandler(  (e)=>{
            const errorCode = e.errorCode;
            const errorMessage = e.errorMessage;

            if( errorCode == '610' || errorCode == '620' || errorCode == '630') { // Token Error
                alert(errorMessage);
                store.commit('logout');
            }
            console.log( `my site default handler ${e.toString()}` )
        });


        /**
         * 전역 메소드
         * @param errorCode
         * @param handler
         * @param propagation
         */
        Vue.prototype.$addApiErrorHandler = function ( errorCode, handler, propagation) {
            serviceErrorEventBus.on(errorCode, handler, propagation);
        }
        Vue.prototype.$removeApiErrorHandler = function (errorCode, handler) {
            serviceErrorEventBus.off(errorCode,handler);
        }
        Vue.prototype.$removeApiErrorHandler = function ( handler) {
            serviceErrorEventBus.off(handler);
        }


        /**
         * TODO: Vue 자체 기능 중 하나, Vue document 참고 (완)
         * : 잡히지 않은 오류에 대한 핸들러를 할당하는 역할( axios 에서 catch 로 오류를 잡아버리면 안됨)
         * 핸들러는 오류 및 Vue 인스턴스와 함께 호출된다.
         **/
        Vue.config.errorHandler = function(err, vm, info) {  // eslint-disable-line no-unused-vars


            if( err instanceof ApiServiceError ){
                serviceErrorEventBus.emit( err.errorCode , err);
            }
            // TODO: API에러가 아닌 경우 ? 누군가 처리하게끔 다시 던져줘야함 ( 나중에 처리할 문제 )
        }

        /** inject service
        const apiServiceFactory = new ApiServiceFactory( {host:options.host} );
        _.forEach( apiServiceFactory.makeBaseServices() , function(service, serviceName) {
            Vue.prototype[serviceName] = service;
        });
        _.forEach( apiServiceFactory.makeExtServices("https://ext.domain.com/") , function(service, serviceName) {
            Vue.prototype[serviceName] = service;
        }); */
    }
};

export default ApiServices;
