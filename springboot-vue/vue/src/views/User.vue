<template>
  <div class="home" style="padding: 10px">
<!--功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>

<!--搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入昵称" clearable style="width: 20%;" />
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="Id" sortable/>
      <el-table-column prop="name" label="用户名"/>
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
<!--      <el-table-column prop="role" label="角色" />-->
        <el-table-column label="角色">
            <template #default="scope">
                <span v-if="scope.row.role === 1">管理员</span>
                <span v-if="scope.row.role === 2">普通用户</span>
            </template>
        </el-table-column>


      <el-table-column fixed="right" label="编辑" width="200">
        <template #default="scope">
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
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
          <el-form :model="form" label-width="120px">
              <el-form-item label="用户名">
                  <el-input v-model="form.name" style="width: 75%;"/>
              </el-form-item>

              <el-form-item label="昵称">
                  <el-input v-model="form.nickName" style="width: 75%;"/>
              </el-form-item>

              <el-form-item label="年龄">
                  <el-input v-model="form.age" style="width: 75%;"/>
              </el-form-item>

              <el-form-item label="性别">
                  <el-input />
              </el-form-item>

              <el-form-item label="住址">
                  <el-input type="textarea" v-model="form.address" style="width: 75%;"/>
              </el-form-item>

              <el-form-item label="角色">

              </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
          </template>
      </el-dialog>



  </div>
</template>

<script>



import request from "@/utils/request";

export default {
  name: 'User',
  components: {

  },
  data(){
    return {
        dialogVisible : false,
        search : '',
        pageNum:1,
        pageSize : 10,
        total : 1,
        form : {},

      tableData : []
    }
  }  ,
created() {
      this.load()

},
    methods: {

    load(){         //查询
        request.get('http://localhost:9001/api/user',{ params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          search: this.search
        }}).then(res => {
            console.log(res)
            this.tableData = res.data.records
            this.total = res.data.total
        })
    },


    add() {
    this.dialogVisible = true
    this.form = {}
    },


    save(){         //添加
     if (this.form.id){
         request.put('http://localhost:9001/api/user', this.form).then(res => {
             if (res.code === '0') {
             this.$message({
                 type: "success",
                 message: "修改成功"
             })
         }else {
             this.$message({
                 type: "error",
                 message: res.msg
             })
         }
             this.load()                //刷新表格数据
             this.dialogVisible = false//关闭弹窗
         })
     }  else {
         request.post('http://localhost:9001/api/user', this.form).then(res => {
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
    },


    handleSizeChange(){         //改变当前每页的个数触发
        this.load()
    },
    handleCurrentChange(){      //改变当前的页码触发
        this.load()
    },


    handleDelete(id) {             //删除
        request.delete('http://localhost:9001/api/user/' + id).then(res => {
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
