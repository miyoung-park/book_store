import Axios from 'axios'
import {store} from '@/store/index'

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

export default AxiosInst;