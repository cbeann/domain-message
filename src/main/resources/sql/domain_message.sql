-- ----------------------------
-- START  初始化领域、领域实体、领域数据库、领域事件
-- ----------------------------
-- ----------------------------
-- Table structure for ad_domain
-- ----------------------------
DROP TABLE IF EXISTS `ad_domain`;
CREATE TABLE `ad_domain` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modifed` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ad_domain
-- ----------------------------
INSERT INTO `ad_domain` VALUES (1, '订单中心', '订单中心', NOW(), NOW());
INSERT INTO `ad_domain` VALUES (2, '创意中心', '创意中心', NOW(), NOW());

-- ----------------------------
-- Table structure for domain_entity
-- ----------------------------
DROP TABLE IF EXISTS `domain_entity`;
CREATE TABLE `domain_entity` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `domain_id` BIGINT DEFAULT NULL,
  `entity_name` VARCHAR(255) DEFAULT NULL,
  `entity_id_name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modifed` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of domain_entity
-- ----------------------------
INSERT INTO `domain_entity` VALUES (11, 1, '订单', 'order_id', '订单实体', NOW(), NOW());
INSERT INTO `domain_entity` VALUES (22, 2, '创意', 'creative_id', '创意实体', NOW(), NOW());

-- ----------------------------
-- Table structure for domain_data_source
-- ----------------------------
DROP TABLE IF EXISTS `domain_data_source`;
CREATE TABLE `domain_data_source` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `domain_id` BIGINT DEFAULT NULL,
  `domain_entity_id` BIGINT DEFAULT NULL,
  `database_name` VARCHAR(255) DEFAULT NULL,
  `table_name` VARCHAR(255) DEFAULT NULL,
  `table_type` INT DEFAULT NULL,
  `entity_id_name_map_table_field` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modifed` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of domain_data_source
-- ----------------------------
INSERT INTO `domain_data_source` VALUES (111, 1, 11, 'domain_message', 'order', 1, 'id', '订单主表', NOW(), NOW());
INSERT INTO `domain_data_source` VALUES (112, 1, 11, 'domain_message', 'order_setting', 0, 'order_id', '订单附表', NOW(), NOW());

-- ----------------------------
-- Table structure for domain_event
-- ----------------------------
DROP TABLE IF EXISTS `domain_event`;
CREATE TABLE `domain_event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `domain_id` BIGINT DEFAULT NULL,
  `domain_entity_id` BIGINT DEFAULT NULL,
  `datasource_id` BIGINT DEFAULT NULL,
  `care_fields` VARCHAR(255) DEFAULT NULL,
  `event_name` VARCHAR(255) DEFAULT NULL,
  `event_type` VARCHAR(255) DEFAULT NULL,
  `condition_field_name` VARCHAR(255) DEFAULT NULL,
  `condition_field_value` VARCHAR(255) DEFAULT NULL,
  `output_topic` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `entity_id_name_map_table_field` VARCHAR(255) DEFAULT NULL,
  `gmt_create` DATETIME DEFAULT NULL,
  `gmt_modifed` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for domain_event_filter_setting
-- ----------------------------
DROP TABLE IF EXISTS `domain_event_filter_setting`;
CREATE TABLE `domain_event_filter_setting` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `event_id` BIGINT DEFAULT NULL,
  `filter_key` VARCHAR(255) DEFAULT NULL,
  `filter_value` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of domain_event
-- ----------------------------
INSERT INTO `domain_event` VALUES (1111, 1, 11, 111, 'id', 'ORDER_NEW', 'INSERT', NULL, NULL, 'ORDER_TOPIC', '订单创建', 'id', NOW(), NOW());
INSERT INTO `domain_event` VALUES (1112, 1, 11, 111, 'order_status', 'ORDER_STATUS_CHANGE', 'UPDATE', NULL, NULL, 'ORDER_TOPIC', '订单状态变更', 'id', NOW(), NOW());
INSERT INTO `domain_event` VALUES (1113, 1, 11, 111, 'id', 'ORDER_DELETE', 'DELETE', NULL, NULL, 'ORDER_TOPIC', '订单删除', 'id', NOW(), NOW());

-- ----------------------------
-- Records of domain_event_filter_setting
-- ----------------------------
INSERT INTO `domain_event_filter_setting` VALUES (1, 1111, 'product_id', '150001');
INSERT INTO `domain_event_filter_setting` VALUES (2, 1112, 'product_id', '150001');
INSERT INTO `domain_event_filter_setting` VALUES (3, 1113, 'product_id', '150001');


-- ----------------------------
-- ENR  初始化领域、领域实体、领域数据库、领域事件
-- ----------------------------



-- ----------------------------
-- 初始化业务数据表
-- ----------------------------
-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态',
  `product_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', 'name1', '2', '8', '150001');

-- ----------------------------
-- Table structure for order_setting
-- ----------------------------
DROP TABLE IF EXISTS `order_setting`;
CREATE TABLE `order_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `setting_key` varchar(255) DEFAULT NULL,
  `setting_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_setting
-- ----------------------------
