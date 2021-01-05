package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reaction")

public class Reaction {
    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Employee_id")
    private Integer emp_id;

    @Column(name = "Report_id")
    private Integer rep_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getRep_id() {
        return rep_id;
    }

    public void setRep_id(Integer rep_id) {
        this.rep_id = rep_id;
    }



}
