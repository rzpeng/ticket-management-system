<template>
  <div>
    <el-card>
      <template #header>
        <div class="card-header">
          <span>工单列表</span>
          <el-button type="primary" @click="dialogVisible = true">创建工单</el-button>
        </div>
      </template>
      
      <el-table :data="tickets" v-loading="loading">
        <el-table-column prop="ticketNo" label="工单编号" width="150" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="priority" label="优先级" width="100">
          <template #default="{ row }">
            <el-tag :type="priorityMap[row.priority].type">
              {{ priorityMap[row.priority].label }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="statusMap[row.status].type">
              {{ statusMap[row.status].label }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleView(row)">查看</el-button>
            <el-button size="small" type="success" @click="handleProcess(row)" v-if="row.status === 0">
              处理
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :total="total"
        @current-change="loadTickets"
        layout="total, prev, pager, next"
        style="margin-top: 20px; justify-content: flex-end"
      />
    </el-card>
    
    <el-dialog v-model="dialogVisible" title="创建工单" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" type="textarea" :rows="4" />
        </el-form-item>
        <el-form-item label="优先级" prop="priority">
          <el-select v-model="form.priority" style="width: 100%">
            <el-option label="低" :value="1" />
            <el-option label="中" :value="2" />
            <el-option label="高" :value="3" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleCreate">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getTickets, createTicket, updateTicketStatus } from '../api/ticket'

const loading = ref(false)
const tickets = ref([])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const dialogVisible = ref(false)
const formRef = ref()

const form = ref({
  title: '',
  content: '',
  priority: 2
})

const rules = {
  title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
  content: [{ required: true, message: '请输入内容', trigger: 'blur' }],
  priority: [{ required: true, message: '请选择优先级', trigger: 'change' }]
}

const priorityMap = {
  1: { label: '低', type: 'info' },
  2: { label: '中', type: 'warning' },
  3: { label: '高', type: 'danger' }
}

const statusMap = {
  0: { label: '待处理', type: 'info' },
  1: { label: '处理中', type: 'warning' },
  2: { label: '已完成', type: 'success' },
  3: { label: '已关闭', type: 'danger' }
}

const loadTickets = async () => {
  loading.value = true
  try {
    const res = await getTickets(pageNum.value, pageSize.value)
    if (res.code === 200) {
      tickets.value = res.data.records
      total.value = res.data.total
    }
  } finally {
    loading.value = false
  }
}

const handleCreate = async () => {
  await formRef.value.validate()
  const res = await createTicket(form.value)
  if (res.code === 200) {
    ElMessage.success('创建成功')
    dialogVisible.value = false
    loadTickets()
    form.value = { title: '', content: '', priority: 2 }
  }
}

const handleProcess = async (row) => {
  const res = await updateTicketStatus(row.id, 1)
  if (res.code === 200) {
    ElMessage.success('已开始处理')
    loadTickets()
  }
}

const handleView = (row) => {
  ElMessage.info('查看详情功能待完善')
}

onMounted(() => {
  loadTickets()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
