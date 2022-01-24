// import Vue from "vue";
// import axios from 'axios';
// import _ from 'lodash';

import ApiServiceError from "./api-service-error";
// import ApiServiceFactory from "./api-service-factory";
import {ApiServiceErrorEventBus} from './api-service-error'

const ApiServices = {
    install(Vue) {

        /** event emitter **/
        const serviceErrorEventBus = new ApiServiceErrorEventBus();

        /** Default error handler **/
        serviceErrorEventBus.setDefaultErrorHandler(  (e)=>{
            console.log( `my site default handler ${e.toString()}` )
        });

        Vue.prototype.$addApiErrorHandler = function ( errorCode, handler, propagation ) {
            serviceErrorEventBus.on(errorCode, handler, propagation);
        }
        Vue.prototype.$removeApiErrorHandler = function (errorCode, handler) {
            serviceErrorEventBus.off(errorCode,handler);
        }
        Vue.prototype.$removeApiErrorHandler = function ( handler) {
            serviceErrorEventBus.off(handler);
        }
        Vue.config.errorHandler = function(err, vm, info) {
            console.log( vm , info)
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
        }); */
    }
};

export default ApiServices;
