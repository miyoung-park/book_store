import createPersistedState from 'vuex-persistedstate';
import Vuex from "vuex";
import Vue from 'vue';
import router from "@/router/router";

Vue.use(Vuex);

export const store = new Vuex.Store({
    plugins: [
        createPersistedState()
    ],
    state: {
        token: null,
        userId: null,
        userName: null
    },
    getters: {
        getToken(state){
            return state.token;
        },
        getUserId(state){
            return state.userId;
        },
        getUserName(state){
            return state.userName;
        }
    },
    mutations: {  // commit 으로 부를 수 있다.
        setPayloads(state, payloads){
            state.token = payloads.token;
            state.userId = payloads.userId;
            state.userName = payloads.userName;
            alert('로그인 되었습니다.');
        },
        logout(state){
            state.token = null;
            state.userId = null;
            state.userName = null;
            alert('로그아웃 되었습니다. \n다시 로그인 해주세요.');
            router.push({
                path: '/login'
            });
        }
    },
    actions: { // dispatch 로 부를 수 있다.
        setToken: (context, payloads ) => {
            context.commit('setPayloads', payloads);
        },
        logout:(context) => {
            context.commit('logout');
        }
    }
})

