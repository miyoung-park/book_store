import $axiosInst from  '@/axios/AxiosInst';

export class pointService {

    getPointList(){
        return $axiosInst
            .get('/point/list')
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

    chargePoint(pointVO){
        return $axiosInst
            .post('/point/charge' , pointVO)
            .then( response => {
                return response;
            }).catch( error => {
               return error;
            })
    }


}