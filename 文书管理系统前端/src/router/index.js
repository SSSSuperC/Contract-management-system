import { createRouter, createWebHistory } from 'vue-router'

//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import Index from '@/views/index.vue'
import ArticleCategoryVue from '@/views/article/ArticleCategory.vue'
import ArticleManageVue from '@/views/article/ArticleManage.vue'
import Article from "../views/article/Article.vue";
import UserAvatarVue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'
import AllArticleManageVue from '@/views/article/AllArticleManage.vue'
import MyNote from '../views/article/MyNote.vue'
import History from '../views/article/History.vue'
import Log from '../views/article/Log.vue'

//定义路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/', component: LayoutVue,redirect:'/index', children: [
            { path: '/index', component: Index },
            { path: '/article/category', component: ArticleCategoryVue },
            { path: '/article/manage', component: ArticleManageVue,name:'article_manage' },
            { path: '/user/info', component: UserInfoVue },
            { path: '/user/avatar', component: UserAvatarVue },
            { path: '/user/resetPassword', component: UserResetPasswordVue },
            { path: '/article/detail', component: Article,name:'article' },
            { path: '/article/all', component: AllArticleManageVue,name:'all_article_manage'},
            { path: '/article/note', component: MyNote,name:'note_manage'},
            { path: '/article/history', component: History,name:'history'},
            { path: '/article/log',component: Log,name:"log"}
        ]
    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
