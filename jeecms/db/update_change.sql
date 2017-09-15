ALTER TABLE `jc_user`
ADD COLUMN `member_type` int(11) DEFAULT NULL AFTER `session_id`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `education` int(1) DEFAULT 0 COMMENT '学历' AFTER `user_signature`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `graduate` varchar(255) DEFAULT null COMMENT '毕业院校' AFTER `education`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_name` varchar(500) DEFAULT null COMMENT '公司名称' AFTER `graduate`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_type` varchar(255) DEFAULT null COMMENT '公司类别' AFTER `company_name`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_address_province` varchar(255) DEFAULT null COMMENT '公司地址-省' AFTER `company_type`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_address_city` varchar(255) DEFAULT null COMMENT '公司地址-市' AFTER `company_address_province`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_address_detail` varchar(500) DEFAULT null COMMENT '公司地址-详细' AFTER `company_address_city`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_intro` varchar(500) DEFAULT null COMMENT '公司简介' AFTER `company_address_detail`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `company_position` varchar(255) DEFAULT NULL AFTER `company_intro`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `province_name` varchar(255) DEFAULT NULL COMMENT '省名' AFTER `company_position`;

ALTER TABLE `jc_user_ext`
ADD COLUMN `city_name` varchar(255) DEFAULT NULL COMMENT '市名' AFTER `province_name`;

