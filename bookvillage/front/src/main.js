import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import store from '@/store/index'
import axios from "axios";


Vue.config.productionTip = false
Vue.prototype.$axios = axios
new Vue({
  vuetify,
  render: h => h(App),
  router,
  store,
  axios
}).$mount('#app')
