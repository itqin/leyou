package com.leyou.item.service;

import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qin
 * @create 2019-04-25 14:07
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;


    public List<SpecGroup> querySpecGroup(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = groupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(HttpStatus.NOT_FOUND, "该分类下没有规格组");
        }
        return list;
    }

    public void addSpecGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        int count = groupMapper.insert(specGroup);
        if (count < 1) {
            throw new LyException(HttpStatus.BAD_REQUEST, "新增失败！");
        }
    }

    public void updateSpecGroup(SpecGroup specGroup) {
        int count = groupMapper.updateByPrimaryKey(specGroup);
        if (count < 1) {
            throw new LyException(HttpStatus.BAD_REQUEST, "更新失败！");
        }
    }

    public List<SpecParam> querySpecParams(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> list = paramMapper.select(specParam);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(HttpStatus.NOT_FOUND, "该分类下没有规格参数！");
        }
        return list;
    }

    public void addSpecParam(SpecParam specParam) {
        specParam.setId(null);
        int count = paramMapper.insert(specParam);
        if (count < 1) {
            throw new LyException(HttpStatus.BAD_REQUEST, "新增规格参数失败！");
        }
    }

    public void deleteSpecParam(Long id) {
        int count = paramMapper.deleteByPrimaryKey(id);
        if (count < 1) {
            throw new LyException(HttpStatus.BAD_REQUEST, "删除规格参数失败！");
        }
    }

    public void updateSpecParam(SpecParam specParam) {
        int count = paramMapper.updateByPrimaryKey(specParam);
        if (count < 1) {
            throw new LyException(HttpStatus.BAD_REQUEST, "更新规格参数失败！");
        }
    }
}
