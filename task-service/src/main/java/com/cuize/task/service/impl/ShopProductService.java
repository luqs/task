package com.cuize.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuize.commons.dao.product.domain.ShopProduct;
import com.cuize.commons.dao.product.mapper.ShopProductMapper;

/**
 * 活动接口
 * 
 * @author Vinson
 * 
 */
@Service
@Transactional(value = "productTransactionManager", rollbackFor = Exception.class)
public class ShopProductService {
	private static final Logger _LOG = LoggerFactory.getLogger(ShopProductService.class);

	@Autowired
	private ShopProductMapper shopProductMapper;
	
	public void updateSalePriceBySetPrice(Integer shopProductId, Double setPrice) throws Exception {
		
		ShopProduct shopProduct = shopProductMapper.selectByPrimaryKey(shopProductId);
		
		if(shopProduct != null){
			shopProduct.setSalesPrice(setPrice);
			shopProductMapper.updateByPrimaryKey(shopProduct);
			_LOG.info("******** 修改价格调度成功 ********");
		}
	}

}
