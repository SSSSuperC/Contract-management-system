package com.contract_management.contract.controller;


import com.contract_management.contract.entity.Article;
import com.contract_management.contract.entity.Category;
import com.contract_management.contract.entity.PageBean;
import com.contract_management.contract.entity.Result;
import com.contract_management.contract.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    //查找我的合同
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location
    ) {
       PageBean<Article> pb =  articleService.list(pageNum,pageSize,category_id,state,title,location);
       return Result.success(pb);
    }

    //查找全部合同
    @GetMapping("/all")
    public Result<PageBean<Article>> allList(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location
    ) {
        PageBean<Article> pb =  articleService.allList(pageNum,pageSize,category_id,state,title,location);
        return Result.success(pb);
    }

    //示例合同
    @GetMapping("/example")
    public Result<List<Article>> Example() {
        List<Article> pb =  articleService.Example();
        return Result.success(pb);
    }

    //合同细节
    @GetMapping("/detail")
    public Result<List<Article>> Detail(Integer id) {
        List<Article> pb =  articleService.Detail(id);
        return Result.success(pb);
    }

    //删除合同
    @DeleteMapping
    public Result delete(Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

    //更新合同
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    //更新笔记
    @PutMapping("/note")
    public Result updateNote(@RequestBody Article article){
        articleService.updateNote(article);
        return Result.success();
    }

    //查找我的笔记
    @GetMapping("/findnote")
    public Result<PageBean<Article>> noteList(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.noteList(pageNum,pageSize);
        return Result.success(pb);
    }

    //查找我的笔记
    @GetMapping("/history")
    public Result<PageBean<Article>> History(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.History(pageNum,pageSize);
        return Result.success(pb);
    }

    //访问
    @PutMapping("/visit")
    public Result Visit(@RequestBody Article article){
        articleService.Visit(article);
        return Result.success();
    }

    //清除访问记录
    @PutMapping("/clear")
    public Result Clear(){
        articleService.Clear();
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result Delete(){
        articleService.Delete();
        return Result.success();
    }

}
