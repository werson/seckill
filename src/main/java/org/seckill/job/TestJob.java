package org.seckill.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/8/15
 */
public class TestJob implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		System.out.println("this is test job.");
	}
}
