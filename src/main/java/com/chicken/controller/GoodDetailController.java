package com.chicken.controller;

import com.chicken.model.GoodDetail;
import com.chicken.model.GoodInfo;
import com.chicken.service.GoodDetailService;
import com.chicken.service.GoodInfoService;
import com.chicken.util.ContantUtil;
import com.chicken.vo.GoodDetailRequest;
import com.chicken.vo.GoodInfoRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-01 23:25
 */
@Controller
@RequestMapping("goodDetail")
public class GoodDetailController {

    @Autowired
    GoodDetailService goodDetailService;

    @Autowired
    GoodInfoService goodInfoService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/goodDetailPage")
    @RequiresPermissions("goodDetailPage")
    public Object goodDetailPage(Model model) {

        GoodDetailRequest goodDetail = new GoodDetailRequest();
        PageInfo<Map> result = this.goodDetailService.selectByGoodDetail(goodDetail, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodId", 0);
        selectByGoodInfo(model);
        return "goodinfo/goodDetailList";
    }

    private void selectByGoodInfo(Model model) {
        List<GoodInfo> selectAll = this.goodInfoService.selectAll();
        model.addAttribute("goodInfoList", selectAll);

    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodDetailPageList", method = RequestMethod.POST)
    @RequiresPermissions("goodDetailPageList")
    public Object goodDetailPageList(@ModelAttribute GoodDetailRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.goodDetailService.selectByGoodDetail(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("goodId", StringUtils.isBlank(info.getGoodId())?0:Integer.valueOf(info.getGoodId()));
        selectByGoodInfo(model);

        return "goodinfo/goodDetailList";
    }
}
