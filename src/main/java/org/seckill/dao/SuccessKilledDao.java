package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId 商品ID
	 * @param userPhone 用户电话
	 * @return 插入的行数
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体
	 * @param seckillId 商品ID
	 * @param userPhone 用户电话
	 * @return 秒杀结果
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
