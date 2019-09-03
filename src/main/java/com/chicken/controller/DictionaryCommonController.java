package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.Dictionary;
import com.chicken.service.DictionaryService;
import com.chicken.util.ContantUtil;
import com.chicken.vo.DictionaryRequest;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

/**
 * @author zhanglei
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryCommonController extends BaseController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/dictionaryCommonPage")
    @RequiresPermissions("dictionaryCommonPage")
    public Object dictionaryCommonPage(Model model) {

        DictionaryRequest info = new DictionaryRequest();
        info.setDictType("common");
        PageInfo<Dictionary> result = this.dictionaryService.selectByDictionary(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "dictionary/dictionaryCommonList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dictionaryCommonPageList", method = RequestMethod.POST)
    public Object dictionaryCommonPageList(@ModelAttribute DictionaryRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Dictionary> result = this.dictionaryService.selectByDictionary(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "dictionary/dictionaryCommonList";
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
        model.addAttribute("dictName",info.getDictName());
        model.addAttribute("dictContent",info.getDictContent());
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/dictionaryCommonAdd/{type}")
    public Object dictionaryChatAdd(Model model,@PathVariable String type) {

        Dictionary dictionary = new Dictionary();
        dictionary.setStatus("1");
        dictionary.setDictType(type);
        model.addAttribute("dictionary", dictionary);

        return "dictionary/dictionaryCommonAdd";
    }

    /**
     * 进入修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/dictionaryCommonEdit/{id}")
    public Object dictionaryChatEdit(Model model, @PathVariable Integer id) {
        Dictionary dictionary = this.dictionaryService.selectByPrimaryKey(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("createTime", sdf.format(dictionary.getCreateTime()));

        return "dictionary/dictionaryCommonAdd";
    }
}
