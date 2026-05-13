package com.ticket.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("ticket")
public class Ticket {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String ticketNo;
    private String title;
    private String content;
    private Integer priority;
    private Integer status;
    private Long createUserId;
    private Long assignUserId;
    private LocalDateTime deadline;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;
}
