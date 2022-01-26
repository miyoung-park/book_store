import createPersistedState from 'vuex-persistedstate';
import Vuex from "vuex";
import Vue from 'vue';

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
            alert('로그아웃 되었습니다.');
        }
    }, // TODO : 서버에서 받아온 데이터를 조작하는 경우 actions 를 이용, 프론트에서만 조작하는 경우는 Mutation 이용!(완)
    actions: { // dispatch 로 부를 수 있다.
        setToken: (context, payloads ) => {
            context.commit('setToken', payloads);
        },
        logout:(context) => {
            context.commit('logout');
        }
    }
})

