import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store/index'
// import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { AdminService } from '@/service/AdminService'
import { UserService } from "@/service/UserService";
import { BookService } from "@/service/BookService"
import { PointService} from "@/service/PointService";
import { RentalService} from "@/service/RentalService";
import ApiServices from "@/plugins/api-service-plugin";
import ErrorCode from "@/service/enum/EnumErrorCode";

const API_URL_HOST = process.env.VUE_APP_API_HOST;

Vue.config.productionTip = false
// TODO: prototype 의 역할에 대해서 공부하기 (완)
// 많은 컴포넘트에서 사용하고 싶지만 global scope 에 의해 오염되는 것은 원치 않을 때 prototype 에서 정의하면 Vue instance 에서 사용 할 수 있다.
Vue.prototype.$axiosInst = axiosInst
Vue.prototype.$errorCode = ErrorCode // ErrorCode Class
Vue.use(ApiServices , { host:API_URL_HOST}) // 플러그인 추가

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
    'userService' : new UserService(API_URL_HOST),
    'pointService' : new PointService(API_URL_HOST),
    'rentalService' : new RentalService(API_URL_HOST)
  },
}).$mount('#app')