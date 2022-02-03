import _ from "lodash";

export default class ApiServiceError {
    constructor( {errorCode, errorMessage} ) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    toString(){
        return `errorCode:${this.errorCode}, errorMessage:${this.errorMessage}`;
    }
}


class ServiceErrorEventHandlerWrapper {
    /**
     *
     * @param serviceErrorEventHandler
     * @param propagation false 라면, 이벤트 핸들러 실행 후 stack 에서 중단.
     */
    constructor(serviceErrorEventHandler, propagation = true){
        this.handler = serviceErrorEventHandler;
        this.propagation = propagation;
    }

    handleError(error){
        try {
            this.handler(error);
        }catch (e) {
            console.warn(e)
        }
        return this.propagation;
    }
}

/**
 * API 에러 이벤트 버스
 */
export class ApiServiceErrorEventBus{

    constructor( ) {
        this.listenerGroup = {}

        this.defaultHandler = new ServiceErrorEventHandlerWrapper( (apiServiceError)=>{
            console.log( `Default handler ${apiServiceError.toString()}` )
        });
    }

    /**
     * 기본 에러 핸들러 변경
     * @param handler
     */
    setDefaultErrorHandler(handler){
        this.defaultHandler = new ServiceErrorEventHandlerWrapper( handler );
    }

    /**
     * 핸들러 등록
     * @param errorCode
     * @param handler
     */
    on(errorCode, handler, propagation){
        const listeners = this._getListeners(errorCode);
        listeners.push( new ServiceErrorEventHandlerWrapper(handler, propagation)  );
    }

    /**
     * 지정 핸들러 삭제
     * @param errorCode
     * @param handler
     */
    off(errorCode, handler){

        if( errorCode instanceof Function){

            handler = errorCode;
            //해당 handler 로 등록된 모든 구독 취소.
            _.forEach(this.listenerGroup, function(listeners, errorCode) {
                _.remove( listeners, (listenerWrapper)=>{
                    return listenerWrapper.handler === handler;
                })
            });

        }else{
            //
            const listeners = this._getListeners(errorCode);
            _.remove( listeners, (listenerWrapper)=>{
                return listenerWrapper.handler === handler;
            })
        }

    }

    /**
     * 모든 핸들러 삭제
     * @param errorCode
     */
    offAll(errorCode){
        if( this.listenerGroup[errorCode] && this.listenerGroup[errorCode].length > 0 ){
            this.listenerGroup[errorCode].splice(0, this.listenerGroup[errorCode].length);
        }
    }

    /**
     * Emit.
     * @param errorCode
     * @param error
     */
    emit(errorCode, error){
        const listeners = this._getListeners(errorCode);
        _.forEachRight(listeners, function(handlerWrapper) {
            return handlerWrapper.handleError(error);
        });
    }

    _getListeners(errorCode ){
        if( this.listenerGroup[errorCode] ){
        }else{
            this.listenerGroup[errorCode] = [];
            this.listenerGroup[errorCode].push(this.defaultHandler);
        }
        return this.listenerGroup[errorCode];
    }

}
