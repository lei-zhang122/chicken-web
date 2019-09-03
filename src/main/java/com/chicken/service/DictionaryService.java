package com.chicken.service;

import com.chicken.dao.DictionaryDao;
import com.chicken.model.AccountDetail;
import com.chicken.model.Dictionary;
import com.chicken.vo.AccountDetailRequest;
import com.chicken.vo.DictionaryRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-01 21:10
 */
@Service
public class DictionaryService {

    @Autowired
    DictionaryDao dictionaryDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return dictionaryDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(Dictionary record) {
        return dictionaryDao.insert(record);
    }

    public Dictionary selectByPrimaryKey(Integer id) {
        return dictionaryDao.selectByPrimaryKey(id);
    }


    public List<Dictionary> selectAll() {
        return dictionaryDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Dictionary record) {
        return dictionaryDao.updateByPrimaryKey(record);
    }


    public PageInfo<Dictionary> selectByDictionary(DictionaryRequest dictionaryRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Dictionary> userLists = dictionaryDao.selectByDictionay(dictionaryRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
