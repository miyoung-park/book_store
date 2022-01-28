import $axiosInst from '@/axios/AxiosInst'

export class UserService {

    constructor(host) {
        this.host = host;
    }

    getUserList(){
        return $axiosInst
            .get(`${this.host}/user/list`)
            .then( response => {
                return response.data;
            })
    }


    addUser(userObj){
        return $axiosInst
            .post(`${this.host}/user/add` , userObj)
            .then( response => {
                return response.data;
            })
    }

    getUserDetail(userSeq) {
        return $axiosInst
            .get(`${this.host}/user/detail/` + userSeq)
            .then(response => {
                return response.data;
            })
    }

    updateUser(userObj) {
        const userSeq = userObj.userSeq
        return $axiosInst
            .put(`${this.host}/user/update/` + userSeq , userObj)
            .then( () => {
                return;
            })
    }

    deleteUser(userSeq){
        return $axiosInst
            .delete(`${this.host}/user/delete/` + userSeq)
            .then( () => {
                return;
            })
    }



}