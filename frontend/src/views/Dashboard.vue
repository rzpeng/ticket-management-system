<template>
  <el-container class="dashboard-container">
    <el-aside width="200px">
      <div class="logo">工单系统</div>
      <el-menu :default-active="$route.path" router>
        <el-menu-item index="/dashboard/tickets">
          <el-icon><Document /></el-icon>
          <span>工单列表</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    
    <el-container>
      <el-header>
        <div class="header-content">
          <span>欢迎，{{ username }}</span>
          <el-button type="danger" size="small" @click="logout">退出</el-button>
        </div>
      </el-header>
      
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Document } from '@element-plus/icons-vue'

const router = useRouter()
const username = ref(localStorage.getItem('username') || '用户')

const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('username')
  router.push('/login')
}
</script>

<style scoped>
.dashboard-container {
  height: 100vh;
}

.el-aside {
  background-color: #545c64;
  color: #fff;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  font-size: 18px;
  font-weight: bold;
  background-color: #434a50;
}

.el-header {
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  align-items: center;
}

.header-content {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-menu {
  border: none;
}
</style>
