INSERT INTO `TASK` (`TASK_ID`, `USER_ID`, `STATUS`, `CONTENT`, `CREATE_TIME`, `DEADLINE_TIME`) VALUES
('1', '84d5b74b56a7479f9f4bbbf64fdf0831', '0', 'OOM与性能优化学习', '2020-10-13 14:08:06', '2020-11-15 23:59:59'),
('2', '84d5b74b56a7479f9f4bbbf64fdf0831', '0', 'Docker学习', '2020-10-18 18:14:46', '2020-11-18 23:59:59'),
('3', '84d5b74b56a7479f9f4bbbf64fdf0831', '1', 'jvm学习', '2020-10-22 12:45:38', '2020-11-22 23:59:59');

INSERT INTO `TASK_STEP` (`TASK_STEP_ID`, `TASK_ID`, `STATUS`, `CONTENT`) VALUES
('11', '1', '1', '阅读OOM排查分析实践第一章'),
('12', '1', '1', '阅读OOM排查分析实践第二章'),
('14', '1', '1', 'OOM排查分析实践练习');
