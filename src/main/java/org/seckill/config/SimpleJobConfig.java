package org.seckill.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.seckill.job.TestJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/8/15
 */
@Configuration
public class SimpleJobConfig {

	@Bean
	public TestJob testJob(){
		return new TestJob();
	}

	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter regCenter(@Value("${elastic-job.reg-center.server-list}") final String serverList,
											 @Value("${elastic-job.reg-center.namespace}") final String namespace) {
		return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
	}

}
