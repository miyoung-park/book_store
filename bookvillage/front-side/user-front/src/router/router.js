import Vue from 'vue'
import VueRouter from 'vue-router'
import {store} from "@/store";
import ListBooks from "@/views/user/book/ListBooks";
import detailBook from '@/views/user/book/DetailBook'
import Customer from "@/components/customerComponent/Customer";
import Login from "@/views/user/user/Login";
import DetailCustomer from "@/views/user/user/DetailCustomer";
import ListRentals from "@/views/user/rental/ListRental";
import PointList from "@/views/user/point/PointList";
import DetailRental from "@/views/user/rental/DetailRental";


// 1. Vue - VueRouter 연결
Vue.use(VueRouter);


function RequiredLogin(to, from, next){
    const token = store.getters.getToken;
    if(token == null){
        alert('로그인 후 이용해주세요.')
        next({path: '/login'})
    }
    next();
}

function alreadyLogin(to, from, next){
    const token = store.getters.getToken;
    if( token != null){
        alert('이미  로그인 된 상태입니다.')
        next({path: '/book/list'})
    }
    next();
}

const routes = [
                {
                    path: '/',
                    redirect: 'book/list',
                    component: Customer,
                    children: [
                        {
                            path: 'login',
                            component: Login,
                            beforeEnter: alreadyLogin
                        },
                        {
                            path: 'book/list',
                            component: ListBooks
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: detailBook
                        },
                        {
                            path: 'customer/detail/',
                            component: DetailCustomer,
                            beforeEnter: RequiredLogin
                        },
                        {
                            path: 'customer/point/list',
                            component: PointList,
                            beforeEnter: RequiredLogin
                        },
                        {
                            path: 'customer/rental/list',
                            component: ListRentals,
                            beforeEnter: RequiredLogin
                        },
                        {
                            path: 'customer/rental/detail/:rentalSeq',
                            component: DetailRental,
                            beforeEnter: RequiredLogin
                        }
                    ]
                },
                ]

// 3. router 인스턴스 생성
const router = new VueRouter({
    mode: 'history',
    routes
})

// 4. export
export default router;