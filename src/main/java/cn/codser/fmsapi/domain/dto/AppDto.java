package cn.codser.fmsapi.domain.dto;

import lombok.Data;

@Data
public class AppDto extends BaseDto {
    private String appId;
    private String appName;
    private String appDesc;
}
