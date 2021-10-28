package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.mapper.AppMapper;
import cn.codser.fmsapi.mapper.FileMapper;
import cn.codser.fmsapi.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 */
@Api(value = "文件下载接口", tags = "文件下载相关的接口")
@RestController
@RequestMapping(value = "/fms/fds")
public class FileDownloadController {
    @Autowired
    FileMapper fileMapper;

    @Autowired
    AppMapper appMapper;
    // fileHome
    @Value("${FILE_HOME}")
    String fileHome;

    /**
     * http://127.0.0.1:8888/fms/fds/download?fileId=2&appId=3
     * @param request
     * @param response
     * @param fileId 文件id
     * @throws Exception
     */
    @ApiOperation("根据文件id下载文件")
    @GetMapping("download")
    void download(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name="fileId")String fileId,
            @RequestParam(name="appId")String appId) throws Exception{
        QueryWrapper<FileDo> fileDoQueryWrapper=new QueryWrapper<>();
        fileDoQueryWrapper.eq("file_id",fileId);
        fileDoQueryWrapper.eq("app_id",appId);
        FileDo fileDo=fileMapper.selectOne(fileDoQueryWrapper);
        FileUtil.downloadFileByAppDoAndFileDo(request,response,fileHome,fileDo);
    }
}
