import $axiosInst from  '@/axios/AxiosInst';

export class PointService {
    constructor(host) {
        this.host = host;
    }

    getPointList(){
        return $axiosInst
            .get(`${this.host}/point/list`)
            .then( response => {
                return response.data;
            })
    }

    chargePoint(pointVO){
        return $axiosInst
            .post(`${this.host}/point/charge` , pointVO)
            .then( response => {
                return response.data;
            })
    }


}