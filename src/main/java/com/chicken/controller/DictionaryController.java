package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.AccountUser;
import com.chicken.model.Dictionary;
import com.chicken.model.WechatUser;
import com.chicken.service.AccountUserService;
import com.chicken.service.DictionaryService;
import com.chicken.service.RedisService;
import com.chicken.service.WechatUserService;
import com.chicken.util.*;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.DictionaryRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    RedisService redisService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/dictionaryPage")
    @RequiresPermissions("dictionaryPage")
    public Object dictionaryPage(Model model) {

        DictionaryRequest info = new DictionaryRequest();
        info.setDictType("dk");
        PageInfo<Dictionary> result = this.dictionaryService.selectByDictionary(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "dictionary/dictionaryList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dictionaryPageList", method = RequestMethod.POST)
    @RequiresPermissions("dictionaryPageList")
    public Object dictionaryPageList(@ModelAttribute DictionaryRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Dictionary> result = this.dictionaryService.selectByDictionary(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "dictionary/dictionaryList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, DictionaryRequest info, PageInfo<Dictionary> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("dictType", info.getDictType());
        model.addAttribute("dictName", info.getDictName());
        model.addAttribute("dictContent", info.getDictContent());
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dictionaryAdd/{type}")
    public Object dictionaryAdd(Model model, @PathVariable String type) {

        Dictionary dictionary = new Dictionary();
        dictionary.setStatus("1");
        dictionary.setDictType(type);
        model.addAttribute("dictionary", dictionary);

        return "dictionary/dictionaryAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/dictionaryEdit/{id}")
    public Object dictionaryEdit(Model model, @PathVariable Integer id) {
        Dictionary dictionary = this.dictionaryService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("createTime", sdf.format(dictionary.getCreateTime()));

        return "dictionary/dictionaryAdd";
    }

    /**
     * 添加/修改信息
     *
     * @param info
     * @return
     */
    @PostMapping(value = "/insertDictionary")
    @ResponseBody
    public Object insertDictionary(@RequestBody DictionaryRequest info) throws Exception {

        /**
         * 判断是否登陆
         */
        if (!userLogin()) {
            return CallResult.fail(CodeEnum.NO_LOGIN_MESSAGE.getMsg());
        }

        /**
         * 校验用户信息
         */
        String errorMsg = DictionaryUtils.checkDictionaryInsertRequest(info);
        if (StringUtils.isNotBlank(errorMsg)) {
            logger.info("字典信息，数据校验失败，传入参数{}", info.toString());
            return CallResult.fail(errorMsg);
        }


        /**
         * 封装对象
         */
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(info, dictionary);
        dictionary.setDictOrder(Integer.valueOf(info.getDictOrder()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.isEmpty(info.getId())) {
            //删除原来的key
            Dictionary dictionaryOld = this.dictionaryService.selectByPrimaryKey(Integer.valueOf(info.getId()));
            redisService.deleteKey("d:" + dictionaryOld.getDictName());

            dictionary.setId(Integer.valueOf(info.getId()));
            dictionary.setCreateTime(sdf.parse(info.getCreateTime()));
            dictionary.setCreateUser(Integer.valueOf(info.getCreateUser()));
            dictionary.setEditTime(new Date());
            this.dictionaryService.updateByPrimaryKey(dictionary);
            logger.info("字典信息，数据修改，修改ID{}，修改人:{}", info.getId(), getUserSession().getUserName());
        } else {

            dictionary.setCreateTime(new Date());
            dictionary.setCreateUser(getUserSession().getId());
            dictionaryService.insert(dictionary);
            logger.info("字典信息，添加内容，内容={}", info.toString());
        }

        if (info.getStatus().equals("1")) {
            redisService.set("d:" + info.getDictName(), info.getDictContent());
        } else {
            redisService.deleteKey("d:" + info.getDictName());
        }

        return CallResult.success();
    }
}
