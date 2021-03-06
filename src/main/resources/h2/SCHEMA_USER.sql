--用户认证
DROP TABLE IF EXISTS `USER_AUTH`;
CREATE TABLE `USER_AUTH` (
  `USER_ID` varchar(32) NOT NULL COMMENT '主键：用户ID',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY(USER_ID)
);

--用户详细信息
DROP TABLE IF EXISTS `USER_DETAIL`;
CREATE TABLE `USER_DETAIL` (
  `USER_ID` varchar(32) NOT NULL COMMENT '主键：用户ID',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `EMAIL` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '手机号',
   PRIMARY KEY(USER_ID)
);

--用户微信信息
DROP TABLE IF EXISTS `USER_WECHAT`;
CREATE TABLE `USER_WECHAT` (
  `USER_ID` varchar(32) NOT NULL COMMENT '主键：用户ID',
  `WECHAT_ID` varchar(50) NOT NULL COMMENT '微信openid',
   PRIMARY KEY(USER_ID)
);
