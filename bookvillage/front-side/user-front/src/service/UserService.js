import $axiosInst from '@/axios/AxiosInst'

export class UserService {

    constructor(host) {
        this.host = host;
    }

    loginUser(userObj){
        return $axiosInst
            .post(`${this.host}/user/login` , userObj)
            .then( response => {
                return response.data;
            })
    }

    async getUserDetailById() {
        return await $axiosInst
            .post(`${this.host}/user/detail` )
            .then(response => {
                return response.data;
            })
    }



    }