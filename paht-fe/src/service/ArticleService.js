import BaseService from "./baseService";

class ArticleService {
    findNewArticlesASC(status_id, pageSize, pageIndex) {
        return BaseService.get(`articles/asc/${status_id}/${pageSize}/${pageIndex}`)
    };

    findNewArticlesDESC(status_id, pageSize, pageIndex) {
        return BaseService.get(`articles/desc/${status_id}/${pageSize}/${pageIndex}`)
    };

    findAllChecked(pageIndex) {
        return BaseService.get(`article/checked/${pageIndex}`)
    }

    findByStatus(status_id) {
        return BaseService.get(`find_articles_by_status/${status_id}`)
    };

    findByDep(dep_id, status_id) {
        return BaseService.get(`find_articles_by_dep/${dep_id}/${status_id}`)
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

    create(article) {
        return BaseService.post("article", article)
    }

    update(formData) {
        return BaseService.put("article", formData)
    }

    search(status_id, keyword) {
        return BaseService.get(`search/${status_id}/${keyword}`)
    }


}
export default new ArticleService();