package com.contract_management.contract.service;


import com.contract_management.contract.entity.Article;
import com.contract_management.contract.entity.PageBean;

import java.util.List;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state,String title,String location);

    //查询示例合同
    List<Article> Example();

    //删除文章
    void deleteById(Integer id);

    //更新合同
    void update(Article article);

    //查询合同细节
    List<Article> Detail(Integer id);

    //更新笔记
    void updateNote(Article article);

    PageBean<Article> allList(Integer pageNum, Integer pageSize, Integer category_id, String state,String title,String location);

    PageBean<Article> noteList(Integer pageNum, Integer pageSize);

    PageBean<Article> History(Integer pageNum, Integer pageSize);

    void Clear();

    void Visit(Article article);

    void Delete();
}
