import Axios from 'axios'
import {store} from '@/store/index'
import ApiServiceError from "@/plugins/api-service-plugin/api-service-error";

const API_URL_HOST = process.env.VUE_APP_API_HOST;

const AxiosInst = Axios.create({
    baseURL: API_URL_HOST
});

AxiosInst.interceptors.request.use(
    // token
    (config) => {
        let access_token = store.getters.getToken;
        if(access_token){
            config.headers.Authorization = `${access_token}`;
        }
        return config;
    }

)

AxiosInst.interceptors.response.use(function (response) {
    return response;
},  (error) => {
    if(error.response){
        return Promise.reject(new ApiServiceError( error.response.data ));  // { errorCode: '', errorMessage: '' } 형태
    }else{
        return Promise.reject( new ApiServiceError( {errorCode:'NETWORK_ERROR', errorMessage:'Network Error'} ) );
    }                           // 404 같은 뜬금없는 행위 다른 홈페이지나 다른 url 탔을 때 정보 없음이 뜬다 ( payloads 가 없다 ! )
});

export default AxiosInst;