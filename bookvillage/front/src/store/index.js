import createPersistedState from 'vuex-persistedstate';
import Vuex from "vuex";
import Vue from 'vue';
import $router from '@/router/router'

Vue.use(Vuex);

// store 생성
export const store = new Vuex.Store({
    plugins: [
        createPersistedState()
    ],
    state: {
        role: null,
        token: null,
    },
    getters: {
        getToken(state){
            return state.token;
        },
        getRole(state){
            return state.role;
        }
    },
    mutations: {  // commit 으로 부를 수 있다.
        setToken(state, payloads){
            state.token = payloads.token;
            state.role = payloads.role;
            alert('로그인 되었습니다.')
        },
        logout(state){
            if(state.token != null){
                state.token = null;
                state.role = null;
            }
            alert('로그아웃 되었습니다.');
            $router.push({
                path: '/login'
            }).catch((e)=>{console.log(e)})
        }
    },
    actions: { // dispatch 로 부를 수 있다.
        setToken: (context, payloads ) => {
            context.commit('setToken', payloads);
        },
        logout:(context) => {
            context.commit('logout');
        }
    }
})

