package com.contract_management.contract.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
public class Business
{
    @NotNull
    private Integer id;//主键ID
    private String business_name;//企业名称
    private String business_img;//企业图片url
    private String business_url;//企业官网地址
}
