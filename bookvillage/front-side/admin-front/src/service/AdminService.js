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
            })
    }

}
