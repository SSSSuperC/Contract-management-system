<script setup>
import {
    Edit,
    Delete,
    Notebook,
    Reading,
    View,
    Memo
} from '@element-plus/icons-vue'

import { ref } from 'vue'

//契约分类数据模型
const categorys = ref([
    {
        "id": 3,
        "category_name": "美食",
        "category_alias": "my",
        "create_time": "2023-09-02 12:06:59",
        "update_time": "2023-09-02 12:06:59"
    },
    {
        "id": 4,
        "category_name": "娱乐",
        "category_alias": "yl",
        "create_time": "2023-09-02 12:08:16",
        "update_time": "2023-09-02 12:08:16"
    },
    {
        "id": 5,
        "category_name": "军事",
        "category_alias": "js",
        "create_time": "2023-09-02 12:08:33",
        "update_time": "2023-09-02 12:08:33"
    }
]
)

//用户搜索时选中的分类id
const category_id = ref('')

//用户搜索时选中的发布状态
const state = ref('')

//契约列表数据模型
const articles = ref([
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "cover_img": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "category_id": 2,
        "create_time": "2023-09-03 11:55:30",
        "update_time": "2023-09-03 11:55:30"
    },
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "cover_img": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "category_id": 2,
        "create_time": "2023-09-03 11:55:30",
        "update_time": "2023-09-03 11:55:30"
    },
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "cover_img": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "category_id": 2,
        "create_time": "2023-09-03 11:55:30",
        "update_time": "2023-09-03 11:55:30"
    },
]

)

//添加文章表单校验
const rules = {
    title: [
        { required: true, message: '请输入名称', trigger: 'blur' },
        { min: 1, max: 10, message: '长度为1~10位非空字符', trigger: 'blur' }
    ],
    content: [
        { required: true, message: '请输入内容', trigger: 'blur' },
    ],
    cover_img: [
        { required: true, message: '请上传图片', trigger: 'blur' },
    ],
    state:[
      { required: true, message: '请选择状态', trigger: 'blur' },
    ],
    category_id:[
      { required: true, message: '请选择分类', trigger: 'blur' },
    ]
}

//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数

//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    NoteList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    NoteList()
}


//回显契约分类
import { articleCategoryListService, articleListService,articleAddService,articleDeleteService,articleUpdateService,NoteListService,noteClearService} from '@/api/article.js'
const articleCategoryList = async () => {
    let result = await articleCategoryListService();

    categorys.value = result.data;

}



//获取契约列表数据
const NoteList = async () => {
    let params = {
        pageNum: pageNum.value,
        pageSize: pageSize.value,
    }
    let result = await NoteListService(params);

    //渲染视图
    total.value = result.data.total;
    articles.value = result.data.items;

    //处理数据,给数据模型扩展一个属性category_name,分类名称
    for (let i = 0; i < articles.value.length; i++) {
        let article = articles.value[i];
        for (let j = 0; j < categorys.value.length; j++) {
            if (article.category_id == categorys.value[j].id) {
                article.category_name = categorys.value[j].category_name;
            }
        }
    }

}



articleCategoryList()
NoteList();

import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { Plus } from '@element-plus/icons-vue'
//控制抽屉是否显示
const title = ref('')
const visibleDrawer = ref(false)
//添加表单数据模型
const articleModel = ref({
    title: '',
    category_id: '',
    cover_img: '',
    content: '',
    state: '',
    party_a:'',
    party_b:'',
    location:''
})

//更新article


//导入token
import { useTokenStore } from '@/stores/token.js';
const tokenStore = useTokenStore();

//上传成功的回调函数
const uploadSuccess = (result)=>{
    articleModel.value.cover_img = result.data;
    console.log(result.data);
}


//添加契约
import {ElMessage, ElMessageBox} from 'element-plus'

//清空笔记
const noteClear = async () =>{
  noteClearService();
  await NoteList();
}

import router from "@/router/index.js";
const  jump = async(id) => {
      const params={id}
      router.push({name:'article',state: { params }})
    }

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>我的笔记</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer = true;title = '添加笔记'; ">添加笔记</el-button>
                    <el-button type="primary" @click="noteClear()">清空记录</el-button>
                </div>
            </div>
        </template>

        <!-- 契约列表 -->
        <el-table :data="articles" style="width: 100%">
            <el-table-column label="契约标题"  prop="title"></el-table-column>
            <el-table-column label="分类" prop="category_name"></el-table-column>
            <el-table-column label="发表时间" prop="create_time"> </el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="甲方" prop="party_a"></el-table-column>
            <el-table-column label="乙方" prop="party_b"></el-table-column>
            <el-table-column label="地点" prop="location"></el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDrawer(row)"></el-button>
                    <el-button :icon="View" circle plain type="primary" @click="jump(row.id)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="noteDelete(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />


    </el-card>
</template>


<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}

/* 抽屉样式 */
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}

.editor {
    width: 100%;

    :deep(.ql-editor) {
        min-height: 200px;
    }
}
</style>