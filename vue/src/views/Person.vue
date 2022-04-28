<template>
  <div style="width: 400px">
    <el-card>
      <el-form :model="form" label-width="80px" size="small">
        <el-form-item label="头像">
          <el-avatar :src="form.avatarUrl"></el-avatar>
        </el-form-item>
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
      <div align="center">
        <el-button type="primary" @click="edit">确 定</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: localStorage.getItem("user") != null ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
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
    }
  }
}
</script>

<style scoped>

</style>
