package com.cuize.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 产品库存入库接口
	 * 
	 * @author luqingsong
	 */
	public void saveLog() throws Exception {
		
	}

	@Transactional(value = "order", propagation = Propagation.REQUIRES_NEW)
	public void deleLog() throws Exception {

	}

}
