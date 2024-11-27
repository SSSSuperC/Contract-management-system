package com.contract_management.contract.mapper;


import com.contract_management.contract.entity.Article;
import com.contract_management.contract.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time,party_a,party_b,location) " +
            "values(#{title},#{content},#{cover_img},#{state},#{category_id},#{create_user},#{create_time},#{update_time},#{party_a},#{party_b},#{location})")
    void add(Article article);

    //查询我的合同
    List<Article> list(Integer user_id, Integer category_id, String state,String title,String location);

    //查询所有合同
    List<Article> allList( Integer category_id, String state,String title,String location);

    //查询笔记
    List<Article> noteList(Integer user_id);

    //查询
    List<Article> Example(Integer user_id);

    //查询
    List<Article> Detail(Integer id);

    //查询笔记
    List<Article> History(Integer user_id);

    //根据id删除
    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);

    //更新
    @Update("update article set title=#{title},content=#{content},cover_img=#{cover_img},state=#{state},category_id=#{category_id},party_a=#{party_a},party_b=#{party_b},location=#{location},update_time=#{update_time} where id=#{id}")
    void update(Article article);

    //更新
    @Update("update article set note=#{note} where id=#{id}")
    void updateNote(Article article);

    //更新
    @Update("update article set checked = TRUE where id=#{id}")
    void Visit(Article article);

    //更新
    @Update("update article set checked = FALSE where create_user=#{id}")
    void Clear(Integer id);

    //更新
    @Update("update article set note= null where create_user=#{create_user}")
    void Delete(Integer create_user);
}
