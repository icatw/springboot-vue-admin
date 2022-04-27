<template>
  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"
                class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"
                class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger" @click="batchDelByIds(ids)">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      <el-upload
          class="upload-demo"
          action="http://localhost:9090/sysUser/import"
          :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" class="ml-5" @click="exportUserList">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="del(scope.row.id)">删除 <i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog :title="addType=='0'?'新增用户信息':'编辑用户信息'" :visible.sync="dialogFormVisible" width="30%">
      <!--        <el-dialog v-else-if="dataStatus==1" title="编辑用户信息" :visible.sync="dialogFormVisible" width="30%">-->

      <el-form :model="form" label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addType=='0'?save():edit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username: "",
      email: "",
      address: "",
      headerBg: 'headerBg',
      addType: '',//0,新增，1，修改
      dialogFormVisible: false,
      ids: [],
      form: {},

    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    // ?pageNum="
    // + this.pageNum + "&pageSize=" + this.pageSize + "&username=" + this.username + "&email=" + this.email + "&address=" + this.address
    load() {
      this.request.get("/sysUser/page", {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              username: this.username,
              email: this.email,
              address: this.address
            }
          }
      ).then(res => {
        console.log(res);
        this.tableData = res.records;
        this.total = res.total;
      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    reset() {
      this.username = ''
      this.email = ''
      this.address = ''
      this.load()
    },
    //打开新增对话框
    handleAdd() {
      this.addType = 0
      this.dialogFormVisible = true;
      this.form = {}
    },
    //新增对话框发送请求
    save() {
      this.request.post("/sysUser", this.form).then(res => {
        console.log(res)
        if (res) {
          this.$message.success("保存成功！")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败！")
        }
      })
    },
    //编辑
    handleEdit(row) {
      this.addType = 1
      this.dialogFormVisible = true
      this.form = row
    },
    edit() {
      this.request.post("/sysUser", this.form).then(res => {
        console.log(res)
        if (res) {
          this.$message.success("修改成功！")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("修改失败！")
        }
      })
    },
    del(id) {
      this.$confirm('此操作将永久删除该用户信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.request.delete("/sysUser/" + id).then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '删除成功!',
            });
            this.load()
          } else {
            this.$message({
              type: 'warning',
              message: '删除失败!',
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      console.log(this.ids)
    },
    batchDelByIds(ids) {
      if (ids.length !== 0) {
        this.$confirm('是否确认删除这' + ids.length + '条数据?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          this.request.delete("/sysUser/batchDelete/" + ids)
        }).then(() => {
          this.load()
          this.$message.success('删除成功')
        })
      } else {
        this.$message.info('未选择数据！')
      }
    },
    //导出Excel
    exportUserList() {
      window.open("http://localhost:9090/sysUser/export")
      this.$message.success("导出成功！")
    },
    //导入Excel
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee !important;
}
</style>
