package com.mengyunzhi.sampleupdatetablewithjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 数据准备
 */
@Component
public class DataInit implements ApplicationListener<ContextRefreshedEvent> {
    private final static Logger logger = LoggerFactory.getLogger(DataInit.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("删除生成的数据表，删除原视图（视图达到及时更新)");
        jdbcTemplate.execute("DROP TABLE IF EXISTS `clazz_view`");
        jdbcTemplate.execute("DROP VIEW IF EXISTS `clazz_view`");

        logger.info("创建新视图");
        String sql = "CREATE VIEW `clazz_view` AS SELECT clazz.teacher_id, \n" +
                "\tclazz.`name`, \n" +
                "\tclazz.id, \n" +
                "\tteacher.username AS teacher_username, \n" +
                "\tteacher.`name` AS teacher_name\n" +
                "FROM clazz LEFT OUTER JOIN teacher ON clazz.teacher_id = teacher.id;";
        jdbcTemplate.execute(sql);
    }
}
