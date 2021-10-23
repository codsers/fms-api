package cn.codser.fmsapi.domain.doo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fms_files")
public class FileDo {
    public static final String PUBLIC_FILE_TYPE="public"; // 公共类型[默认]
    public static final String ENCRYPT_FILE_TYPE="encrypt"; // 加密类型
    @TableId
    private String fileId; // md5值
    private String sha256; // sha256值
    private String realName; // 真实文件名
    private String saveName; // 保存文件名
    private String suffix; // 后缀
    private String path; // 保存路径
    private Long size; // 大小
    private String showSize; // 显示大小
    private String cusTag; // 自定义标签
    private String busInfo; // 业务数据
    private Long uploadTime; // 创建时间
    private String appId; // appId
    private Integer enableDownload; // 是否启用下载
}
