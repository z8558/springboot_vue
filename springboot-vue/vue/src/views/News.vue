<template>
    <div class="home" style="padding: 10px">
        <!--功能区域-->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add">新增</el-button>
        </div>

        <!--搜索区域-->
        <div style="margin: 10px 0">
            <el-input v-model="search" placeholder="请输入标题" clearable style="width: 20%;" />
            <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
        </div>

        <el-table :data="tableData" border stripe style="width: 100%">
            <el-table-column prop="id" label="Id" sortable/>
            <el-table-column prop="title" label="标题"/>
            <el-table-column prop="author" label="作者" />
            <el-table-column prop="time" label="发布时间" />


            <el-table-column fixed="right" label="编辑" width="200">
                <template #default="scope">
                    <el-button size="small" @click="details(scope.row)">查看</el-button>
                    <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-popconfirm title="确认要删除吗?" @confirm="handleDelete(scope.row.id)">
                        <template #reference>
                            <el-button  size="small" type="danger">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <div style="margin: 10px 0">
            <el-pagination
                    v-model:currentPage="pageNum"
                    v-model:page-size="pageSize"
                    :page-sizes="[5, 10, 20]"
                    :small="true"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            />
        </div>
        <el-dialog v-model="dialogVisible" title="提示" width="50%">
            <el-form :model="form" label-width="120px" @submit.native.prevent>
                <el-form-item label="标题">
                    <el-input v-model="form.title" style="width: 50%;" />
                </el-form-item>

                <div id="div1">
                    <Toolbar
                            style="border-bottom: 1px solid #ccc"
                            :editor="editorRef"
                            :defaultConfig="toolbarConfig"
                            :mode="mode"
                    />
                    <Editor
                            style="height: 500px; overflow-y: hidden;"
                            v-model="valueHtml"
                            :defaultConfig="editorConfig"
                            :mode="mode"
                            @onCreated="handleCreated"
                    />
                </div>

            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
            </template>
        </el-dialog>

        <el-dialog v-model="vis" width="50%">
            <div v-html="detail.title" style="height: 50px;"></div>
        <el-card>
            <div v-html="detail.content" style="min-height: 100px"></div>
        </el-card>
        </el-dialog>

    </div>
</template>

<script>



    import request from "@/utils/request";
    import '@wangeditor/editor/dist/css/style.css' // 引入 css
    import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
    import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

    export default {
        name: 'News',
        components: {
            Editor, Toolbar
        },


        setup() {
            // 编辑器实例，必须用 shallowRef
            const editorRef = shallowRef()

            // 内容 HTML
            const valueHtml = ref('')

            const toolbarConfig = {}
            const editorConfig = { placeholder: '请输入内容...' }
            // 组件销毁时，也及时销毁编辑器
            onBeforeUnmount(() => {
                const editor = editorRef.value
                if (editor == null) return
                editor.destroy()
                this.valueHtml = ''
            })
            const handleCreated = (editor) => {
                editorRef.value = editor // 记录 editor 实例，重要！
            }


            return {
                editorRef,
                valueHtml,
                mode: 'default', // 或 'simple'
                toolbarConfig,
                editorConfig,
                handleCreated,
                selector: '#editor-container',
                config: editorConfig,
            };
        },



            data(){
            return {
                dialogVisible : false,
                search : '',
                pageNum:1,
                pageSize : 10,
                total : 1,
                form : {},
                tableData : [],
                vis: false,
                detail: {}
            }

        }  ,
        created() {
            this.load()
        },

        methods: {

            details(row){
               this.detail = row
                this.vis = true
            },

            fileUpload(res){
               this.form.cover = res.data;
            },

            load(){         //查询
                request.get('http://localhost:9001/api/news',{ params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        search: this.search
                    }}).then(res => {
                    console.log(res)
                    this.tableData = res.data.records
                    this.total = res.data.total
                    this.valueHtml = ''
                })
            },


            add() {
                this.dialogVisible = true
                this.form = {}
            },


            save(){         //添加
                this.form.content = this.valueHtml;

                if (this.form.id){//有id修改,没id添加
                    request.put('http://localhost:9001/api/news', this.form).then(res => {
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "修改成功"
                            })
                        }else {
                            this.$message({
                                type: "error",
                                message: res.msg,
                            })
                        }
                        this.load()                //刷新表格数据
                        this.dialogVisible = false//关闭弹窗
                    })
                }  else {
                    let userStr = JSON.parse(sessionStorage.getItem('user')) || "{}"
                    this.form.author = userStr.nickName
                    request.post('http://localhost:9001/api/news', this.form).then(res => {
                        this.$message({
                            type: "success",
                            message: "添加成功"
                        })
                        this.load()                    //刷新表格数据
                        this.dialogVisible = false     //关闭弹窗
                    })
                }
            },


            handleEdit(row) {           //编辑
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogVisible = true
                this.valueHtml = this.form.content
            },


            handleSizeChange(){         //改变当前每页的个数触发
                this.load()
            },
            handleCurrentChange(){      //改变当前的页码触发
                this.load()
            },


            handleDelete(id) {             //删除
                request.delete('http://localhost:9001/api/news/' + id).then(res => {
                    if (res.code === '0') {
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load()
                })
            }
        }
    }
</script>
