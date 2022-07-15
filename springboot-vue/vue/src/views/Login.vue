<template>
    <div  style="margin: 0; width: 100%; height: 100vh; overflow: hidden">
        <div style="width: 400px; margin: 100px auto">
        <div style="color: black;font-size: 30px;text-align: center; padding: 30px 0">欢迎登录</div>
            <el-form :model="form" ref="form" size="normal" :rules="rules">
                <el-form-item prop="name">
                    <el-input v-model="form.name"  prefix-icon="Avatar"/>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" prefix-icon="Lock" show-password/>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "Login",
        data() {
            return {
                form: {},
                rules: {
                    name: [
                        {required: true, message: '请输入用户名',trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码',trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
          login() {
           this.$refs['form'].validate((valid)=> {//账号密码都输入才执行验证登录
               if (valid){
                   request.post("http://localhost:9001/api/user/login",this.form).then(res =>{
                       if (res.code === '0'){
                           this.$message({
                               type: "success",
                               message: "登录成功",
                           })
                            // sessionStorage.setItem("user",this.form.name)

                           sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
                           // sessionStorage.setItem("fff","QWQ")
                           this.$router.push("/user")//登录成功后跳转主页
                       }else {
                           this.$message({
                               type: "error",
                               message: res.msg
                           })
                       }
                   })
               }
           })
          },
     }
    }
</script>

<style scoped>

</style>