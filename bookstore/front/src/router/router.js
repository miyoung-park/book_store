import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from "@/views/Main";
import Login from "@/views/common/login/Login";

// 1. Vue - VueRouter 연결
Vue.use(VueRouter);

// 2. routes 정의
const routes = [
                    {
                        path: '/',   // url 경로
                        component : Main,// 매핑하고 싶은 컴포넌트(import필요!)
                    },
                    {
                        path: '/login',
                        component: Login
                    }
                ]

// 3. router 인스턴스 생성
const router = new VueRouter({
    mode: 'history',
    routes
})

// 4. export
export default router;