// import Vue from "vue";
// import axios from 'axios';
// import _ from 'lodash';

import ApiServiceError from "./api-service-error";
// import ApiServiceFactory from "./api-service-factory";
import {ApiServiceErrorEventBus} from './api-service-error'

const ApiServices = {
    install(Vue) {

        /** event emitter **/
        const serviceErrorEventBus = new ApiServiceErrorEventBus();  // 만들어 놓은 이벤트에 관심있으면 알려주는 용도

        // TODO: 꼭 여기 안 있어도 되지만 여기 저기서 사용되서도 안된다. 반드시 의도한 케이스로 되게끔 책임자 한명이 박아두고 써야 한다.
        // TODO: 여기서는 전역적으로 사용할 것들은 처리하면 될듯 ( 토큰 만료 같은... ) (e)를 까보면 에러가 나올 것.
        // TODO: 여러 처리를 한 번에 하고 싶으면 분기처리로 진행
        /** Default error handler **/
        serviceErrorEventBus.setDefaultErrorHandler(  (e)=>{
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
         * TODO: Vue 자체 기능 중 하나, Vue document 참고 !
         **/
        Vue.config.errorHandler = function(err, vm, info) {
            console.log( vm , info)
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
