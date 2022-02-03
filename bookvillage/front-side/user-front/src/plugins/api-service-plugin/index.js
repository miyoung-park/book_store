// import Vue from "vue";
// import axios from 'axios';
// import _ from 'lodash';
// import ApiServiceFactory from "./api-service-factory";
import ApiServiceError from "./api-service-error";
import {ApiServiceErrorEventBus} from './api-service-error'
import { store } from "@/store";

const ApiServices = {
    install(Vue, options) { // eslint-disable-line no-unused-vars

        /** Event emitter **/
        const serviceErrorEventBus = new ApiServiceErrorEventBus();

        /** Default error handler **/
        serviceErrorEventBus.setDefaultErrorHandler( (e)=>{
            console.log(e);
            const errorCode = e.errorCode;
            const errorMessage = e.errorMessage;
            if( errorCode === '610' || errorCode === '620' || errorCode === '630') { // Token Error
                alert(errorMessage);
                store.commit('logout');
            }
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
        Vue.config.errorHandler = function(err, vm, info) { // eslint-disable-line no-unused-vars
            if( err instanceof ApiServiceError ){
                serviceErrorEventBus.emit( err.errorCode , err);
            }
        }

        /** inject service
        const apiServiceFactory = new ApiServiceFactory( {host:options.host} );
        _.forEach( apiServiceFactory.makeBaseServices() , function(service, serviceName) {
            Vue.prototype[serviceName] = service;
        });
        _.forEach( apiServiceFactory.makeExtServices("https://ext.domain.com/") , function(service, serviceName) {
            Vue.prototype[serviceName] = service;
        });
         **/
    }
};

export default ApiServices;
