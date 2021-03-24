import axios from 'axios';
import API_URL from "../common/config";

class AuthService {
    login(user) {
        return axios.post(API_URL + '/signin/user', {
            username: user.username,
            password: user.password
        })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            })
    }

    logout() {
        localStorage.removeItem('user')
    }

    register(user) {
        return axios.post(API_URL + '/user/signup',
            {
                fullname: user.fullname,
                avatar: user.avatar,
                username: user.username,
                email: user.email,
                password: user.password,
                is_enable: 0,
            })
    }
}

export default new AuthService();