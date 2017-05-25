package com.xiaoming.constants;

public class Constants {
	
	public static final String URL_JOIN_QRCODE = "http://localhost:8080/GaoXiaoXiaoMing/#/regist";
	
	/**
	 * ============扫描包的前缀名称=============
	 */
	public static final String PACKAGE_NAME = "com.xiaoming";

	/**
	 * ============权限类型=============
	 */
	// 系统管理员
	public static final String SYS_ADMIN = "SYS_ADMIN";
	// 社团管理员账号
	public static final String ORG_ADMIN = "ORG_ADMIN";
	// 社团成员帐号
	public static final String ORG_USER = "ORG_USER";

	/**
	 * ============权限操作类型=============
	 */
	// 增
	public static final String CREATE = "PERM_CREATE";
	// 系统增
	public static final String SYS_CREATE = "PERM_SYS_CREATE";
	// 查
	public static final String READ = "PERM_READ";
	// 系统查
	public static final String SYS_READ = "PERM_SYS_READ";
	// 改
	public static final String UPDATE = "PERM_UPDATE";
	// 系统改
	public static final String SYS_UPDATE = "PERM_SYS_UPDATE";
	// 删
	public static final String DELETE = "PERM_DELETE";
	// 系统删
	public static final String SYS_DELETE = "PERM_SYS_DELETE";

	/**
	 * ============账号类型=============
	 */
	// 总账号
	public static final int ALL_ACCOUNT = 0;
	// 子帐号
	public static final int SIMPLE_ACCOUNT = 1;
	// 系统管理员
	public static final int SYS_ACCOUNT = 2;

	/**
	 * ============过滤器通过的url=============
	 */
	// 登陆UI
	public static final String LOGIN_UI = "/smallming/login_ui";
	// 主页
	public static final String INDEX = "/smallming/";
	// 多媒体文件
	public static final String STYLE = "/smallming/assets";
	// js文件
	public static final String SCRIPT = "/smallming/assets";
	// 验证码
	public static final String CODE = "/smallming/code";
	// 申请账号界面
	public static final String APPLYAC = "/smallming/jsp/apply.html";
	// 申请账号请求
	public static final String APPLY = "/smallming/apply";

	/**
	 * ============机构资料是否公开=============
	 */
	// 公开
	public static final int OPEN = 1;
	// 不公开
	public static final int CLOSE = 0;

	/**
	 * ============机构资料是否被管理员通过=============
	 */
	// 通过
	public static final int PASS = 1;
	// 未通过
	public static final int NOPASS = 0;

	/**
	 * ============初始化=============
	 */
	public static final int INITALIZEINTEGER = 0;

	/**
	 * ============日期格式=============
	 */
	public static final String DOMAIN_DATE_FORMAT = "_time";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * ============性别=============
	 */
	// 男
	public static final int MAN = 0;
	// 女
	public static final int WOMAN = 1;

	/**
	 * ============排序方式=============
	 */
	// 默认，部门排序
	public static final int DEFAULT_SORT = 0;
	// 姓名排序
	public static final int NAME_SORT = 1;
	// 学院专业排序
	public static final int COLLEGE_CAMPUS_SORT = 2;

	// 总人数
	public static final int ALL_COUNT = 0;
	// 性别
	public static final int SEX_COUNT = 1;
	// 部门人数
	public static final int DEPARTMENT_COUNT = 2;
	// 职位人数
	public static final int POSITION_COUNT = 3;

	/**
	 * ============物资是否出借=============
	 */
	// 未借
	public static final int NO_LENT = 0;
	// 出借
	public static final int LENT = 1;

	/**
	 * ============是否是机构联系人=============
	 */
	// 是机构联系人
	public static final int isContact = 1;
	// 不是机构联系人
	public static final int noContact = 0;

	/**
	 * ============导出文件的后缀=============
	 */
	public static final String EXCEL = ".xls";

	/**
	 * ============消息的类型=============
	 */
	// 普通的消息
	public static final int COMMONMESSAGE = 0;
	// 系统消息
	public static final int SYSMESSAGE = 1;
	//请求
	public static final int ASK = 2;

	/**
	 * ============是否被删除=============
	 */
	// 已被删除
	public static final int ISDELETE = 1;
	// 未被删除
	public static final int NODELETE = 0;

	/**
	 * ============评论是否已读=============
	 */
	// 未读
	public static final int NOREAD = 0;
	// 已读
	public static final int ISREAD = 1;

