CREATE TABLE employees (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	entrance_date DATE NOT NULL,
	wage INT NOT NULL
);

INSERT INTO employees (name, entrance_date, wage) VALUES ('Duke', '1996-01-01', 1000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Tom', '1996-01-01', 7000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Philip', '1995-09-13', 5000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('July', '1985-12-01', 1000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Mike', '1990-08-12', 4000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Anna', '1996-05-24', 1000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Fred', '1996-07-09', 3000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Andy', '1998-12-12', 1000);
INSERT INTO employees (name, entrance_date, wage) VALUES ('Rob', '1980-06-02', 2000);	  

CREATE OR REPLACE PROCEDURE p_raise_wage_employee_older_than(operating_years int, raise int)
AS $$
	BEGIN
	   UPDATE employees
       SET wage = wage + raise 
       WHERE EXTRACT(year FROM age(entrance_date)) >= operating_years;
	END $$
LANGUAGE plpgsql;