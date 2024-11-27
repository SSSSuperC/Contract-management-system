<script setup>



import { ref } from 'vue'
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom,
    Notebook,
    Reading,
    View,
    Memo
} from '@element-plus/icons-vue'

//公司数据模型
const companies = ref(
    {
      id:'',
      business_name: '',
      business_img: '',
      business_url: ''
}
)

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
])

//获取公司数据
import {businessListService} from '@/api/business.js'
const businessList = async () => {
    let result = await businessListService();
    companies.value = result.data;
}
businessList();
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
        "cover_img": "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
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
])

const srclist = [
  'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
  'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
  'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
  'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg'
]

import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { Plus } from '@element-plus/icons-vue'

import {ExampleListService} from '@/api/article.js'
const ExampleList = async () =>{
  let result = await ExampleListService();
  articles.value = result.data;
  //处理数据,将img_url加入srclist
    for (let i = 0; i < articles.value.length; i++) {
        srclist[i] = articles.value[i].cover_img;
    }

}

ExampleList();

//导入token
import { useTokenStore } from '@/stores/token.js';
const tokenStore = useTokenStore();

// $(function(){
// $('#img').click(function(){
//
// $(this).toggleClass('min');
// $(this).toggleClass('max');
// });
// });

//添加契约
import {ElMessage} from 'element-plus'

import router from "@/router/index.js";

const  jump1 = async() => {
      router.push({name:'article_manage'})
    }

const  jump2 = async() => {

      router.push({name:'note_manage'})
    }

const  jump4 = async() => {

      router.push({name:'history'})
    }

const  jump3 = async() => {

      router.push({name:'log'})
    }

</script>



<template>
  <el-main class="main">
    <el-row class = "btn-group" type="flex" justify="start" :gutter="20" >

      <el-col :span="4" class="bt" style="position: relative">
      <el-button round class="btn" @click="jump1()">
        <el-icon color="#ee9900" size="50">
          <Reading>

          </Reading>
        </el-icon>
          我的合同
      </el-button>

      <el-button round class="btn" style="margin-left: 0;margin-top: 5vh" @click="jump3()">
        <el-icon color="#ee9900" size="50">
          <Memo>

          </Memo>
        </el-icon>
          检索记录
      </el-button>
      </el-col>

      <el-col :span = "4" class="bt" style="position: relative">
        <div >
          <el-button round class="btn" @click="jump2()">
        <el-icon color="#ee9900" size="50" >
          <Notebook>

          </Notebook>
        </el-icon>
          我的笔记
         </el-button>

      <el-button round class="btn" style="margin-left: 0;margin-top: 5vh" @click="jump4()">
        <el-icon color="#ee9900" size="50">
          <View>

          </View>
        </el-icon>
          阅读历史
      </el-button>
        </div>
      </el-col>

      <el-col :span = "14" class = "basic_info" style="position: relative;left: 10vh;">
        <div >
          <el-image src="/src/assets/基本信息.png"  style="height: 40vh"></el-image>
          <el-text style="width: 90vh;position: relative;left: 2vh;bottom: 2vh;color: black" size="large" type="info" line-clamp="10">
            在现代商业环境中，契约文书管理是企业运营和业务发展的关键环节之一。契约文书记录着各种商业活动的具体细节和法律责任，包括供应商合同、客户协议、服务条款等。然而，随着商业活动的不断增长，企业面临着越来越多的契约文书需要处理和管理的挑战。随着信息技术的快速演进，基于Web的契约文书管理平台正日益成为企业和组织管理中不可或缺的一部分。这些平台以其高效的文书处理和安全的信息管理而闻名，为各行各业带来了更加智能化和便捷化的文书管理解决方案。尤其是在当今数字化时代，随着文书数量的激增和法律法规的不断变化，对于文书处理和信息安全的需求变得愈发迫切。基于Web的契约文书管理平台不仅可以帮助企业实现契约文书管理的自动化和标准化，还可以为企业提供实时的文书信息和数据分析报告，帮助企业更好地了解自己的商业活动和合作伙伴，及时调整和优化业务策略。此外，通过基于Web的平台，企业可以实现文书管理的远程访问和跨部门协作，促进企业内部沟通和协同，提高组织的运作效率和响应速度。
          </el-text>
        </div>
      </el-col>
    </el-row>

    <el-card class="card">
    <template #header>
      <div >
        <span style="color: white">优秀企业</span>
      </div>
    </template>
      <el-row type="flex" justify="space-evenly">
    <p v-for="c in companies" :key="c.id" class="contact_item" style="display: flex;flex-direction: column;">{{ c.business_name }}
      <a :href="c.business_url">
      <el-image
      :src="c.business_img"
      style="width: 30vh;height: 20vh"

    />
      </a>
    </p>
        </el-row>
    <template #footer>Footer content</template>
  </el-card>



    <el-card class="card">
    <template #header>
      <div >
        <span style="color: white">示例合同</span>
      </div>
    </template>
      <el-row type="flex" justify="space-around" :gutter="20" >
            <p v-for="c in articles" :key="c.id" :label="c.title" :value="c.cover_img" class="contact_item" style="display: flex;flex-direction: column">{{c.title+"\n"}}
        <el-image
      :src="c.cover_img"
      id="img"
      class="min"
      :previewSrcList="srclist"
      :close-on-press-escape="true"
    />
    </p>
        </el-row>
    <template #footer>Footer content</template>
  </el-card>

  </el-main>

  <el-footer>
    <el-row justify="space-evenly" class="gradient">

    <el-text> 友情链接:</el-text>
    <el-link href="https://www.nwu.edu.cn/" target="_blank" class="bottom_font">|  西北大学  |</el-link>
    <el-link href="https://www.esign.cn/site/landing/contract?tsign_source_type=ONLINE_PROMOTION&tsign_source_detail=baidupc7-9716-298&utm_term=%E5%90%88%E5%90%8C%E5%B1%A5%E7%BA%A6%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fsaas&utm_campaign=%E7%99%BE%E5%BA%A6SEM&_channel_track_key=48hSGJaM&utm_source=sem**baidu**in%E7%99%BE%E5%BA%A6%E4%BB%98%E8%B4%B9&bd_vid=7693792319235786117" target="_blank" class="bottom_font">|  e签宝  |</el-link>
    <el-link href="https://jincenda.com/?source=150&kw=jcd-220425-pc-004&bd_vid=7864560696340290376" target="_blank" class="bottom_font">|  今承达  |</el-link>
    <el-link href="https://cont.12315.cn/" target="_blank" class="bottom_font">|  合同示范文本库  |</el-link>

  </el-row>
  </el-footer>

