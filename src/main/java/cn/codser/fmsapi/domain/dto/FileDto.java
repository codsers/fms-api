package cn.codser.fmsapi.domain.dto;

import lombok.Data;

@Data
public class FileDto extends BaseDto {
    private String fileId;
    private String appId;
    private String appName;
    private String fileName;
    private String realName;
    private String suffix;
    private String cusTag;
    private Integer enableDownload;
}
