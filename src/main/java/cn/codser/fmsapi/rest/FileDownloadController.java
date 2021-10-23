package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.mapper.AppMapper;
import cn.codser.fmsapi.mapper.FileMapper;
import cn.codser.fmsapi.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 */
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
     * http://127.0.0.1:8888/fms/fds/download?fileId=2
     * @param request
     * @param response
     * @param fileId 文件id
     * @throws Exception
     */
    @GetMapping("download")
    void download(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name="fileId")String fileId) throws Exception{
        FileDo fileDo = fileMapper.selectById(fileId);
        FileUtil.downloadFileByAppDoAndFileDo(request,response,fileHome,fileDo);
    }
}
