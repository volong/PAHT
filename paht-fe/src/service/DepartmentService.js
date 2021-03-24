import BaseService from "./baseService";

class DepartmentService {
    findAll() {
        return BaseService.get(`dep`)
    }

    findById(id) {
        return BaseService.getById("dep", id)
    }

    create(params) {
        return BaseService.post("dep", params)
    }

    update(params) {
        return BaseService.put("dep", params)
    }

    remove(id) {
        return BaseService.delete("delete_dep", id)
    }

    restore(id) {
        return BaseService.restore("restore_dep", id)
    }
}

export default new DepartmentService();