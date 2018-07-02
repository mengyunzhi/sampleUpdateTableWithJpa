package com.mengyunzhi.sampleupdatetablewithjpa;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * 注意：此测试涉及到视图，不能使用事务注解。
 * 如果使用事物，则查询视图时，将得到空值
 * panjie
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClazzViewRepositoryTest {
    private final static Logger logger = LoggerFactory.getLogger(ClazzViewRepositoryTest.class);
    @Autowired TeacherRepository teacherRepository;
    @Autowired ClazzRepository clazzRepository;
    @Autowired ClazzViewRepository clazzViewRepository;
    @Test
    public void findById() {
        logger.info("实例化教师");
        Teacher teacher = new Teacher();
        teacher.setName("测试教师");
        teacher.setUsername("测试用户名");
        teacherRepository.save(teacher);

        logger.info("实例化班级");
        Clazz clazz = new Clazz();
        clazz.setName("班级名称");
        clazz.setTeacher(teacher);
        clazzRepository.save(clazz);

        logger.info("查询视图");
        Optional<ClazzView> clazzViewOptional = clazzViewRepository.findById(clazz.getId());
        ClazzView clazzView = clazzViewOptional.get();
        Assertions.assertThat(clazzView.getName()).isEqualTo("班级名称");
        Assertions.assertThat(clazzView.getTeacherId()).isEqualTo(teacher.getId());
        Assertions.assertThat(clazzView.getTeacherName()).isEqualTo("测试教师");

        logger.info("恢复数据");
        clazzRepository.delete(clazz);
        teacherRepository.delete(teacher);
    }
}