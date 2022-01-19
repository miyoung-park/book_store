import $axiosInst from '@/axios/AxiosInst'
import {adminResponseApi} from "@/util/AdminResponseApi";

export class customerService {

    constructor(host) {
        this.host = host;
    }

    getCustomerList(){
        return $axiosInst
            .get(`${this.host}/customer/list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }


    addCustomer(customerObj){
        return $axiosInst
            .post(`${this.host}/customer/add` , customerObj)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    getCustomerDetail(userSeq) {
        return $axiosInst
            .get(`${this.host}/customer/detail/` + userSeq)
            .then(response => {
                return response.data.data;
            }).catch(error => {
                throw adminResponseApi(error);
            })
    }

    updateCustomer(customerObj) {
        const userSeq = customerObj.userSeq
        return $axiosInst
            .put(`${this.host}/customer/update/` + userSeq , customerObj)
            .then( () => {
                return;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }

    deleteCustomer(userSeq){
        return $axiosInst
            .delete(`${this.host}/customer/delete/` + userSeq)
            .then( () => {
                return;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }



}