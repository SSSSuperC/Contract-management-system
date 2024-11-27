import request from '@/utils/request.js'
import { useTokenStore } from '@/stores/token.js'

export const logListService = ()=>{
    //const tokenStore = useTokenStore();
    //在pinia中定义的响应式数据,都不需要.value
    //return request.get('/category',{headers:{'Authorization':tokenStore.token}})
    return request.get('/log')
}

export const logDeleteService = ()=>{
    return request.delete('/log')
}

//所有文章列表查询
export const insertLogService = (params)=>{
    console.log(params);
   return  request.get('/log/insert',{params:params})
}