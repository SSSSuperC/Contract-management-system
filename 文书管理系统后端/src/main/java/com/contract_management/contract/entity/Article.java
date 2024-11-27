package com.contract_management.contract.entity;



import com.contract_management.contract.anno.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    @NotEmpty
    @URL
    private String cover_img;//封面图像

    @State
    private String state;//发布状态 已发布|草稿
    @NotNull(message = "分类id不能为空")
    private Integer category_id;//文章分类id
    private Integer create_user;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_time;//更新时间
    private String party_a;
    private String party_b;
    private String location;
    private String note;//文章笔记
    @NotNull
    private boolean checked;//是否被查看
}
