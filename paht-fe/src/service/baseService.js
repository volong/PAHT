import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import API_URL from "@/common/config";


const BaseService = {

    get(resource) {
        return axios.get(`${API_URL}/${resource}`)

    },

    getById(resource, id) {
        return axios.get(`${API_URL}/${resource}/${id}`)
    },

    post(resource, params) {
        return axios.post(`${API_URL}/${resource}`, params);
    },

    put(resource, formData) {
        return axios.put(`${API_URL}/${resource}`, formData);
    },

    delete(resource, id) {
        return axios.delete(`${API_URL}/${resource}/${id}`)
    },

    restore(resource, id) {
        return axios.put(`${API_URL}/${resource}/${id}`);
    },

    postFormData(resource, formData) {
        return axios.post(`${API_URL}/${resource}`, formData);

    }
}

export default BaseService;