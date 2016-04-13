package com.cuize.task.dao.order.domain;

import java.util.Date;

public class OrderOptlog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.order_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Integer orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.order_detail_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Integer orderDetailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.opt_type
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Integer optType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.opt_desc
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private String optDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.opt_user
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private String optUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.create_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.update_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_optlog.version
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.id
     *
     * @return the value of order_optlog.id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.id
     *
     * @param id the value for order_optlog.id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.order_id
     *
     * @return the value of order_optlog.order_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.order_id
     *
     * @param orderId the value for order_optlog.order_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.order_detail_id
     *
     * @return the value of order_optlog.order_detail_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.order_detail_id
     *
     * @param orderDetailId the value for order_optlog.order_detail_id
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.opt_type
     *
     * @return the value of order_optlog.opt_type
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Integer getOptType() {
        return optType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.opt_type
     *
     * @param optType the value for order_optlog.opt_type
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.opt_desc
     *
     * @return the value of order_optlog.opt_desc
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public String getOptDesc() {
        return optDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.opt_desc
     *
     * @param optDesc the value for order_optlog.opt_desc
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.opt_user
     *
     * @return the value of order_optlog.opt_user
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public String getOptUser() {
        return optUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.opt_user
     *
     * @param optUser the value for order_optlog.opt_user
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.create_time
     *
     * @return the value of order_optlog.create_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.create_time
     *
     * @param createTime the value for order_optlog.create_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.update_time
     *
     * @return the value of order_optlog.update_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.update_time
     *
     * @param updateTime the value for order_optlog.update_time
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_optlog.version
     *
     * @return the value of order_optlog.version
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_optlog.version
     *
     * @param version the value for order_optlog.version
     *
     * @mbggenerated Wed Apr 13 15:29:36 CST 2016
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}