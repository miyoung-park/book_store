import $axiosInst from '@/axios/AxiosInst';
import {adminResponseApi} from "@/util/AdminResponseApi";

export class pointService {
    constructor(host) {
        this.host = host;
    }

    getPointList(userSeq){
        return $axiosInst
            .get(`${this.host}/point/list/` + userSeq)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw adminResponseApi(error);
            })
    }


}