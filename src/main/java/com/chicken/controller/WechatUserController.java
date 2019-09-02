package com.chicken.controller;

import com.alibaba.fastjson.JSONObject;
import com.chicken.base.BaseController;
import com.chicken.model.WechatUser;
import com.chicken.service.RedisService;
import com.chicken.service.WechatUserService;
import com.chicken.util.CallResult;
import com.chicken.util.CodeEnum;
import com.chicken.util.ContantUtil;
import com.chicken.util.WechatUserUtils;
import com.chicken.vo.WechatUserRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("wechatUser")
public class WechatUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    RedisService redisService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/wechatUserPage")
    @RequiresPermissions("wechatUserPage")
    public Object wechatUserPage(Model model) {

        WechatUserRequest info = new WechatUserRequest();
        PageInfo<Map> result = this.wechatUserService.selectByWechatUser(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "wechatuser/wechatUserList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatUserPageList", method = RequestMethod.POST)
    @RequiresPermissions("wechatUserPageList")
    public Object wechatUserPageList(@ModelAttribute WechatUserRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.wechatUserService.selectByWechatUser(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "wechatuser/wechatUserList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, WechatUserRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("nickName", info.getNickName());
        model.addAttribute("inviteNum", info.getInviteNum());
        model.addAttribute("regSource", info.getRegSource());
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/wechatUserEdit/{id}")
    public Object wechatUserEdit(Model model, @PathVariable Integer id) {
        WechatUser wechatUser = this.wechatUserService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("wechatUser", wechatUser);
        model.addAttribute("createTime", sdf.format(wechatUser.getCreateTime()));

        return "wechatuser/wechatUserAdd";
    }

    /**
     * 添加/修改信息
     *
     * @param wechatUserRequest
     * @return
     */
    @PostMapping(value = "/insertWechatUser")
    @ResponseBody
    public Object insertWechatUser(@RequestBody WechatUserRequest wechatUserRequest) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail(CodeEnum.NO_LOGIN_MESSAGE.getMsg());
        }

        /**
         * 校验用户信息
         */
        String errorMsg = WechatUserUtils.checkWechatUserInsertRequest(wechatUserRequest);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("用户信息，数据校验失败，传入参数{}", wechatUserRequest.toString());
            return CallResult.fail(errorMsg);
        }


        /**
         * 封装对象
         */
        WechatUser wechatUser = new WechatUser();
        BeanUtils.copyProperties(wechatUserRequest, wechatUser);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(wechatUserRequest.getId())) {

            wechatUser.setId(Integer.valueOf(wechatUserRequest.getId()));
            wechatUser.setCreateTime(sdf.parse(wechatUserRequest.getCreateTime()));
            this.wechatUserService.updateByPrimaryKey(wechatUser);
            logger.info("用户信息，数据修改，修改ID{},修改人:{}", wechatUserRequest.getId(), getUserSession().getUserName());
        }

        //启用状态
        /*if (wechatUser.getStatus().equals("1")) {
            redisService.set("userId:" + wechatUser.getId(), JSONObject.toJSON(wechatUser));
        } else {
            redisService.deleteKey("userId:" + wechatUser.getId());
        }*/

        return CallResult.success();
    }
}
