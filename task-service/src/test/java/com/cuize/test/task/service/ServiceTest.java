/**
 * 
 */
package com.cuize.test.task.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cuize.task.service.impl.OService;

/**
 * @author xyz(Auto-generated)
 * The Service class for the ko_product_stock database table.
 *
 */
public class ServiceTest extends BaseServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);

	@Autowired
	private OService service;
	
	@Test
	public void countByParams() throws Exception {
		
		service.activityDraw();
	}

}
