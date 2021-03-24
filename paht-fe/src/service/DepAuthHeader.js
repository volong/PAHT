export default function depAuthHeader() {
    let dep = JSON.parse(localStorage.getItem('dep'));

    if (dep && dep.accessToken) {
        return { Authorization: 'Bearer ' + dep.accessToken }
    } else {
        return {};
    }
}