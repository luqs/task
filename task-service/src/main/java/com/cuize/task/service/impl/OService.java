package com.cuize.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.order.mapper.OrderMapper;

@Service
@Transactional(value="order",rollbackFor=Exception.class)
public class OService {
	private static final Logger _LOG = LoggerFactory.getLogger(OService.class);
	
	
	@Autowired 
	private OrderMapper awardMapper;

	public synchronized void activityDraw() {
		_LOG.info("*******result*******"+awardMapper.selectByPrimaryKey(1));
	}
}
