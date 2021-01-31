package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAllReactions",
            query = "SELECT react FROM Reaction AS react ORDER BY react.id DESC"
            ),
    @NamedQuery(
            name = "getReactionsCount",
            query = "SELECT COUNT(react) FROM Reaction AS react WHERE react.rep_id = :rep_id"
            )
})
@Table(name = "reactions")

public class Reaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Employee_id", nullable = false)
    private Integer emp_id;

    @Column(name = "Report_id", nullable = false, unique = true)
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
