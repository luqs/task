package com.cuize.task.web.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cuize.commons.dao.activity.domain.ScheduleSetPrice;
import com.cuize.commons.utils.DateUtils;
import com.cuize.task.service.impl.ActivityService;
import com.cuize.task.service.impl.ShopProductService;

@Component("scheduleSetSalePrice") 
public class ScheduleSetSalePriceJob {
	private static final Logger _LOG = LoggerFactory.getLogger(ScheduleSetSalePriceJob.class);
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ShopProductService shopProductService;
	
	private final String minDateFormat = "yyyy-MM-dd HH:mm";
	
	@Scheduled(cron="0 0/30 * * * ?")
	public void excuteJob() throws Exception {
		_LOG.info("*******Timer Task ScheduleSetSalePriceJob.excuteJob start *******");
		// 获取当前时间
		Date minDate = DateUtils.getNowDate(minDateFormat);
		// 得到大于当前时间的Jobs
		List<ScheduleSetPrice> setPriceJobs = activityService.findScheduleSets(minDate);
		
		for (ScheduleSetPrice scheduleSetPrice : setPriceJobs) {
			// 每天的0时0分执行符合条件的Job
			if(minDate.compareTo(DateUtils.convertDateFormat(scheduleSetPrice.getUpdateTime(), minDateFormat)) == 0){
				shopProductService.updateSalePriceBySetPrice(scheduleSetPrice.getProductId(), scheduleSetPrice.getSetPrice());
			}
		}
		_LOG.info("*******Timer Task ScheduleSetSalePriceJob.excuteJob end *******");
	}
}
