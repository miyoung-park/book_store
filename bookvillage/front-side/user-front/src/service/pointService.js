import $axiosInst from  '@/axios/AxiosInst';
import {responseApi} from "@/util/responseApi";

export class pointService {
    constructor(host) {
        this.host = host;
    }

    getPointList(){
        return $axiosInst
            .get(`${this.host}/point/list`)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw responseApi(error);
            })
    }

    chargePoint(pointVO){
        return $axiosInst
            .post(`${this.host}/point/charge` , pointVO)
            .then( response => {
                return response.data.data;
            }).catch( error => {
                throw responseApi(error);
            })
    }


}