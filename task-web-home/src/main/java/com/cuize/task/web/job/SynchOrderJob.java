package com.cuize.task.web.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.order.domain.Order;
import com.cuize.task.dao.order.domain.OrderExample;
import com.cuize.task.meta.Constant;
import com.cuize.task.service.impl.OrderOptlogService;
import com.cuize.task.service.impl.OrderService;

@Component("synchOrder") 
@Transactional(value="order",rollbackFor=Exception.class)
public class SynchOrderJob {
	private static final Logger _LOG = LoggerFactory.getLogger(SynchOrderJob.class);
	
	@Autowired 
	private OrderOptlogService logService;
	
	@Autowired 
	private OrderService orderService;
	
	@Scheduled(fixedRate=3000)
	public void synchStatus() throws Exception {
		_LOG.info("*******Timer Task SynchOrderJob.synchStatus start *******");
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria()
			.andStatEqualTo(Constant.ORDER_STATUS_PAID)
			.andThirdpartOrderNoLike("S%");
		List<Order> oLst = orderService.getHqPaidOrders();
		for(Order o:oLst){
			orderService.synchOrderStatus(o);
		}
		_LOG.info("*******Timer Task SynchOrderJob.synchStatus end *******");
	}
}
