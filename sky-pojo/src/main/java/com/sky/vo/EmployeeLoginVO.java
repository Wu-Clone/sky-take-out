package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// @Data 注解是 Lombok 提供的简化类代码的注解 Getter Setter equals hashCode toString
@Data
// lombok 实现构建器模式（Builder Pattern）
@Builder
// 双 构造器
@NoArgsConstructor
@AllArgsConstructor
// swagger swagger文档生成的注解，描述API模型 用于自动生成 API 文档时展示给开发者或使用者。
@ApiModel(description = "员工登录返回的数据格式")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("jwt令牌")
    private String token;

}
