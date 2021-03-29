import BaseService from "./baseService";

class ArticleService {
    findNewArticlesASC(status_id, pageSize, pageIndex) {
        return BaseService.get(`articles/asc/${status_id}/${pageSize}/${pageIndex}`)
    };

    findAllChecked(pageIndex) {
        return BaseService.get(`article/checked/${pageIndex}`)
    }

    findArticlesIsDeleted(pageIndex, pageSize) {
        return BaseService.get(`article/isdeleted/${pageIndex}/${pageSize}`)
    }

    findByStatus(status_id) {
        return BaseService.get(`find_articles_by_status/${status_id}`)
    };

    findByDep(status_id, dep_id, pageSize, pageIndex) {
        return BaseService.get(`articles/bydep/${status_id}/${dep_id}/${pageSize}/${pageIndex}`)
    }

    findByDate(dateofpost) {
        return BaseService.get(`find_article_by_date/${dateofpost}`)
    }

    findById(article_id) {
        return BaseService.get(`article/${article_id}`)
    }

    getSumArticles(status_id) {
        return BaseService.get(`sum/articles/${status_id}`)
    }

    getSumArticlesForDep(dep_id, status_id) {
        return BaseService.get(`sum/articles/fordep/${dep_id}/${status_id}`)
    }

    create(article) {
        return BaseService.post("article", article)
    }

    update(formData) {
        return BaseService.put("article", formData)
    }

    search(status_id, keyword) {
        return BaseService.get(`search/${status_id}/${keyword}`)
    }

    remove(id) {
        return BaseService.delete(`article`, id)
    }

    restore(id) {
        return BaseService.restore("article/restore", id)
    }


}
export default new ArticleService();