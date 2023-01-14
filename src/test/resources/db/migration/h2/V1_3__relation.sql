ALTER TABLE employee
ADD department_id BIGINT;

UPDATE employee SET department_id = 1; --pamiętaj, że department o id=1 musi istnieć
ALTER TABLE employee
ALTER department_id SET NOT NULL;

ALTER TABLE employee
ADD FOREIGN KEY (department_id) REFERENCES department(id);