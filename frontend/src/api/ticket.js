import request from './request'

export function getTickets(pageNum = 1, pageSize = 10, status) {
  return request.get('/tickets', {
    params: { pageNum, pageSize, status }
  })
}

export function createTicket(data) {
  return request.post('/tickets', data, {
    params: { userId: 1 } // TODO: 从token获取用户ID
  })
}

export function updateTicketStatus(id, status) {
  return request.put(`/tickets/${id}/status`, null, {
    params: { status }
  })
}

export function getTicketById(id) {
  return request.get(`/tickets/${id}`)
}
