-- 先于hibernate执行
-- 重写 ; 为 // ，在spring中，注释掉下面一行，应该我们在配置文件中的 separator: // 便是起的该作用
-- DELIMITER //
-- 如果存在函数，则先删除
DROP PROCEDURE IF EXISTS `FUN20180628` //
-- 定义函数FUN20180628
CREATE PROCEDURE `FUN20180628` ()
	BEGIN
		DECLARE hasDataTable INT;
		SELECT count(*) INTO hasDataTable FROM information_schema.tables WHERE (table_schema = 'sampleUpdateJpa') AND (table_name= 'version');
		IF hasDataTable = 0 THEN
			CREATE TABLE `sampleUpdateJpa`.`version` (
				`version` float NOT NULL COMMENT '版本号',
				PRIMARY KEY (`version`)
			) COMMENT='版本号';
			insert into `sampleUpdateJpa`.`version` ( `version`) values ( '1.1');
		END IF;
	END
//

-- 调用函数
CALL FUN20180628() //
-- 恢复重写的;，以免影响其它的function
-- DELIMITER ;