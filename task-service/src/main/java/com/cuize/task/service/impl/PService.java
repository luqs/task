package com.cuize.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.task.dao.product.mapper.ShopMapper;

@Service
@Transactional(value="product",rollbackFor=Exception.class)
public class PService {
	private static final Logger _LOG = LoggerFactory.getLogger(PService.class);
	
	
	@Autowired 
	private ShopMapper awardMapper;

	public synchronized void activityDraw() {
		_LOG.info("*******result*******"+awardMapper.selectByPrimaryKey(1));
	}
}
