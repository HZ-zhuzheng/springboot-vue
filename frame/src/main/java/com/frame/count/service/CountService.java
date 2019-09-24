package com.frame.count.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.along.entity.Count;
import com.frame.count.mapper.CountMapper;
import com.frame.tool.UUIDUtil;

@Service
public class CountService {
	
	@Autowired
	private CountMapper countMapper;
	
	/**
	 * 添加用户信息数据
	 * @Transactional 提交事务
	 * @return 操作记录数
	 */
	@Transactional
	public int addCountSer(Count count) {
		
		count.setId(UUIDUtil.createUUID());
		return countMapper.addCount(count);
	}
	
	/**
	 * 查询用户信息数据
	 * @return 集合
	 */
	@Transactional
	public List<Count> selectCountSer(Count count) {
		
		int paNo = count.getPageNo();
		int paSize = count.getPageSize();
		count.setPageNo((paNo-1)*paSize);
		
//		System.out.println(JSON.toJSONString(count));
		return countMapper.selectCount(count);
	}
	
	/**
	 * 查询记录总数
	 * @return 集合
	 */
	@Transactional
	public Count selectCountTotalSer(Count count) {
		Count count2 = new Count();
		count2.setCountNum(countMapper.selectCountTotal(count));
		return count2;
	}
	
	/**
	 * 修改用户信息数据
	 * @Transactional 提交事务
	 * @return 操作记录数
	 */
	@Transactional
	public int updateCountSer(Count count) {
		return countMapper.updateCount(count);
	}
	
	/**
	 * 删除用户信息数据
	 * @Transactional 提交事务
	 * @return 操作记录数
	 */
	@Transactional
	public int deleteCountSer(Count count) {
		for(String id : count.getDeleteID()) {
			Count count1 = new Count();
			count1.setId(id);
			int result = countMapper.deleteCount(count1);
			if (result == 0) {
				return  0;
			}
		}
		return 1;
	}
	
	
}
