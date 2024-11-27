import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js'
//文章分类列表查询
export const articleCategoryListService = ()=>{
    //const tokenStore = useTokenStore();
    //在pinia中定义的响应式数据,都不需要.value
    //return request.get('/category',{headers:{'Authorization':tokenStore.token}})
    return request.get('/category')
}

//文章分类添加
export const articleCategoryAddService = (categoryData)=>{
    return request.post('/category',categoryData)
}

//文章分类修改
export const articleCategoryUpdateService = (categoryData)=>{
   return  request.put('/category',categoryData)
}


//文章修改
export const articleUpdateService = (articleData)=>{
   return  request.put('/article',articleData)
}

//文章分类删除
export const articleCategoryDeleteService = (id)=>{
    return request.delete('/category?id='+id)
}

//我的文章列表查询
export const articleListService = (params)=>{
   return  request.get('/article',{params:params})
}

//访问历史列表查询
export const historyListService = (params)=>{
   return  request.get('/article/history',{params:params})
}

//所有文章列表查询
export const allArticleListService = (params)=>{
   return  request.get('/article/all',{params:params})
}

//文章添加
export const articleAddService = (articleData)=>{
    return request.post('/article',articleData);

}

//访问文章
export const visitService = (articleData)=>{
   return  request.put('/article/visit',articleData)
}

//文章删除
export const articleDeleteService = (id)=>{
    return request.delete('/article?id='+id)
}

//示例合同查询
export const ExampleListService = ()=>{
    return request.get('/article/example')
}

//查询文章细节
export const articleDetailService = (id)=>{

    return request.get('/article/detail?id=' + id)
}

//更新笔记
export const noteUpdateService = (articleData)=>{
   return  request.put('/article/note',articleData)
}

//查找笔记
//所有文章列表查询
export const NoteListService = (params)=>{
   return  request.get('/article/findnote',{params:params})
}

//清空历史记录
export const clearService = ()=>{
    return request.put('/article/clear');
}

//清空笔记
//文章删除
export const noteClearService = ()=>{
    return request.delete('/article/delete')
}