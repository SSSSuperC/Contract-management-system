<script setup>
import { ref } from 'vue'
import useUserInfoStore from '@/stores/userInfo.js'
const userInfoStore = useUserInfoStore();
import {ElMessage} from 'element-plus'
import {userPasswordUpdateService} from "../../api/user";

//用户密码
const userInfo = ref({...userInfoStore.info})
//用户更新数据
const changeData = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd:''
})

//校验密码的函数
const checkRePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次确认密码'))
    } else if (value !== changeData.value.new_pwd) {
        callback(new Error('请确保两次输入的密码一样'))
    } else {
        callback()
    }
}


//定义表单校验规则
const rules = {
    new_pwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
    ],
    re_pwd: [
        { validator: checkRePassword, trigger: 'blur' }
    ]
}

//修改个人密码
import {userInfoUpdateService} from '@/api/user.js'

const updatePwdInfo = async ()=>{
    //调用接口
    let result = await userPasswordUpdateService(changeData.value);

    ElMessage.success(result.msg? result.msg:'修改成功')


}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="changeData" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="旧密码">
                        <el-input v-model="changeData.old_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input v-model="changeData.new_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码确认" prop="new_pwd">
                        <el-input v-model="changeData.re_pwd"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatePwdInfo">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>