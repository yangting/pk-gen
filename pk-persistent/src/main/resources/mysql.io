CREATE TABLE IF NOT EXISTS `pk_gen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `namespace` varchar(32) NOT NULL COMMENT '命名空间',
  `key_name` varchar(32) NOT NULL COMMENT '主键的名子',
  `current_val` bigint(20) NOT NULL DEFAULT '1' COMMENT '主键当前值',
  `enable` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否可用 0=可用',
  `last_opt_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `pk_gen` ADD UNIQUE `uniq_pk_namespace_key` (`namespace`,`key_name`) comment '主键空单值唯一性';