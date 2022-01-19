import $axiosInst from "@/axios/AxiosInst";
import {adminResponseApi} from "@/util/AdminResponseApi";

export class adminService {

    constructor(host) {
        this.host = host;
    }

    adminLogin(userObj) {
        return $axiosInst
            .post(`${this.host}/admin/login` , userObj)
            .then(response => {
                return response.data.data;
            }).catch(error => {
                throw adminResponseApi(error);
            })
    }

    getDetailAdmin(){
        return $axiosInst
            .post('/admin/detail')
            .then( response => {
                return response.data.data;
            }).catch(( error )=>{
                throw adminResponseApi(error);
            })
    }

}
