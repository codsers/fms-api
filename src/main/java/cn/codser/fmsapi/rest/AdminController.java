package cn.codser.fmsapi.rest;

import cn.codser.fmsapi.domain.vo.RespVo;
import cn.codser.fmsapi.utils.WebUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/fms/admin")
public class AdminController {

    @Value("${fms.admin.username}")
    String adminUsername;

    @Value("${fms.admin.password}")
    String adminPassword;

    /**
     * 首页/登录
     * @param request
     * @param modelMap
     * @return
     */
    @GetMapping
    public String index(HttpServletRequest request, ModelMap modelMap) {
        if(!WebUtil.sessionIsExist(request)){
            return "login";
        }else{
            return "index";
        }
    }

    /**
     * 登录json接口
     * @param request
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @GetMapping("login.json")
    public RespVo login(
                        HttpServletRequest request,
                        @RequestParam("username")String username,
                        @RequestParam("password")String password) {
        if(username.equals(adminUsername)&&password.equals(adminPassword)){
            request.getSession().setAttribute("username",adminUsername);
            request.getSession().setAttribute("nickname","管理员");
            return RespVo.success("","登录成功");
        }else{
            return RespVo.fail("用户名密码不正确！");
        }
    }

    /**
     * 登出
     * @param session
     * @param sessionStatus
     * @return
     */
    @ResponseBody
    @GetMapping("logout.json")
    public RespVo logout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();
        return RespVo.success("","登出成功");
    }
}
