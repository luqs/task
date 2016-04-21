package com.cuize.task.meta;

public interface Constant {
	public static final Integer ORDER_STATUS_CREATE = 1;
	public static final Integer ORDER_STATUS_PAID = 2;
	public static final Integer ORDER_STATUS_FINISH = 3;
	public static final Integer ORDER_STATUS_CANCEL = 4;
	
	public static final Integer ORDEROPT_TYPE_CREATE = 1;
	public static final Integer ORDEROPT_TYPE_DELETE = 3;
	public static final Integer ORDEROPT_TYPE_UPDATE = 2;
	public static final Integer ORDEROPT_TYPE_QUERY = 4;
	
	public static final Integer THIRD_SYSTYPE_HIYO = 0;//嗨摇
	public static final Integer THIRD_SYSTYPE_HUANQI = 1;//环企
	public static final Integer THIRD_SYSTYPE_HUANQI_PAYFIRST = 2;//环企先支付
	public static final Integer THIRD_SYSTYPE_SHENDA = 3;//深大
	
	public static final Integer TICKET_DAYTYPE_TODAY = 1;//当日票
	public static final Integer TICKET_DAYTYPE_OTHERDAY = 2;//预约票
	public static final Integer TICKET_DAYTYPE_NOTDISPLAY = 0;//不显示
	public static final Integer TICKET_DAYTYPE_PACK = 3;//套票（打包票）
	
	public static final Integer PRODUCT_PACKTYPE_HUANQI = 1;//打包产品的打包类型
}
