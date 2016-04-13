package com.cuize.task.service.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.order.domain.Order;
import com.cuize.task.dao.order.domain.OrderExample;
import com.cuize.task.dao.order.mapper.OrderMapper;
import com.cuize.task.meta.Constant;
import com.cuize.task.service.impl.OrderOptlogService;

@Component("synchOrder") 
@Transactional(value="order",rollbackFor=Exception.class)
public class SynchOrderJob {
	private static final Logger _LOG = LoggerFactory.getLogger(SynchOrderJob.class);
	
	@Autowired 
	private OrderOptlogService logService;
	
	@Autowired 
	private OrderMapper orderMapper;
	
	@Scheduled(fixedRate=10000)
	public void synchStatus() throws Exception {
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andStatEqualTo(Constant.ORDER_STATUS_PAID);
		List<Order> oLst = orderMapper.selectByExample(orderExample);
		
		_LOG.info("*******result*******");
	}
}
