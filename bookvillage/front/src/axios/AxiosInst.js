import Axios from 'axios'
// import {store} from '@/store/index'

const AxiosInst = Axios.create({
    baseURL: 'http://localhost:8080'
});

AxiosInst.interceptors.response.use(
    // token

)

export default AxiosInst;