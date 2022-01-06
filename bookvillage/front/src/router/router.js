import Vue from 'vue'
import VueRouter from 'vue-router'
import {store} from "@/store";
/* basic */
import ListBooks from "@/views/user/book/ListBooks";
import detailBook from '@/views/user/book/DetailBook'
import Customer from "@/components/customer/Customer";
import Login from "@/views/user/customer/login/CustomerLogin";
/* admin */
import AdminListBooks from "@/views/admin/book/AdminListBooks";
import AdminDetailBook from "@/views/admin/book/AdminDetailBook";
import addBook from '@/views/admin/book/AddBook'
import updateBook from '@/views/admin/book/UpdateBook'
import Admin from "@/components/admin/Admin";
import DetailCustomer from "@/views/user/customer/profile/DetailCustomer";
import ListRentals from "@/views/user/customer/rental/ListRental";
import ListCustomers from "@/views/admin/customer/ListCustomers";
import AddCustomer from "@/views/admin/customer/AddCustomer";
import UpdateCustomer from "@/views/admin/customer/UpdateCustomer";
import AdminListRental from "@/views/admin/rental/AdminListRental";
import PointList from "@/views/user/customer/point/PointList";
import DetailRental from "@/views/user/customer/rental/DetailRental";
import AdminDetailCustomer from "@/views/admin/customer/AdminDetailCustomer";
import AdminLogin from "@/views/admin/login/AdminLogin";

// 1. Vue - VueRouter 연결
Vue.use(VueRouter);

/*
const bookProps = (route) => {
    const props =  {}
    props.query = route.query;
    return props;
}; */


function checkAdminRight(to, from, next){
    const role = store.getters.getRole;
    if(role != 'admin'){
        alert('접근할 수 없습니다.')
        next({path: '/'})
    }
    next();
}

function checkCustomerRight(to, from, next){
    const role = store.getters.getRole;
    if(role != 'customer'){
        alert('로그인 후 이용해주세요.')
        next({path: '/login'})
    }
    next();
}

function alreadyLogin(to, from, next){
    const role = store.getters.getRole;
    if(role == 'admin'){
        alert('이미 관리자 계정에 로그인 된 상태입니다.')
        next({path: '/admin/book/list'})
    } else if(role == 'customer'){
        alert('이미 유저 계정에 로그인 된 상태입니다.')
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
                            path: 'admin/login',
                            component: AdminLogin,
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
                            beforeEnter: checkCustomerRight
                        },
                        {
                            path: 'customer/point/list',
                            component: PointList,
                            beforeEnter: checkCustomerRight
                        },
                        {
                            path: 'customer/rental/list',
                            component: ListRentals,
                            beforeEnter: checkCustomerRight
                        },
                        {
                            path: 'customer/rental/detail/:rentalSeq',
                            component: DetailRental,
                            beforeEnter: checkCustomerRight
                        }
                    ]
                },
                 {
                    path: '/admin',
                    redirect: 'admin/book/list',
                    beforeEnter: checkAdminRight,
                    component: Admin,
                    children :[
                        {
                            path: 'book/list',
                            component: AdminListBooks
                        },
                        {
                            path: 'book/add',
                            component: addBook
                        },
                        {
                            path: 'book/detail/:bookSeq',
                            component: AdminDetailBook
                        },
                        {
                            path: 'book/update/:bookSeq',
                            component: updateBook
                        },
                        {
                            path: 'customer/list',
                            component: ListCustomers
                        },
                        {
                            path: 'customer/add',
                            component: AddCustomer
                        },
                        {
                            path: 'customer/detail/:userSeq',
                            component: AdminDetailCustomer
                        },
                        {
                            path: 'customer/update/:userSeq',
                            component: UpdateCustomer
                        },
                        {
                            path: 'rental/list',
                            component: AdminListRental
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