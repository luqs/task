package com.cuize.task.service.dto;

import com.cuize.task.meta.RequireField;


public class DrawInDto {
		@RequireField
		private String activityCode;
		@RequireField
		private String openid;

		public String getActivityCode() {
			return activityCode;
		}

		public void setActivityCode(String activityCode) {
			this.activityCode = activityCode;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

}
