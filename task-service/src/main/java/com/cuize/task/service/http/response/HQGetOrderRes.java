package com.cuize.task.service.http.response;

import java.util.List;

public class HQGetOrderRes {
	private Result result;
	private List<Log> logs;
	private Order order;
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public class Result{
		private Boolean status;
		private String errormessage;
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public String getErrormessage() {
			return errormessage;
		}
		public void setErrormessage(String errormessage) {
			this.errormessage = errormessage;
		}
	}
	
	public class Log{
		private String OptAction;
		private String Memo;
		private String ExecTime;
		public String getOptAction() {
			return OptAction;
		}
		public void setOptAction(String optAction) {
			OptAction = optAction;
		}
		public String getMemo() {
			return Memo;
		}
		public void setMemo(String memo) {
			Memo = memo;
		}
		public String getExecTime() {
			return ExecTime;
		}
		public void setExecTime(String execTime) {
			ExecTime = execTime;
		}
	}

	public class Order{
		private String OrderNo;
		private String BookNumber;
		private String OrderStatus;
		private String CreateDate;
		private String BookPerson;
		private String BookMobile;
		public String getOrderNo() {
			return OrderNo;
		}
		public void setOrderNo(String orderNo) {
			OrderNo = orderNo;
		}
		public String getBookNumber() {
			return BookNumber;
		}
		public void setBookNumber(String bookNumber) {
			BookNumber = bookNumber;
		}
		public String getOrderStatus() {
			return OrderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			OrderStatus = orderStatus;
		}
		public String getCreateDate() {
			return CreateDate;
		}
		public void setCreateDate(String createDate) {
			CreateDate = createDate;
		}
		public String getBookPerson() {
			return BookPerson;
		}
		public void setBookPerson(String bookPerson) {
			BookPerson = bookPerson;
		}
		public String getBookMobile() {
			return BookMobile;
		}
		public void setBookMobile(String bookMobile) {
			BookMobile = bookMobile;
		}
	}
}
