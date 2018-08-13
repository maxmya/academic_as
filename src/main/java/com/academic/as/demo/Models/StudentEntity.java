package com.academic.as.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "acadmic_as", catalog = "")
public class StudentEntity {
    private Integer academicLevel;
    private int academicId;
    private int gpa;

    @Basic
    @Column(name = "academic_level")
    public Integer getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(Integer academicLevel) {
        this.academicLevel = academicLevel;
    }

    @Id
    @Column(name = "academic_id")
    public int getAcademicId() {
        return academicId;
    }

    public void setAcademicId(int academicId) {
        this.academicId = academicId;
    }

    @Basic
    @Column(name = "gpa")
    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return academicId == that.academicId &&
                gpa == that.gpa &&
                Objects.equals(academicLevel, that.academicLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(academicLevel, academicId, gpa);
    }
}
