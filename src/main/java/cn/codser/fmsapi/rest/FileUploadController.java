package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.domain.vo.RespVo;
import cn.codser.fmsapi.mapper.AppMapper;
import cn.codser.fmsapi.mapper.FileMapper;
import cn.codser.fmsapi.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传
 */
@RestController
@RequestMapping(value = "/fms/fus")
public class FileUploadController {
    @Autowired
    FileMapper fileMapper;

    @Autowired
    AppMapper appMapper;
    // fileHome
    @Value("${FILE_HOME}")
    String fileHome;

    /**
     * 上传文件
     * @param multipartFile
     * @param appId 应用 id
     * @param md5 文件md5
     * @param cusTag 标签
     * @param busInfo 业务数据
     * @return
     */
    @PostMapping("upload")
    @Transactional(rollbackFor = Exception.class)
    ResponseEntity<Object> upload(
            @RequestParam(name="file") MultipartFile multipartFile,
            @RequestParam(name="appId")String appId,
            @RequestParam(name="md5",required = false,defaultValue="")String md5,
            @RequestParam(name="ct",required = false,defaultValue = "")String cusTag,
            @RequestParam(name="bi",required = false,defaultValue = "{}")String busInfo){
        try{
            AppDo appDo=appMapper.selectById(appId);
            if(appDo==null){
                return new ResponseEntity<>(RespVo.fail("appId未注册"), HttpStatus.OK);
            }
            String destMd5= StringUtils.isEmpty(md5) ?FileUtil.getMd5(multipartFile):md5;

            FileDo fileDo=fileMapper.selectById(destMd5);
            String fileId=null;
            if(fileDo==null){
                String savePath=FileUtil.getSavePath(multipartFile,appId,fileDo.PUBLIC_FILE_TYPE);
                File dest=FileUtil.uploadMultipartFile(multipartFile,fileHome,savePath);
                FileDo saveFileDo = new FileDo();
                saveFileDo.setFileId(destMd5);
                saveFileDo.setSha256(FileUtil.getSha256(dest));
                saveFileDo.setRealName(multipartFile.getOriginalFilename());
                saveFileDo.setSaveName(dest.getName());
                saveFileDo.setSuffix(FileUtil.getExtName(multipartFile.getOriginalFilename()));
                saveFileDo.setPath(savePath);
                saveFileDo.setSize(multipartFile.getSize());
                saveFileDo.setShowSize(FileUtil.getShowSize(multipartFile.getSize()));
                saveFileDo.setAppId(appId);
                saveFileDo.setCusTag(cusTag);
                saveFileDo.setBusInfo(busInfo);
                saveFileDo.setUploadTime(System.currentTimeMillis());
                saveFileDo.setEnableDownload(1);
                fileMapper.insert(saveFileDo);
                fileId=saveFileDo.getFileId();
            }else{
                fileId=fileDo.getFileId();
            }
            return new ResponseEntity<>(RespVo.success(fileId,"上传成功"), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(RespVo.fail(ex.getMessage()), HttpStatus.OK);
        }
    }
}
