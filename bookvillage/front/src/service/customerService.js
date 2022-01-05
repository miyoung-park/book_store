import $axiosInst from '@/axios/AxiosInst'

export class customerService {


    loginCustomer(customerObj){
        return $axiosInst
            .post('/customer/login' , customerObj)
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

    addCustomer(customerObj){
        return $axiosInst
            .post('/customer/add' , customerObj)
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }


    getCustomerList(){
        return $axiosInst
            .get('/customer/list')
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }


    getCustomerDetail(userSeq) {
        return $axiosInst
            .get('/customer/detail/' + userSeq)
            .then(response => {
                return response.data;
            }).catch(error => {
                return error;
            })
    }

    getCustomerDetailById() {
        return $axiosInst
            .post('/customer/detail' )
            .then(response => {
                return response.data;
            }).catch(error => {
                return error;
            })
    }


    updateCustomer(customerObj) {
        return $axiosInst
            .put('/customer/update/' , customerObj)
            .then( response => {
                return response.data;
            }).catch( error => {
                return error;
            })
    }

    deleteCustomer(userSeq){
         return $axiosInst
             .delete('/customer/delete/' + userSeq)
             .then( response => {
                 return response;
             }).catch( error => {
                 return error;
             })
    }



    }