package com.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.dto.TicketCreateDTO;
import com.ticket.entity.Ticket;
import com.ticket.mapper.TicketMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import cn.hutool.core.util.IdUtil;

@Service
public class TicketService extends ServiceImpl<TicketMapper, Ticket> {
    
    public Ticket createTicket(TicketCreateDTO dto, Long userId) {
        Ticket ticket = new Ticket();
        ticket.setTicketNo("TK" + IdUtil.getSnowflakeNextIdStr());
        ticket.setTitle(dto.getTitle());
        ticket.setContent(dto.getContent());
        ticket.setPriority(dto.getPriority());
        ticket.setStatus(0); // 待处理
        ticket.setCreateUserId(userId);
        ticket.setAssignUserId(dto.getAssignUserId());
        
        if (StringUtils.hasText(dto.getDeadline())) {
            ticket.setDeadline(java.time.LocalDateTime.parse(dto.getDeadline()));
        }
        
        this.save(ticket);
        return ticket;
    }
    
    public Page<Ticket> getTicketPage(Integer pageNum, Integer pageSize, Integer status, Long userId) {
        LambdaQueryWrapper<Ticket> wrapper = new LambdaQueryWrapper<>();
        
        if (status != null) {
            wrapper.eq(Ticket::getStatus, status);
        }
        if (userId != null) {
            wrapper.eq(Ticket::getCreateUserId, userId)
                   .or()
                   .eq(Ticket::getAssignUserId, userId);
        }
        
        wrapper.orderByDesc(Ticket::getCreateTime);
        return this.page(new Page<>(pageNum, pageSize), wrapper);
    }
    
    public boolean updateStatus(Long ticketId, Integer status) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatus(status);
        return this.updateById(ticket);
    }
}
