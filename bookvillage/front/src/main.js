import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import { store } from '@/store/index'
import axios from 'axios'
import axiosInst from '@/axios/AxiosInst'
import { adminService } from '@/service/adminService'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$axiosInst = axiosInst
new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  provide: {
    'adminService': new adminService
  },
}).$mount('#app')
