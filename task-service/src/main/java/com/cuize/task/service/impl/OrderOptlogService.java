package com.cuize.task.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.order.domain.OrderOptlog;
import com.cuize.task.dao.order.mapper.OrderOptlogMapper;

/**
 * 产品库存入库接口
 * 
 * @author luqingsong
 *
 */
@Service
@Transactional(value = "order", rollbackFor = Exception.class)
public class OrderOptlogService {
	private static final Logger _LOG = LoggerFactory
			.getLogger(OrderOptlogService.class);

	@Autowired 
	private OrderOptlogMapper optlogMapper;
	/**
	 * 产品库存入库接口
	 * 
	 * @author luqingsong
	 */
	@Transactional(value = "order")
	public void saveLog(Integer orderId,Integer optType,String optDesc,String optUser) throws Exception {
		OrderOptlog log = new OrderOptlog();
		log.setCreateTime(new Date());
		log.setOptDesc(optDesc);
		log.setOptType(optType);
		log.setOptUser(optUser);
		log.setOrderId(orderId);
		log.setVersion(1);
		optlogMapper.insert(log);
		_LOG.info("******** 记录操作日志："+optDesc);
	}

}
