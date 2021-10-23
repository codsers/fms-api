package cn.codser.fmsapi.domain.doo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fms_apps")
public class AppDo {
    @TableId
    private String appId; // 应用id
    private String appName; // 应用名称
    private String appDesc; // 应用描述
    private Long createTime; // 创建时间
}
