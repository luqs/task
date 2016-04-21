package com.cuize.task.service.impl;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.cuize.task.dao.order.domain.OrderDetailPackdtl;
import com.cuize.task.dao.order.domain.OrderDetailPackdtlExample;
import com.cuize.task.dao.order.mapper.OrderDetailMapper;
import com.cuize.task.dao.order.mapper.OrderDetailPackdtlMapper;
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
	private OrderDetailPackdtlMapper packdtlMapper;

	@Autowired
	private OrderOptlogService optlogService;

	public List<OrderDetail> getHqPaidOrders() throws Exception {
		OrderDetailExample detailExample = new OrderDetailExample();
		detailExample.or()
				.andStatEqualTo(Constant.ORDER_STATUS_PAID)
				.andThirdOrderNoLike("S%");
		detailExample.or()
				.andProductPacktypeEqualTo(Constant.PRODUCT_PACKTYPE_HUANQI);
		
		List<OrderDetail> oLst = detailMapper.selectByExample(detailExample,
				new RowBounds(0, 100));
		_LOG.info("*******getHqPaidOrders*******" + oLst);
		return oLst;
	}

	/**
	 * 产品库存入库接口
	 * 
	 * @author luqingsong
	 */
	public void synchOrderStatus(OrderDetail orderDetail) throws Exception {
		if (orderDetail.getProductPacktype() == Constant.PRODUCT_PACKTYPE_HUANQI) {
			//如果order_detail对应的产品是打包产品 
			OrderDetailPackdtlExample example = new OrderDetailPackdtlExample();
			example.createCriteria().andOrderDetailIdEqualTo(orderDetail.getId());
			List<OrderDetailPackdtl> oPackdtlLst = packdtlMapper.selectByExample(example);
			for(OrderDetailPackdtl oPackdtl:oPackdtlLst){
				if(!StringUtils.isBlank(oPackdtl.getThirdOrderNo())
						&&Constant.THIRD_SYSTYPE_HUANQI.equals(oPackdtl.getTicketSystype())){
					String hqGetOrderURL = new StringBuffer(config.getHqGetOrderUrl())
								.append("?otaaccount=").append(config.getHqAccount())
								.append("&otapassWord=").append(config.getHqPassword())
								.append("&orderno=").append(oPackdtl.getThirdOrderNo())
								.toString();
					String resJson = Request.Get(hqGetOrderURL).execute()
							.returnContent().asString(Charset.forName("utf-8"));
					HQGetOrderRes response = JSON.parseObject(resJson,
							new TypeReference<HQGetOrderRes>() {
							});
					_LOG.info("**********timer task packdtl:" + resJson);
					if (response.getResult().getStatus() && (response.getOrder().getOrderStatus().equals("已取票") || response.getOrder().getOrderStatus().equals("已取消"))) {
						// 修改订单明细的状态
						OrderDetailPackdtlExample updateExample = new OrderDetailPackdtlExample();
						updateExample.createCriteria()
								.andIdEqualTo(oPackdtl.getId())
								.andVersionEqualTo(oPackdtl.getVersion());
						if(response.getOrder().getOrderStatus().equals("已取票")){
							oPackdtl.setStat(Constant.ORDER_STATUS_FINISH);
						}else if(response.getOrder().getOrderStatus().equals("已取消")){
							oPackdtl.setStat(Constant.ORDER_STATUS_CANCEL);
						}
						oPackdtl.setVersion(orderDetail.getVersion() + 1);
						oPackdtl.setUpdateTime(new Date());
						packdtlMapper.updateByExampleSelective(oPackdtl, updateExample);

						optlogService.saveLog(orderDetail.getOrderId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态【"
										+ response.getOrder().getOrderStatus() + "】",
								"TimerTask");
						_LOG.info("**********【定时任务】同步环企订单状态【"
								+ response.getOrder().getOrderStatus() + "】");
					} else if (!response.getResult().getStatus()) {
						optlogService.saveLog(orderDetail.getOrderId(),
								Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态失败【"
										+ response.getResult().getErrormessage() + "】",
								"TimerTask");
						_LOG.info("**********【定时任务】同步环企订单状态失败【"
								+ response.getResult().getErrormessage() + "】");
					}
				}
				
				
			}

		} else {
			//非打包产品
			String hqGetOrderURL = new StringBuffer(config.getHqGetOrderUrl())
					.append("?otaaccount=").append(config.getHqAccount())
					.append("&otapassWord=").append(config.getHqPassword())
					.append("&orderno=").append(orderDetail.getThirdOrderNo())
					.toString();
			String resJson = Request.Get(hqGetOrderURL).execute()
					.returnContent().asString(Charset.forName("utf-8"));
			HQGetOrderRes response = JSON.parseObject(resJson,
					new TypeReference<HQGetOrderRes>() {
					});
			_LOG.info("**********timer task :" + resJson);
			if (response.getResult().getStatus() && (response.getOrder().getOrderStatus().equals("已取票") || response.getOrder().getOrderStatus().equals("已取消"))) {
				// 修改订单明细的状态
				OrderDetailExample updateExample = new OrderDetailExample();
				updateExample.createCriteria()
						.andIdEqualTo(orderDetail.getId())
						.andVersionEqualTo(orderDetail.getVersion());
				if(response.getOrder().getOrderStatus().equals("已取票")){
					orderDetail.setStat(Constant.ORDER_STATUS_FINISH);
				}else if(response.getOrder().getOrderStatus().equals("已取消")){
					orderDetail.setStat(Constant.ORDER_STATUS_CANCEL);
				}
				orderDetail.setVersion(orderDetail.getVersion() + 1);
				orderDetail.setUpdateTime(new Date());
				detailMapper.updateByExampleSelective(orderDetail, updateExample);

				optlogService.saveLog(orderDetail.getOrderId(), Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态【"
								+ response.getOrder().getOrderStatus() + "】",
						"TimerTask");
				_LOG.info("**********【定时任务】同步环企订单状态【"
						+ response.getOrder().getOrderStatus() + "】");
			} else if (!response.getResult().getStatus()) {
				optlogService.saveLog(orderDetail.getOrderId(),
						Constant.ORDEROPT_TYPE_UPDATE, "【定时任务】同步环企订单状态失败【"
								+ response.getResult().getErrormessage() + "】",
						"TimerTask");
				_LOG.info("**********【定时任务】同步环企订单状态失败【"
						+ response.getResult().getErrormessage() + "】");
			}
		}

	}

}