	/**
	 * ============保存图片的文件夹=============
	 */
	public static final String PHOTO_PATH = "logo";
	
	/**
	 * ============重置的密码=============
	 */
	public static final String RESET_PW= "111111";
	
	/**
	 * ============请求结果的类型=============
	 */
	//待处理
	public static final int RESULT_ING = 0;
	//通过
	public static final int RESULT_PASS = 1;
	//未通过
	public static final int RESULT_NO_PASS = 2;
	
	/**
	 * ============是否为发送给所有用户的消息=============
	 */
	//发送给所有的用户
	public static final int IS_ALL = 1;
	//不是发送给所有用户
	public static final int NO_ALL = 0;
	
	/*
	 * ============成员在组织中的状态=============
	 */
//	/**
//	 * 正常
//	 */
//	public static final int NORMAL = 0;
//	/**
//	 * 过时，换届后过时
//	 */
//	public static final int OUT_OF_DATE = 1;
//	/**
//	 * 未加入
//	 */
//	public static final int NOT_JOIN = 2;
//	/**
//	 * 已删除
//	 */
//	public static final int FIRED = 3;
	
	/*
	 * ==============查询的类型=============
	 */
	/**
	 * 发出的
	 */
	public static final int PUBLICSHED = 0;
	/**
	 * 收到的
	 */
	public static final int RECEIVED = 1;
	
	/*
	 * =============未读消息类型=============
	 */
	/**
	 * 全部
	 */
	public static final int UNREAD_ALL = 1;
	/**
	 * 活动
	 */
	public static final int UNREAD_ACTIVITY = 2;
	/**
	 * 请假
	 */
	public static final int UNREAD_ABSENCE = 3;
	/**
	 * 工作
	 */
	public static final int UNREAD_ASSIGNMENT = 4;
	/**
	 * 通知
	 */
	public static final int UNREAD_MESSAGE = 5;
	/**
	 * 系统消息
	 */
	public static final int UNREAD_SYS_MESSAGE = 6;
	
	/*
	 * ===============物资状态=============
	 */
	/**
	 * 未借出
	 */
	public static final int MATERIAL_NORMAL = 0;
	/**
	 * 有借出
	 */
	public static final int MATERIAL_LENT = 1;
	/**
	 * 已归还
	 */
	public static final int MATERIAL_REVERTED = 2;
	/**
	 * 逾期
	 */
	public static final int MATERIAL_OUT_OF_DATE = 3;
	
	/*
	 * ===============导出Excel表格的默認標題欄==============
	 */
	/**
	 * 数据统计的默认标题栏
	 */
	public static final String[] EXCEL_DATA_STATSITC = {"姓名","部门","安排工作","接受工作","按时完成工作","逾期完成","未完成","请假"};
	/**
	 * 默认文件名
	 */
	public static final String EXCEL_DATA_STATISTIC_FILENAME = "工作数据统计表";
	
	public static final String EXCEL_DATA_ACTIVITY_FILENAME = "活动报名信息表";
	/*
	 * ================操作的类型================
	 */
	/**
	 * 添加
	 */
	public static final String OPERATE_ADD = "add";
	public static final String OPERATE_UPDATE = "update";
	public static final String OPERATE_DELETE = "delete";
	
	/*
	 * 成员状态
	 */
	/**
	 * 正常，已加入
	 */
	public static final byte MEMBER_STATE_NORMAL = 1;
	/**
	 * 邀请了，未加入
	 */
	public static final byte MEMBER_STATE_NOT_JOIN = 0;
	/**
	 * 已删除
	 */
	public static final byte MEMBER_STATE_DELETE = 2;
	/**
	 * 已申请，还未处理
	 */
	public static final byte MEMBER_STATE_APPLIED = 3;
	/**
	 * 已申请，未通过
	 */
	public static final byte MEMBER_STATE_APPLIED_FAILD = 4;
	
	/*
	 * ===============用户的状态================ 
	 */
	/**
	 * 正常
	 */
	public static final byte USER_NORMAL = 1;
	/**
	 * 被删除封号
	 */
	public static final byte USER_DELETED = 0;
	/**
	 * 被邀请，还未正式加入
	 */
	public static final byte USER_INVITED = 2;
	/**
	 * 倒序
	 */
	public static final String SORT_DESC = "desc";
	/**
	 * 正序
	 */
	public static final String SORT_ASC = "asc";
}
