CREATE TYPE designatioon as ENUM (
    'Manager',
    'Developer',
    'QA Tester'
);

CREATE TABLE Department(
    dept_id bigserial primary key,
    dept_name varchar(50) not null
);

CREATE TABLE Employee(
    emp_id bigserial primary key,
    emp_name varchar(50) not null,
    emp_dob timestamp not null,
    emp_email varchar(64) not null,
    emp_salary int not null,
    emp_designation designatioon not null,
    emp_manager int null,
    emp_dept int not null,
    constraint emp_dept_fk foreign key (emp_dept) references Department(dept_id),
    constraint emp_emp_fk foreign key (emp_manager) references Employee(emp_id)
);