</template>
<style lang="scss" scoped>

.max{width:100%;height:auto;}
.min{width: 30vh;height: 30vh}

.el-link {
  margin-right: 8px;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}
.main {

  background-color:#f6ebe3;
background-image:
radial-gradient(white, rgba(255,255,255,.2) 2px, transparent 40px),
radial-gradient(white, rgba(255,255,255,.15) 1px, transparent 30px),
radial-gradient(white, rgba(255,255,255,.1) 2px, transparent 40px),
radial-gradient(rgba(255,255,255,.4), rgba(255,255,255,.1) 2px, transparent 30px);
background-size: 550px 550px, 350px 350px, 250px 250px, 150px 150px;
background-position: 0 0, 40px 60px, 130px 270px, 70px 100px;

  @keyframes animated-border {
  0% {
    box-shadow: 0 0 0 0 rgba(255,255,255,0.4);
  }
  100% {
     box-shadow: 0 0 0 20px rgba(255,255,255,0);
  }
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

  .contact_item {
    font-family: 仿宋;
    font-size: 18px;
    line-height: 30px;
    font-weight: bold;
    color: black;
    //padding: 40px;
    //border-radius: 10px;
    //box-shadow: 0 0 0 10px lightsalmon;
    //width: 32vh;
    //height: 20vh;
  }


  .btn-group {
    margin-top: 2vh;
    height: 50vh;
    .bt {
      display: flex;
      align-items: start;
      flex-direction: column;
      top: 2vh;

    }

    .btn {
      background-color: #f6c5a7;
      font-family: 华光行楷_CNKI;
      color: black;
      font-size: large;
      height: 15vh;
      width: 25vh;
    }

    .basic_info {
      margin-top: 3vh;
    font-family: Arial;
    font-size: 18px;
    line-height: 30px;
    font-weight: bold;
    padding: 20px;
    box-shadow: 0 0 0 10px white;
    outline: dashed 10px #FFC8B4;
      height: 45vh;
}
  }

  .bottom_font {
    font-family: 华光行楷_CNKI;
    color: black;
  }

  .gradient {
    /* 设置容器尺寸 - 原理1 */
    width: 100%;
    height: 200px;
    /* 背景渐变色 - 原理2 */
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    /* 背景尺寸 - 原理3 */
    background-size: 600% 600%;
    /* 循环动画 - 原理4 */
    animation: gradientBG 5s ease infinite;
  }

  /* 动画，控制背景 background-position */
  @keyframes gradientBG {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }
}

  .bottom_font
  {
  }

</style>