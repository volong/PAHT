import axios from 'axios';
import API_URL from "../common/config";

class DepAuthService {
    login(dep) {
        return axios.post(API_URL + '/signin/dep', {
            username: dep.username,
            password: dep.password
        })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('dep', JSON.stringify(response.data));
                }
                return response.data;
            })
    }

    logout() {
        localStorage.removeItem('dep')
    }

    register(dep) {
        return axios.post(API_URL + '/dep/signup',
            {
                fullname: dep.fullname,
                avatar: dep.url,
                username: dep.username,
                email: dep.email,
                password: dep.password,
                is_enable: 0,
            })
    }
}

export default new DepAuthService();