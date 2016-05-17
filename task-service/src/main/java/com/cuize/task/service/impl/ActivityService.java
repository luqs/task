package com.cuize.task.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.commons.dao.activity.domain.ScheduleSetPrice;
import com.cuize.commons.dao.activity.domain.ScheduleSetPriceExample;
import com.cuize.commons.dao.activity.mapper.ScheduleSetPriceMapper;

/**
 * 活动接口
 * 
 * @author Vinson
 * 
 */
@Service
@Transactional(value = "activityTransactionManager", rollbackFor = Exception.class)
public class ActivityService {
	private static final Logger _LOG = LoggerFactory.getLogger(ActivityService.class);

	@Autowired
	private ScheduleSetPriceMapper scheduleSetPriceMapper;
	
	
	public List<ScheduleSetPrice> findScheduleSets(Date baginDate) throws Exception {
		ScheduleSetPriceExample setExample = new ScheduleSetPriceExample();
		setExample.createCriteria().andUpdateTimeGreaterThanOrEqualTo(baginDate);
		List<ScheduleSetPrice> setPrices = scheduleSetPriceMapper.selectByExample(setExample);
		
		_LOG.info("******** 查询修改价格调度信息的日志："+ setPrices);
		return setPrices;
	}

}
