<template>
    <div>
        <el-menu
                style="width: 200px; min-height: calc(100vh - 50px)"
                :default-active="$route.path.substr(1)"
                router
                class="el-menu-vertical-demo">
            <el-sub-menu index="1">
                <template #title>
                    <el-icon></el-icon>
                    <span>系统管理</span>
                </template>
                    <el-menu-item index="user">用户管理</el-menu-item>
                    <el-menu-item index="book">书籍管理</el-menu-item>
                    <el-menu-item index="news">新闻管理</el-menu-item>
            </el-sub-menu>

            <el-menu-item index="">
                <el-icon><setting /></el-icon>
                <span>设置</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        data(){
        },

    };


</script>

<style scoped>

</style>