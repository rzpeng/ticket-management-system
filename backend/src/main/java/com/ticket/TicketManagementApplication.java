package com.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工单管理系统启动类
 * 
 * @author Your Name
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.ticket.mapper")
public class TicketManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketManagementApplication.class, args);
        System.out.println("====================================");
        System.out.println("工单管理系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("====================================");
    }
}
