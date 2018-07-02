package com.mengyunzhi.sampleupdatetablewithjpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClazzView {
    @Id
    private Long id;

    private String name;

    private String teacherName;

    private Long teacherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
