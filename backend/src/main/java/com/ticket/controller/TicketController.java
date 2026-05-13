package com.ticket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ticket.dto.TicketCreateDTO;
import com.ticket.entity.Ticket;
import com.ticket.service.TicketService;
import com.ticket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;
    
    @PostMapping
    public Result<Ticket> create(@RequestBody TicketCreateDTO dto, @RequestParam Long userId) {
        Ticket ticket = ticketService.createTicket(dto, userId);
        return Result.success(ticket);
    }
    
    @GetMapping
    public Result<Page<Ticket>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Long userId) {
        
        Page<Ticket> page = ticketService.getTicketPage(pageNum, pageSize, status, userId);
        return Result.success(page);
    }
    
    @PutMapping("/{id}/status")
    public Result<String> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        boolean success = ticketService.updateStatus(id, status);
        
        if (!success) {
            return Result.error("更新失败");
        }
        
        return Result.success("更新成功");
    }
    
    @GetMapping("/{id}")
    public Result<Ticket> getById(@PathVariable Long id) {
        Ticket ticket = ticketService.getById(id);
        
        if (ticket == null) {
            return Result.error("工单不存在");
        }
        
        return Result.success(ticket);
    }
}
