package com.contract_management.contract.entity;

import com.contract_management.contract.anno.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class Log {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题
    @State
    private String state;//发布状态 已发布|草稿
    @NotEmpty
    private String category_name;//分类名称
    @NotNull
    private Integer category_id;//文章分类id
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime search_time;//更新时间
    private Integer create_user;//创建人ID
}