CREATE TABLE `jc_project_release` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `company_address_province` varchar(255) DEFAULT NULL COMMENT '公司地址-省',
  `company_address_city` varchar(255) DEFAULT NULL COMMENT '公司地址-市',
  `company_address_detail` varchar(255) DEFAULT NULL COMMENT '公司具体地址',
  `stage` tinyint(4) DEFAULT NULL COMMENT '所属阶段(0:种子期;1:成长期;2:扩张期;3:成熟期;4:Pre-IPO)',
  `industry` tinyint(11) DEFAULT NULL COMMENT '所属行业(0:互联网;1:文化传媒;2:科学研究和技术服务业;3:先进制造;4:金融业;5:新能源;6:新材料;7:教育;8:生物制药;9:节能环保;10:医疗器械;11:其他)',
  `other_industry` varchar(255) DEFAULT NULL COMMENT '其他行业',
  `team_stand` varchar(1000) DEFAULT NULL COMMENT '管理团队情况',
  `financing_amount` double(15,4) DEFAULT '0.0000' COMMENT '融资金额',
  `discuss_financing` tinyint(2) DEFAULT '0' COMMENT '是否面议(0:否;1:是)',
  `account_scale_min` double(15,2) DEFAULT '0.00' COMMENT '资金方占股比例最小值',
  `account_position_max` double(15,2) DEFAULT '0.00' COMMENT '资金方占股比例最大值',
  `invest_exit` tinyint(4) DEFAULT NULL COMMENT '投资退出方式(0:种子期;1:成长期;2:扩张期;3:成熟期;4:Pre-IPO)',
  `discuss_exit` tinyint(2) DEFAULT '0' COMMENT '是否面议(0:否;1:是)',
  `account_purpose` varchar(1000) DEFAULT NULL COMMENT '资金用途',
  `project_situation` varchar(1000) DEFAULT NULL COMMENT '项目情况介绍',
  `project_advantage` varchar(1000) DEFAULT NULL COMMENT '项目优势',
  `notes` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `check_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `check_status` tinyint(2) DEFAULT '1' COMMENT '审核状态 1待审核  2审核通过 3审核失败',
  `show_status` tinyint(2) DEFAULT NULL COMMENT '展示状态 1展示  2不展示',
  `reason` text COMMENT '审核原因',
  `user_id` int(11) DEFAULT NULL COMMENT 'USER关联ID',
  `province_name` varchar(255) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `financing_year` int(11) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

TRUNCATE TABLE jc_dictionary;
INSERT INTO `jc_dictionary` VALUES ('1', '10-20人', '10-20人', 'scale'), ('2', '20-50人', '20-50人', 'scale'), ('3', '50-10人', '50-10人', 'scale'), ('4', '100人以上', '100人以上', 'scale'), ('5', '私企', '私企', 'nature'), ('6', '股份制', '股份制', 'nature'), ('7', '国企', '国企', 'nature'), ('8', '互联网', '互联网', 'industry'), ('9', '房地产', '房地产', 'industry'), ('10', '加工制造', '加工制造', 'industry'), ('11', '服务行业', '服务行业', 'industry'), ('12', '普通会员', '1', 'recvNoticeUserType'), ('13', '投资人\r\n', '2', 'recvNoticeUserType'), ('14', '入驻企业', '3', 'recvNoticeUserType'), ('15', '外部合作机构', '4', 'recvNoticeUserType'), ('16', '医疗健康', '1', 'investFiled'), ('17', '生活消费', '2', 'investFiled'), ('18', '移动互联网', '3', 'investFiled'), ('19', '金融支付', '4', 'investFiled'), ('20', '企业服务', '5', 'investFiled'), ('21', '电子商务', '6', 'investFiled'), ('22', '硬件', '7', 'investFiled'), ('23', '文体娱乐', '8', 'investFiled'), ('24', '教育', '9', 'investFiled'), ('25', '大数据', '10', 'investFiled'), ('26', '旅游户外', '11', 'investFiled'), ('27', '环保', '12', 'investFiled'), ('28', '游戏动漫', '13', 'investFiled'), ('29', '交通出行', '14', 'investFiled'), ('30', '新能源', '15', 'investFiled'), ('31', '新技术', '16', 'investFiled'), ('32', '通信及IT', '17', 'investFiled'), ('33', '体育运动', '18', 'investFiled'), ('34', '社交', '19', 'investFiled'), ('35', '房产', '20', 'investFiled'), ('36', '人工智能', '21', 'investFiled'), ('37', '媒体资讯', '22', 'investFiled'), ('38', '新农业', '23', 'investFiled'), ('39', '营销广告', '24', 'investFiled'), ('40', '物流', '25', 'investFiled'), ('41', '餐饮', '26', 'investFiled'), ('42', '其他', '99', 'investFiled'), ('43', '种子期', '1', 'investStage'), ('44', '成长期', '2', 'investStage'), ('45', '扩张期', '3', 'investStage'), ('46', '成熟期', '4', 'investStage'), ('47', 'Pre-IPO', '5', 'investStage');
COMMIT;

CREATE TABLE `jc_activities_enroll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enroll_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `enroll_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `enroll_time` datetime DEFAULT NULL COMMENT '报名时间',
  `content_id` int(11) DEFAULT NULL COMMENT 'CONTENT关联ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8

CREATE TABLE `jc_corp` (
  `corp_id` int(11) NOT NULL AUTO_INCREMENT,
  `corp_name` varchar(100) DEFAULT NULL COMMENT '企业姓名',
  `corp_legal_person` varchar(30) DEFAULT NULL COMMENT '企业法人姓名',
  `corp_legal_person_tel` varchar(16) DEFAULT NULL COMMENT '法人联系电话',
  `corp_registered_funds` decimal(15,2) DEFAULT NULL COMMENT '注册资金',
  `corp_true_funds` decimal(15,2) DEFAULT NULL COMMENT '实到资金',
  `corp_manage_scope` varchar(60) DEFAULT NULL COMMENT '经营范围/合作机构:业态归类',
  `corp_fixed_tel` varchar(16) DEFAULT NULL COMMENT '企业固定电话',
  `corp_fax` varchar(16) DEFAULT NULL COMMENT '传真',
  `corp_homepage` varchar(200) DEFAULT NULL COMMENT '企业网站',
  `corp_wechat` varchar(50) DEFAULT NULL COMMENT '联系人微信',
  `corp_main_business` varchar(600) DEFAULT NULL COMMENT '企业主营业务范围',
  `corp_situation_business` varchar(600) DEFAULT NULL COMMENT '现阶段业务经营情况',
  `corp_plan_business` varchar(600) DEFAULT NULL COMMENT '业务开展计划/合作机构:与中心合作内容',
  `corp_chairman` varchar(30) DEFAULT NULL COMMENT '董事长姓名',
  `corp_chairman_tel` varchar(16) DEFAULT NULL COMMENT '董事长联系方式',
  `corp_general_manager` varchar(30) DEFAULT NULL COMMENT '总经理',
  `corp_general_manager_tel` varchar(16) DEFAULT NULL COMMENT '总经理联系方式',
  `corp_organization_structure` varchar(600) DEFAULT NULL COMMENT '人员架构',
  `corp_management_structure` varchar(600) DEFAULT NULL COMMENT '管理结构',
  `corp_license` varchar(600) DEFAULT NULL COMMENT '营业执照图片/合作机构:资质',
  `corp_apply_time` datetime NOT NULL COMMENT '申请入住日期',
  `corp_audit` int(1) NOT NULL DEFAULT '1' COMMENT '是否审核通过 0 未审核 1 审核通过 2 审核不通过',
  `corp_apply_username` varchar(100) NOT NULL COMMENT '用户名',
  `corp_isCooperation` tinyint(1) NOT NULL COMMENT '是否外部合作机构',
  `corp_audit_person` varchar(40) DEFAULT NULL,
  `corp_audit_time` datetime DEFAULT NULL,
  `corp_audit_reason` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`corp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='企业信息表';

