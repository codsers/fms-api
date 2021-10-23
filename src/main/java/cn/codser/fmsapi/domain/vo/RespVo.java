package cn.codser.fmsapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespVo {
    private Object data; // 业务数据
    private String msg; // 消息
    private Integer code; // [2000:成功，1000：失败]

    public static RespVo success(Object data,String msg){
        return new  RespVo(data, msg, 2000);
    }
    public static RespVo fail(String msg){
        return new  RespVo(null, msg, 1000);
    }
}
