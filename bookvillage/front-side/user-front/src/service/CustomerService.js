import $axiosInst from '@/axios/AxiosInst'

export class CustomerService {

    constructor(host) {
        this.host = host;
    }

    loginCustomer(customerObj){
        return $axiosInst
            .post(`${this.host}/user/login` , customerObj)
            .then( response => {
                return response.data;
            })
    }

    getCustomerDetailById() {
        return $axiosInst
            .post(`${this.host}/user/detail` )
            .then(response => {
                return response.data;
            })
    }



    }