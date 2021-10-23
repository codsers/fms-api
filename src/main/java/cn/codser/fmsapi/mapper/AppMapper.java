package cn.codser.fmsapi.mapper;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.dto.AppDto;
import cn.codser.fmsapi.domain.vo.AppVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AppMapper extends BaseMapper<AppDo> {
    // 分页查询APP列表
    List<AppVo> queryAll(AppDto appDto);
    Integer queryAllCount(AppDto appDto);
}
