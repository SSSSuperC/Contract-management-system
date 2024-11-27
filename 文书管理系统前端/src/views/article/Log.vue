<script setup>
import {
    Edit,
    Delete,
    View
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const log = ref([
    {
        "id": 3,
        "title":"da",
        "category_name":"你好",
        "category_id": "5",
        "state": "草稿",
        "location":"西安",
        "search_time": "2023-09-02 12:06:59",
        "create_user": "4"
    },

])
//声明一个异步的函数
import { logListService,logDeleteService} from '@/api/log.js'
const logList = async () => {
    let result = await logListService();
    log.value = result.data;

}
logList();
//控制添加分类弹窗

//添加分类数据模型
const logModel = ref({
    category_name: '',
    category_alias: ''
})

//调用接口,添加表单
import { ElMessage } from 'element-plus'






//清空记录的数据
const clearData = () => {
    ElMessageBox.confirm(
        '你确认要清空检索记录吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //调用接口
            let result = await logDeleteService();
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            //刷新列表
            logList();
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '用户取消了删除',
            })
        })
}

//删除分类
import {ElMessageBox} from 'element-plus'
import router from "@/router/index.js";

const useLog = async(row) =>
{
    const params = { category_id: row.category_id,state : row.state, title: row.title, location: row.location,category_name:row.category_name }
    router.push({ name: 'article_manage', state: { params } })
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>检索记录</span>
                <div class="extra">
                    <el-button type="primary" @click="clearData()">清空记录</el-button>
                </div>
            </div>
        </template>
        <el-table :data="log" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="标题" prop="title"></el-table-column>
            <el-table-column label="分类名称" prop="category_name"></el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="地点" prop="location"></el-table-column>
            <el-table-column label="查询时间" prop="search_time"></el-table-column>

            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="View" circle plain type="primary" @click="useLog(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>


    </el-card>
</template>

<style lang="scss" scoped>
.page-container {

    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>