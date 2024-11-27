<script setup>

import '@vueup/vue-quill/dist/vue-quill.snow.css'

import {ref} from "vue";
import {
    Edit,
    Delete,
    Notebook,
    Reading,
    View,
    Memo
} from '@element-plus/icons-vue'

const article = ref({
  title: '',
  category_id: '',
  cover_img: '',
  content: '',
  state: '',
  party_a: '',
  party_b: '',
  location: '',
  note: '',
  create_time:''
})

const historyParams = history.state.params;


import router from "@/router/index.js";
//获取公司数据
import {articleDetailService,noteUpdateService} from '@/api/article.js'
const articleDetail = async (id) => {
    let result = await articleDetailService(id);
    article.value = result.data[0];

}
articleDetail(historyParams.id);

//调用接口,添加表单
import { ElMessage } from 'element-plus'
const dialogVisible = ref(false)
const changeNote = async () => {
    //调用接口
    let result = await noteUpdateService(article.value);
    ElMessage.success(result.msg ? result.msg : '添加成功')

    //关闭该栏

    dialogVisible.value = false;
}

//定义变量,控制标题的展示
const title = ref('')

//展示编辑弹窗
const showDialog = () => {
    dialogVisible.value = true; title.value = '修改笔记'
    //数据拷贝
    // article.value.note = row.note;

}

//查看笔记
const nothing = async () => {

    //隐藏弹窗
    dialogVisible.value = false;
}



</script>


<script>
export default {
  name: "Article"
}
</script>

<template slot-scope="scope">

  <el-header class="header-bar">
    <el-text class = "header-text">
      标题: {{article.title}}
      <span style="font-size: large"><br>创建时间: {{article.create_time}}  甲方: {{article.party_a}}  乙方: {{article.party_b}} <br></span>

    </el-text>
    <span  >
      <el-text style="color: white;font-size: large">
        笔记
      </el-text>
      <el-button  :icon="Notebook" circle plain type="primary" @click="showDialog()"></el-button>

    </span>

  </el-header>
  <el-main class="page-container">
    <!--    展示合同-->
    <el-card class="card">
    <template #header>
      <div >
        <span style="color: white">合同内容</span>
      </div>
    </template>
      <el-row type="flex" justify="space-around" :gutter="20" >
            <p  class="contact_item" style="display: flex;flex-direction: column">
              <el-image
              :src="article.cover_img"
              id="img"
              style="height: 120vh;width: 100vh"
              />
              <el-text contentType="html">
                {{article.content}}
              </el-text>
            </p>
        </el-row>
    <template #footer>Footer content</template>
  </el-card>

    <!-- 编辑笔记 -->
        <el-dialog v-model="dialogVisible" :title="title" width="60%">
            <el-form :model="article" label-width="100px" style="padding-right: 30px">
                <el-form-item label="笔记内容" prop="note">
                        <el-input  v-model="article.note" type="textarea" style="width: 80vh" :rows="20" >
                        </el-input>
                </el-form-item>

            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '修改笔记' ? changeNote():nothing()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
  </el-main>
</template>

<style scoped>

  .header-bar{
    height: auto;
    background-color: #f6c5a7;
  }

  .header-text{
    font-size: x-large;
    color: white;
  }

  .card{
    margin-top: 5vh;
    font-family: Arial;
    font-size: 18px;
    line-height: 30px;
    font-weight: bold;

  background: linear-gradient(#e58f58 50%, rgba(255,255,255,0) 0) 0 0,
  radial-gradient(circle closest-side, #e58f58 53%, rgba(255,255,255,0) 0) 0 0,
  radial-gradient(circle closest-side, #e58f58 50%, rgba(255,255,255,0) 0) 55px 0 #f6c5a7;
  background-size: 110px 150px;
  background-repeat: repeat-x;
    animation: animated-border 1.5s infinite;
  font-family: Arial;
  font-size: 18px;
  line-height: 30px;
  font-weight: bold;
  color: white;
  border: 2px solid;
  border-radius: 10px;
  padding: 15px;
  }


</style>