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
import com.cuize.task.dao.order.domain.Order;
import com.cuize.task.dao.order.domain.OrderDetail;
import com.cuize.task.dao.order.domain.OrderDetailExample;
import com.cuize.task.dao.order.domain.OrderExample;
import com.cuize.task.dao.order.domain.OrderQr;
import com.cuize.task.dao.order.domain.OrderQrExample;
import com.cuize.task.dao.order.mapper.OrderDetailMapper;
import com.cuize.task.dao.order.mapper.OrderMapper;
import com.cuize.task.dao.order.mapper.OrderQrMapper;
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
	private OrderQrMapper qrMapper;
	
	@Autowired 
	private OrderDetailMapper detailMapper;
	
	@Autowired
	private OrderOptlogService optlogService;
	
	
	public List<Order> getHqPaidOrders() throws Exception {
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria()
			.andStatEqualTo(Constant.ORDER_STATUS_PAID)
			.andThirdpartOrderNoLike("S%");
		List<Order> oLst = orderMapper.selectByExample(orderExample,new RowBounds(0, 50));
		_LOG.info("*******getHqPaidOrders*******"+oLst);
		return oLst;
	}
	/**
	 * 产品库存入库接口
	 * 
	 * @author luqingsong
	 */
	public void synchOrderStatus(Order order) throws Exception {
		String hqGetOrderURL= new StringBuffer(config.getHqGetOrderUrl())
								.append("?otaaccount=").append(config.getHqAccount())
								.append("&otapassWord=").append(config.getHqPassword())
								.append("&orderno=").append(order.getThirdpartOrderNo())
								.toString();
		String resJson = Request
				.Get(hqGetOrderURL)
				.execute().returnContent()
				.asString(Charset.forName("utf-8"));
		HQGetOrderRes response= JSON.parseObject(
				resJson,new TypeReference<HQGetOrderRes>(){});
		_LOG.info("**********timer task :"+resJson);
		if(response.getResult().getStatus() && response.getOrder().getOrderStatus().equals("已取票")){
			//查询订单明细并修改订单明细的状态
			OrderDetailExample detailExample = new OrderDetailExample();
			detailExample.createCriteria()
				.andOrderIdEqualTo(order.getId());
			List<OrderDetail> deLst = detailMapper.selectByExample(detailExample);
			for(OrderDetail de:deLst){
				OrderDetailExample updateExample = new OrderDetailExample();
				updateExample.createCriteria()
					.andIdEqualTo(de.getId())
					.andVersionEqualTo(de.getVersion());
				de.setStat(Constant.ORDER_STATUS_FINISH);
				de.setVersion(de.getVersion()+1);
				de.setUpdateTime(new Date());
				detailMapper.updateByExampleSelective(de, updateExample);
			}
			//查询二维码并修改订二维码的状态
			OrderQrExample qrExample = new OrderQrExample();
			qrExample.createCriteria()
				.andOrderIdEqualTo(order.getId());
			List<OrderQr> qrLst = qrMapper.selectByExample(qrExample);
			for(OrderQr qr:qrLst){
				OrderQrExample updateExample = new OrderQrExample();
				updateExample.createCriteria()
					.andIdEqualTo(qr.getId())
					.andVersionEqualTo(qr.getVersion());
				qr.setStatus(Constant.ORDER_STATUS_FINISH);
				qr.setVersion(qr.getVersion()+1);
				qr.setUpdateTime(new Date());
				qrMapper.updateByExampleSelective(qr, updateExample);
			}
			OrderExample orderExample = new OrderExample();
			orderExample.createCriteria()
				.andIdEqualTo(order.getId())
				.andVersionEqualTo(order.getVersion());
			order.setVersion(order.getVersion()+1);
			order.setUpdateTime(new Date());
			orderMapper.updateByExample(order, orderExample);
			optlogService.saveLog(order.getId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态【"+response.getOrder().getOrderStatus()+"】", "TimerTask");
			_LOG.info("**********【定时任务】同步环企订单状态【"+response.getOrder().getOrderStatus()+"】");
		}else if(!response.getResult().getStatus()){
			optlogService.saveLog(order.getId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态失败【"+response.getResult().getErrormessage()+"】", "TimerTask");
			_LOG.info("**********【定时任务】同步环企订单状态【"+response.getOrder().getOrderStatus()+"】");
		}
	}

}
