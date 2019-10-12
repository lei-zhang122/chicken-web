package com.chicken.scheduletask;

import com.alibaba.fastjson.JSONObject;
import com.chicken.model.AccountUser;
import com.chicken.model.WechatUser;
import com.chicken.service.*;
import com.chicken.util.ContantUtil;
import com.chicken.util.DateUtil;
import com.chicken.vo.AccountDetailRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-20 19:14
 */
@Component
public class WeekPushJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    AccountHitService accountHitService;

    @Autowired
    AccountSignedService accountSignedService;

    @Autowired
    GoodInfoService goodInfoService;

    @Autowired
    AccountUserService accountUserService;

    /**
     * 每周推送一次
     */
    @Scheduled(initialDelay = 1000, fixedRate = 1000 * 60 * 60 * 24 * 7)
    public void weekPushByUser() {

        logger.info("每周推送，查询每周推送的用户信息");
        int pageNum;
        int pageSize;
        Long count = wechatUserService.selectCount();
        logger.info("每周推送，总用户数量 :{} ", count);

        Integer[] val = ContantUtil.getPageNumAndPageSize(count);
        pageNum = val[0];
        pageSize = val[1];
        logger.info("每周推送，数据分{}页 ", pageNum);
        for (int i = 1; i <= pageNum; i++) {
            PageInfo<WechatUser> userList = this.wechatUserService.selectUserByPage(pageNum, pageSize);
            if (userList.getList().size() > 0) {
                for (WechatUser u : userList.getList()) {
                    String now = DateUtil.currentYYYYMMDDWithSymbol();
                    String seven = DateUtil.getSpecifiedDayAfter(new Date(), "yyyy-MM-dd", -7);

                    AccountDetailRequest accountDetailRequest = new AccountDetailRequest();
                    accountDetailRequest.setHitUserId(u.getId());
                    accountDetailRequest.setStartTime(seven);
                    accountDetailRequest.setEndTime(now);
                    List<Map> list = this.accountHitService.selectHitMyCount(accountDetailRequest);
                    if (null != list && list.size() > 0) {
                        String nickName = list.get(0).get("nick_name") + "";
                        logger.info("推送用户提醒，上一周被{}揍的最多，用户id={}", nickName, u.getId());
                        String content = "这周" + nickName + "揍你小鸡的次数最多哦，快去‘回揍’TA！";
                        pushMsg(u.getOpenid(), ContantUtil.INVOKE_URL, content);
                    }
                }
            }
        }
    }


    /**
     * 每天查询一次
     */
    @Scheduled(cron = "0 0 19 * * ?")
    public void pushBeckon() {

        logger.info("用户唤醒，查询用户唤醒推送的用户信息");
        int pageNum;
        int pageSize;
        Long count = accountSignedService.selectCountByTips();
        logger.info("用户唤醒，总用户数量 :{} ", count);

        Integer[] val = ContantUtil.getPageNumAndPageSize(count);
        pageNum = val[0];
        pageSize = val[1];
        logger.info("用户唤醒，数据分{}页 ", pageNum);
        for (int i = 1; i <= pageNum; i++) {
            PageInfo<Map> signedList = this.accountSignedService.selectTips(pageNum, pageSize);
            if (signedList.getList().size() > 0) {
                for (Map m : signedList.getList()) {
                    Integer diffDate = Integer.valueOf(m.get("diffDate").toString());
                    String openid = m.get("openid").toString();
                    String content = null;
                    if (diffDate == 4) {
                        content = "小鸡想主人了，快回来看看我吧~";
                        pushMsg(openid, ContantUtil.INVOKE_URL, content);
                    } else if (diffDate == 5) {
                        //查询商品
                        Map goodInfo = this.goodInfoService.selectMinVirtual();

                        //用户id
                        Integer userId = Integer.valueOf(m.get("user_id").toString());

                        //查询用户积分
                        AccountUser accountUser = this.accountUserService.selectByUserId(userId);

                        Integer virtual = Integer.valueOf(goodInfo.get("zong").toString());

                        Integer diff = accountUser.getBalance().intValue() / virtual * 100;

                        if (diff < 50) {
                            Integer diffVal = accountUser.getBalance().intValue() - virtual;
                            content = "您还差" + diffVal + "元气值就可以兑换" + goodInfo.get("good_name") + "了哦，快来赚取元气值换奖品吧";
                        } else {
                            content = "您现在还有" + accountUser.getBalance().intValue() + "个元气值，可以直接兑换" + goodInfo.get("good_name") + "啦，快来兑换吧！";
                        }

                        pushMsg(openid, ContantUtil.INVOKE_URL, content);
                    } else if (diffDate == 6) {
                        content = "今天有人揍了你的小鸡，快去看看是谁吧！";
                        pushMsg(openid, ContantUtil.INVOKE_URL, content);
                    } else if (diffDate == 7) {
                        content = "工作不开森，生活有鸭梨？快来揍小鸡解解闷，发泄你的情绪，还有奖品可以拿哦！";
                        pushMsg(openid, ContantUtil.INVOKE_URL, content);
                    }
                    if (StringUtils.isNotBlank(content)) {
                        logger.info("用户唤醒，推送给用户消息，用户openid={}，用户{}天未登录。", openid, diffDate);
                    }
                }
            }
        }
    }


    /**
     * 发送消息
     *
     * @return
     */
    private ResponseEntity<String> pushMsg(String openid, String url, String content) {
        ResponseEntity<String> responseEntity = null;
        try {
            logger.info("开始调用接口，接口地址：{}", url);
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", content);
            jsonObject.put("openid", openid);
            jsonObject.put("type", "3");
            logger.info("调用接口，传入参数{}", jsonObject.toJSONString());
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toJSONString(), headers);
            responseEntity = restTemplate.postForEntity(url, entity, String.class);
            logger.info("调用接口，返回结果{},用户openid={}", responseEntity.getBody(), openid);
        } catch (Exception e) {
            logger.info("调用接口失败:{}", e.getMessage());
        }
        return responseEntity;
    }
}