CREATE TABLE `jc_corp_shareholder` (
  `shareholder_id` int(11) NOT NULL AUTO_INCREMENT,
  `shareholder_name` varchar(50) DEFAULT NULL COMMENT '股东姓名',
  `shareholder_donate` decimal(15,2) DEFAULT NULL COMMENT '投资注册金额',
  `shareholder_percent` varchar(10) DEFAULT NULL COMMENT '出资比例',
  `shareholder_paytype` varchar(20) DEFAULT NULL COMMENT '出资方式(货币/实物/无形资产)',
  `corp_id` int(11) NOT NULL,
  PRIMARY KEY (`shareholder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='公司股东表';

CREATE TABLE `jc_event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_uuid` varchar(100) DEFAULT NULL COMMENT '事件唯一uuid',
  `event_type` varchar(30) DEFAULT NULL COMMENT '要做啥',
  `event_apply_time` datetime DEFAULT NULL COMMENT '申请时间开始区域',
  `event_end_time` datetime DEFAULT NULL COMMENT '申请时间结束区域',
  `event_user_id` int(11) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='更改记录表';

DROP TABLE IF EXISTS `jc_investor_qualification`;
CREATE TABLE `jc_investor_qualification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `identity` tinyint(2) DEFAULT NULL COMMENT '1 个人投资者 2机构投资者',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `user_img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `company_post` varchar(255) DEFAULT NULL COMMENT '公司职位',
  `company_address_province` varchar(255) DEFAULT NULL COMMENT '省',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省名 ',
  `company_address_city` varchar(255) DEFAULT NULL COMMENT '市',
  `city_name` varchar(255) DEFAULT NULL COMMENT '市名',
  `company_address_detail` varchar(255) DEFAULT NULL COMMENT '公司详细地址',
  `idcard_number` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `wechat_number` varchar(255) DEFAULT NULL COMMENT ' 微信号',
  `invest_stage` varchar(255) DEFAULT NULL COMMENT '投资阶段',
  `intention_industry` varchar(255) DEFAULT NULL COMMENT '意向投资行业',
  `invest_min` double(11,4) DEFAULT NULL COMMENT '单个项投资额度',
  `invest_max` double(11,4) DEFAULT NULL COMMENT '单个项投资额度',
  `hold_stock_ratio_min` double(11,4) DEFAULT NULL COMMENT '单个项目参股比例',
  `hold_stock_ratio_max` double(11,4) DEFAULT NULL COMMENT '单个项目参股比例',
  `success_case` text COMMENT '成功案例',
  `comment` text COMMENT '投资者成功投资项项目',
  `check_status` tinyint(2) DEFAULT '1' COMMENT '审核状态 1待审核  2审核通过 3审核失败',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `check_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `reason` text COMMENT '原因',
  PRIMARY KEY (`id`),
  KEY `FK_User` (`user_id`) USING BTREE,
  CONSTRAINT `jc_investor_qualification_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `jc_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jc_release_transfer`;
CREATE TABLE `jc_release_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '发布转让id',
  `user_id` int(11) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `company_address_province` varchar(255) DEFAULT NULL COMMENT '省',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省名',
  `company_address_city` varchar(255) DEFAULT NULL COMMENT '市',
  `city_name` varchar(255) DEFAULT NULL COMMENT '市名',
  `company_address_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `industry` varchar(255) DEFAULT NULL COMMENT '领域',
  `stage` varchar(255) DEFAULT NULL COMMENT '阶段',
  `project_describe` text COMMENT '项目描述',
  `evaluation` double(11,4) DEFAULT NULL COMMENT '估价',
  `transfer_stock_ratio` double(11,4) DEFAULT NULL COMMENT '转让股份份额',
  `hold_stock_ratio` double(11,4) DEFAULT NULL COMMENT '股权占比',
  `remark` text COMMENT '备注',
  `check_status` tinyint(4) DEFAULT NULL COMMENT '审核状态 1待审核 2审核通过 2审核失败',
  `show_status` tinyint(4) DEFAULT NULL COMMENT '展示状态 1展示  2不展示',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `reason` text COMMENT '审核原因',
  `check_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jc_follow_project`;
CREATE TABLE `jc_follow_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注项目信息',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `tag` tinyint(4) DEFAULT NULL COMMENT '1 发布的项目 2转让的项目',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jc_sys_notice`;
CREATE TABLE `jc_sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统通知',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送人',
  `recv_user_type` varchar(255) DEFAULT NULL COMMENT '接收人用户类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jc_user_notice`;
CREATE TABLE `jc_user_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-消息通知关系表id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `result` tinyint(2) DEFAULT NULL COMMENT '2审核通过 3审核失败',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `read_status` tinyint(2) DEFAULT NULL COMMENT '1 未读 2 已读',
  `tag` tinyint(2) DEFAULT NULL COMMENT '分类 0系统通知 1投资认证审核 2项目发布转让审核',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `jc_message_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_code` varchar(100) DEFAULT NULL COMMENT '事件唯一uuid',
  `message_type` varchar(30) DEFAULT NULL COMMENT '要做啥',
  `message_apply_time` datetime DEFAULT NULL COMMENT '申请时间开始区域',
  `message_end_time` datetime DEFAULT NULL COMMENT '申请时间结束区域',
  `user_name` varchar(20) NOT NULL COMMENT 'username',
  `reserve_one` varchar(50) NOT NULL COMMENT '保留字段1',
  `reserve_two` varchar(50) NOT NULL COMMENT '保留字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='更改记录表';



