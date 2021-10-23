package cn.codser.fmsapi.mapper;

import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.domain.dto.FileDto;
import cn.codser.fmsapi.domain.vo.FileVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface FileMapper extends BaseMapper<FileDo> {
    // 分页查询文件列表
    List<FileVo> queryAll(FileDto fileDto);
    Integer queryAllCount(FileDto fileDto);
}
