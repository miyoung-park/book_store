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


}