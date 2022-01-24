import $axiosInst from "@/axios/AxiosInst";

export class AdminService {

    constructor(host) {
        this.host = host;
    }

    adminLogin(userObj) {
        return $axiosInst
            .post(`${this.host}/admin/login` , userObj)
            .then(response => {
                return response.data;
            }).catch(error => {
                console.log(error);
            })
    }

    getDetailAdmin(){
        return $axiosInst
            .post('/admin/detail')
            .then( response => {
                return response.data;
            }).catch(( error )=>{
                alert( error.errorMessage + ' 다시 로그인 해주세요.');
                this.$store.commit('logout');
            })
    }

}
