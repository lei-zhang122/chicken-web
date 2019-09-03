package com.chicken.dao;

import com.chicken.model.Dictionary;
import com.chicken.vo.DictionaryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Integer id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);

    List<Dictionary> selectByDictionay(DictionaryRequest dictionaryRequest);
}