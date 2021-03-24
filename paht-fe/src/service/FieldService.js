import BaseService from "./baseService";

class FieldService {
    findAll(is_delete) {
        return BaseService.get(`fields/${is_delete}`)
    }

    findById(id) {
        return BaseService.getById("field", id)
    }

    create(params) {
        return BaseService.post("field", params)
    }

    update(data) {
        return BaseService.put("field", data)
    }

    remove(id) {
        return BaseService.delete(`field`, id)
    }

    restore(id) {
        return BaseService.restore("field/restore", id)
    }
}

export default new FieldService();
