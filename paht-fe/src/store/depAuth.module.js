import DepAuthService from "../service/DepAuthService";
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const dep = JSON.parse(localStorage.getItem('dep'))

const initialState = dep
    ? { status: { loggedIn: true }, dep }
    : { status: { loggedIn: false }, dep: null };


export const depAuth = {
    namespaced: true,
    state:
        initialState,
    actions: {
        login({ commit }, dep) {
            return DepAuthService.login(dep).then(
                dep => {
                    commit('loginSuccess', dep);
                    return Promise.resolve(dep);
                },

                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            )
        },
        logout({ commit }) {
            DepAuthService.logout();
            commit('logout')
        },
        register({ commit }, dep) {
            return DepAuthService.register(dep).then(
                response => {
                    commit('registerSuccess');
                    return Promise.resolve(response.data)
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error)
                }
            )
        }
    },
    mutations: {
        loginSuccess(state, dep) {
            state.status.loggedIn = true;
            state.dep = dep;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.dep = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.dep = null;
        },
        registerSuccess(state) {
            state.dep = null;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        }
    }
}