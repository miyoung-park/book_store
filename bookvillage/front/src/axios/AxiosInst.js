import Axios from 'axios'
import {store} from '@/store/index'

const AxiosInst = Axios.create({
    baseURL: 'http://localhost:8080'
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