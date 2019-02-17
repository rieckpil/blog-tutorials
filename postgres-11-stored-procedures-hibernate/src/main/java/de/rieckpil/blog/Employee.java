package de.rieckpil.blog;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ParameterMode;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@NamedStoredProcedureQuery(name = "raiseWage", procedureName = "p_raise_wage_employee_older_than", parameters = {
		@StoredProcedureParameter(name = "operating_years", type = Integer.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "raise", type = Integer.class, mode = ParameterMode.IN) })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate entranceDate;

	@Column(nullable = false)
	private Integer wage;

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

	public LocalDate getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(LocalDate entranceDate) {
		this.entranceDate = entranceDate;
	}

	public Integer getWage() {
		return wage;
	}

	public void setWage(Integer wage) {
		this.wage = wage;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", entranceDate=" + entranceDate + ", wage=" + wage + "]";
	}

}
