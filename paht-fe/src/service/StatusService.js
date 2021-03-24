import BaseService from "./baseService";


class StatusService {

    findAll() {
        return BaseService.get("statuses")
    }

}

export default new StatusService();