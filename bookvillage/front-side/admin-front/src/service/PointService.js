import $axiosInst from '@/axios/AxiosInst';

export class PointService {
    constructor(host) {
        this.host = host;
    }

    getPointList(userSeq){
        return $axiosInst
            .get(`${this.host}/point/list/` + userSeq)
            .then( response => {
                return response.data;
            })
    }


}