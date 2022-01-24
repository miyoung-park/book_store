import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store/index'
import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { AdminService } from '@/service/AdminService'
import { CustomerService } from "@/service/CustomerService";
import { BookService } from "@/service/BookService"
import { PointService} from "@/service/PointService";
import { RentalService} from "@/service/RentalService";
import ApiServices from "@/plugins/api-service-plugin";

const API_URL_HOST = process.env.VUE_APP_API_HOST;

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$axiosInst = axiosInst // TODO: prototype 의 역할에 대해서 공부하기
Vue.use(ApiServices , { host:API_URL_HOST})

Vue.mixin({
  beforeDestroy() {
    if( this.$apiErrorHandler ){
      this.$removeApiErrorHandler( this.$apiErrorHandler );
    }
  }
})

new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  provide: {
    'adminService': new AdminService(API_URL_HOST), // TODO: Java Class 처럼 JavaScript Class 도 파스칼 케이스로 (완)
    'bookService' : new BookService(API_URL_HOST),
    'customerService' : new CustomerService(API_URL_HOST),
    'pointService' : new PointService(API_URL_HOST),
    'rentalService' : new RentalService(API_URL_HOST)
  },
}).$mount('#app')