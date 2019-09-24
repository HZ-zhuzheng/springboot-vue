package com.frame.count.mapper;

import java.util.List;

import com.along.entity.Count;

public interface CountMapper {
	
	/**添加 用户信息
	 * @param count
	 * @return
	 */
	int addCount(Count count);
	
	/** 查询 用户信息
	 * @param count
	 * @return
	 */
	List<Count> selectCount(Count count);
	
	/** 查询 记录数
	 * @param count
	 * @return
	 */
	Integer selectCountTotal(Count count);
	
	/**修改 用户信息
	 * @param count
	 * @return
	 */
	int updateCount(Count count);
	
	/**删除 用户信息
	 * @param count
	 * @return
	 */
	int deleteCount(Count count);

}
