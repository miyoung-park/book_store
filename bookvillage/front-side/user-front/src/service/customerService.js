import $axiosInst from '@/axios/AxiosInst'
import {responseApi} from "@/util/responseApi";

export class customerService {

    constructor(host) {
        this.host = host;
    }

    loginCustomer(customerObj){
        return $axiosInst
            .post(`${this.host}/customer/login` , customerObj)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw responseApi(error);
            })
    }

    getCustomerDetailById() {
        return $axiosInst
            .post(`${this.host}/customer/detail` )
            .then(response => {
                return response.data.data;
            }).catch(error => {
                throw responseApi(error);
            })
    }



    }