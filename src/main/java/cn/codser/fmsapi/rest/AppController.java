package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.doo.AppDo;
import cn.codser.fmsapi.domain.doo.FileDo;
import cn.codser.fmsapi.domain.dto.AppDto;
import cn.codser.fmsapi.domain.vo.RespVo;
import cn.codser.fmsapi.mapper.AppMapper;
import cn.codser.fmsapi.mapper.FileMapper;
import cn.codser.fmsapi.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用
 */
@RestController
@RequestMapping(value = "/fms/app")
public class AppController {

    @Autowired
    AppMapper appMapper;

    @Autowired
    FileMapper fileMapper;

    @Value("${FILE_HOME}")
    String fileHome;

    /**
     * 分页查询APP列表
     * @param appDto
     * @return
     */
    @PostMapping("list")
    ResponseEntity<Object> list(@RequestBody AppDto appDto) {
        JSONObject res=new JSONObject();
        res.put("data",appMapper.queryAll(appDto));
        res.put("total",appMapper.queryAllCount(appDto));
        return new ResponseEntity<>(RespVo.success(res,"查询成功"), HttpStatus.OK);
    }

    /**
     * 新增APP
     * @param appDo
     * @return
     */
    @PostMapping("add")
    @Transactional(rollbackFor = Exception.class)
    ResponseEntity<Object> add(@RequestBody AppDo appDo) {
        AppDo appDo1=appMapper.selectById(appDo.getAppId());
        if(appDo1!=null){
            return new ResponseEntity<>(RespVo.fail("APPID已存在"), HttpStatus.OK);
        }
        appDo.setCreateTime(System.currentTimeMillis());
        return new ResponseEntity<>(RespVo.success(appMapper.insert(appDo),"新增成功"), HttpStatus.OK);
    }
    /**
     * 编辑APP
     * @param appDo
     * @return
     */
    @PostMapping("edit")
    @Transactional(rollbackFor = Exception.class)
    ResponseEntity<Object> edit(@RequestBody AppDo appDo) {
        return new ResponseEntity<>(RespVo.success(appMapper.updateById(appDo),"编辑成功"), HttpStatus.OK);
    }

    /**
     * 删除APP
     * @param appId
     * @return
     */
    @GetMapping("rm")
    @Transactional(rollbackFor = Exception.class)
    ResponseEntity<Object> rm(String appId) throws Exception {
        QueryWrapper<FileDo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);
        List<FileDo> files=fileMapper.selectList(queryWrapper);
        // 删除所有文件
        if(files!=null&&files.size()>0){
            FileUtil.removeFile(fileHome,files);
            fileMapper.delete(queryWrapper);
        }
        return new ResponseEntity<>(RespVo.success(appMapper.deleteById(appId),"删除成功"), HttpStatus.OK);
    }
}
