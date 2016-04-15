package com.cuize.task.service.impl;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cuize.task.dao.order.domain.OrderDetail;
import com.cuize.task.dao.order.domain.OrderDetailExample;
import com.cuize.task.dao.order.mapper.OrderDetailMapper;
import com.cuize.task.dao.order.mapper.OrderMapper;
import com.cuize.task.meta.Constant;
import com.cuize.task.service.dto.GlobalConfig;
import com.cuize.task.service.http.response.HQGetOrderRes;

/**
 * 产品库存入库接口
 * 
 * @author luqingsong
 *
 */
@Service
@Transactional(value = "order", rollbackFor = Exception.class)
public class OrderService {
	private static final Logger _LOG = LoggerFactory
			.getLogger(OrderService.class);

	@Autowired
	private GlobalConfig config;
	
	@Autowired 
	private OrderMapper orderMapper;
	
	@Autowired 
	private OrderDetailMapper detailMapper;
	
	@Autowired
	private OrderOptlogService optlogService;
	
	
	public List<OrderDetail> getHqPaidOrders() throws Exception {
		OrderDetailExample detailExample = new OrderDetailExample();
		detailExample.createCriteria()
			.andStatEqualTo(Constant.ORDER_STATUS_PAID)
			.andThirdOrderNoLike("S%");
		List<OrderDetail> oLst = detailMapper.selectByExample(detailExample,new RowBounds(0, 100));
		_LOG.info("*******getHqPaidOrders*******"+oLst);
		return oLst;
	}
	/**
	 * 产品库存入库接口
	 * 
	 * @author luqingsong
	 */
	public void synchOrderStatus(OrderDetail orderDetail) throws Exception {
		String hqGetOrderURL= new StringBuffer(config.getHqGetOrderUrl())
								.append("?otaaccount=").append(config.getHqAccount())
								.append("&otapassWord=").append(config.getHqPassword())
								.append("&orderno=").append(orderDetail.getThirdOrderNo())
								.toString();
		String resJson = Request
				.Get(hqGetOrderURL)
				.execute().returnContent()
				.asString(Charset.forName("utf-8"));
		HQGetOrderRes response= JSON.parseObject(
				resJson,new TypeReference<HQGetOrderRes>(){});
		_LOG.info("**********timer task :"+resJson);
		if(response.getResult().getStatus() && response.getOrder().getOrderStatus().equals("已取票")){
			//修改订单明细的状态
			OrderDetailExample updateExample = new OrderDetailExample();
			updateExample.createCriteria()
				.andIdEqualTo(orderDetail.getId())
				.andVersionEqualTo(orderDetail.getVersion());
			orderDetail.setStat(Constant.ORDER_STATUS_FINISH);
			orderDetail.setVersion(orderDetail.getVersion()+1);
			orderDetail.setUpdateTime(new Date());
			detailMapper.updateByExampleSelective(orderDetail, updateExample);
			
			optlogService.saveLog(orderDetail.getOrderId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态【"+response.getOrder().getOrderStatus()+"】", "TimerTask");
			_LOG.info("**********【定时任务】同步环企订单状态【"+response.getOrder().getOrderStatus()+"】");
		}else if(!response.getResult().getStatus()){
			optlogService.saveLog(orderDetail.getOrderId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态失败【"+response.getResult().getErrormessage()+"】", "TimerTask");
			_LOG.info("**********【定时任务】同步环企订单状态失败【"+response.getResult().getErrormessage()+"】");
		}
	}

}
