package com.cuize.task.dao.order.mapper;

import com.cuize.task.dao.order.domain.OrderQr;
import com.cuize.task.dao.order.domain.OrderQrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderQrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int countByExample(OrderQrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int deleteByExample(OrderQrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int insert(OrderQr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int insertSelective(OrderQr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    List<OrderQr> selectByExample(OrderQrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    OrderQr selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByExampleSelective(@Param("record") OrderQr record, @Param("example") OrderQrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByExample(@Param("record") OrderQr record, @Param("example") OrderQrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByPrimaryKeySelective(OrderQr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_qr
     *
     * @mbggenerated Tue Apr 12 17:22:54 CST 2016
     */
    int updateByPrimaryKey(OrderQr record);
}