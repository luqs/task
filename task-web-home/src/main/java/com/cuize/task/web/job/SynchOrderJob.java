package com.cuize.task.web.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.order.domain.OrderDetail;
import com.cuize.task.dao.order.domain.OrderDetailExample;
import com.cuize.task.meta.Constant;
import com.cuize.task.service.impl.OrderService;

@Component("synchOrder") 
@Transactional(value="order",rollbackFor=Exception.class)
public class SynchOrderJob {
	private static final Logger _LOG = LoggerFactory.getLogger(SynchOrderJob.class);
	
	@Autowired 
	private OrderService orderService;
	
	@Scheduled(fixedRate=1800000)
	//@Scheduled(cron="0 0 0 * * ?")
	public void synchStatus() throws Exception {
		_LOG.info("*******Timer Task SynchOrderJob.synchStatus start *******");
		OrderDetailExample detailExample = new OrderDetailExample();
		detailExample.createCriteria()
			.andStatEqualTo(Constant.ORDER_STATUS_PAID)
			.andThirdOrderNoLike("S%");
		List<OrderDetail> oLst = orderService.getHqPaidOrders();
		for(OrderDetail o:oLst){
			orderService.synchOrderStatus(o);
		}
		_LOG.info("*******Timer Task SynchOrderJob.synchStatus end *******");
	}
}
