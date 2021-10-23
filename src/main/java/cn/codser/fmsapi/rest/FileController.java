package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.domain.dto.AppDto;
import cn.codser.fmsapi.domain.dto.FileDto;
import cn.codser.fmsapi.domain.vo.RespVo;
import cn.codser.fmsapi.mapper.AppMapper;
import cn.codser.fmsapi.mapper.FileMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 文件
 */
@RestController
@RequestMapping(value = "/fms/file")
public class FileController {
    @Autowired
    AppMapper appMapper;

    @Autowired
    FileMapper fileMapper;

    @Value("${FILE_HOME}")
    String fileHome;
    /**
     * 分页查询文件列表
     * @param fileDto
     * @return
     */
    @PostMapping("list")
    ResponseEntity<Object> list(@RequestBody FileDto fileDto) {
        JSONObject res=new JSONObject();
        res.put("data",fileMapper.queryAll(fileDto));
        res.put("total",fileMapper.queryAllCount(fileDto));
        return new ResponseEntity<>(RespVo.success(res,"查询成功"), HttpStatus.OK);
    }

    /**
     * 改变文件下载状态
     * @param fileId
     * @return
     */
    @GetMapping("changeDownloadStatus")
    ResponseEntity<Object> changeDownloadStatus(String fileId,Integer dwe) {
        FileDo fileDo=new FileDo();
        fileDo.setFileId(fileId);
        fileDo.setEnableDownload(dwe==1?0:1);
        return new ResponseEntity<>(RespVo.success(fileMapper.updateById(fileDo),"修改状态成功"), HttpStatus.OK);
    }
}
