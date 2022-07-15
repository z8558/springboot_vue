<template>
    <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 30px;font-weight: bold;color: dodgerblue;">后台管理</div>
    <div style="flex: 1;"></div>
    <div style="width: 100px;
        cursor: pointer;
        color: var(--el-color-primary);
        display: flex;
        align-items: center;">
        <el-dropdown>
        <span class="el-dropdown-link">
<!--            {{JSON.parse((sessionStorage.getItem("popo")))}}-->
<!--            {{fff}}-->
            {{user.name}}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item @click.native="$router.push('/person')">个人信息</el-dropdown-item>
                    <el-dropdown-item @click.native="$router.push('/login')">退出系统</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </div>

    </div>
</template>

<script>

    export default {
        name: "Header",
        // props:['user'],
        data(){
            return{
                user:''
            }
        },
        created() {
        },
        beforeMount() {
            // this.fff = sessionStorage.getItem('fff')
            this.user = JSON.parse(sessionStorage.getItem('user'))//session对象转为js对象才能正常使用
        }
    };
</script>

<style scoped>

</style>