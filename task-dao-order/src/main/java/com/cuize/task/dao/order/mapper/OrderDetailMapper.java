package com.cuize.task.dao.order.mapper;

import com.cuize.task.dao.order.domain.OrderDetail;
import com.cuize.task.dao.order.domain.OrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int countByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int deleteByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int insert(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int insertSelective(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    List<OrderDetail> selectByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    OrderDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByPrimaryKeySelective(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByPrimaryKey(OrderDetail record);
}