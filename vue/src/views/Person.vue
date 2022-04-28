<template>
  <div style="width: 400px">
    <el-card>
      <el-form :model="form" label-width="80px" size="small">
        <el-form-item label="头像">
          <el-avatar :src="form.avatarUrl"></el-avatar>
          <el-upload
              class="avatar-uploader"
              action="http://localhost:9090/file/upload"
              :headers="headers"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
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

var mytoken = localStorage.getItem('user') // 要保证取到
// let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
let user = localStorage.getItem("user") != null ? JSON.parse(localStorage.getItem("user")) : {}
console.log(user)
// if (user) {
//   token = user.token;  // 设置请求头
// }
export default {
  name: "Person",
  data() {
    return {
      form: localStorage.getItem("user") != null ? JSON.parse(localStorage.getItem("user")) : {},
      imageUrl: '',
      headers: {token: user.token}
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
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
