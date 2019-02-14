-- script for database creation

create database it-info;
create table companies
(
  id        int auto_increment
    primary key,
  name      text          not null,
  employees int default 1 not null
);

create table customers
(
  id     int auto_increment
    primary key,
  name   text not null,
  branch text null
);

create table companies_customers
(
  id          int auto_increment
    primary key,
  company_id  int not null,
  customer_id int not null,
  constraint companies_customers_companies_id_fk
    foreign key (company_id) references companies (id),
  constraint companies_customers_customers_id_fk
    foreign key (customer_id) references customers (id)
);

create table developers
(
  id     int auto_increment
    primary key,
  name   text                    not null,
  age    int                     not null,
  sex    enum ('MALE', 'FEMALE') null,
  salary int                     null
);

create table company_developer
(
  id           int auto_increment
    primary key,
  company_id   int not null,
  developer_id int not null,
  constraint company_developer_companies_id_fk
    foreign key (company_id) references companies (id),
  constraint company_developer_developers_id_fk
    foreign key (developer_id) references developers (id)
);

create table projects
(
  id        int auto_increment
    primary key,
  name      text not null,
  startDate date not null,
  endDate   date null,
  cost      int  null
);

create table developers_projects
(
  id      int auto_increment
    primary key,
  dev_id  int not null,
  proj_id int not null,
  constraint developers_projects_developers_id_fk
    foreign key (dev_id) references developers (id),
  constraint developers_projects_projects_id_fk
    foreign key (proj_id) references projects (id)
);

create table project_company
(
  id         int auto_increment
    primary key,
  project_id int not null,
  company_id int not null,
  constraint project_company_companies_id_fk
    foreign key (company_id) references companies (id),
  constraint project_company_projects_id_fk
    foreign key (project_id) references projects (id)
);

create table project_customer
(
  id          int auto_increment
    primary key,
  project_id  int not null,
  customer_id int not null,
  constraint project_customer_customers_id_fk
    foreign key (customer_id) references customers (id),
  constraint project_customer_projects_id_fk
    foreign key (project_id) references projects (id)
);

create table skills
(
  id       int auto_increment
    primary key,
  language text not null,
  level    text not null
);

create table developer_skills
(
  id           int auto_increment
    primary key,
  skill_id     int not null,
  developer_id int not null,
  constraint developer_skills_developers_id_fk
    foreign key (developer_id) references developers (id),
  constraint developer_skills_skills_id_fk
    foreign key (skill_id) references skills (id)
);

-- script for database queries

ALTER TABLE developers ADD COLUMN salary int (11);


ALTER TABLE projects ADD COLUMN cost INT(11);


SELECT SUM(salary) AS devSalaries
FROM developers INNER JOIN developers_projects AS dp ON dp.dev_id = developers.id
WHERE dp.proj_id = 5;


SELECT SUM(salary) AS devSalaries FROM developers INNER JOIN developer_skills as ds ON ds.developer_id = developers.id
INNER JOIN skills ON ds.skill_id = skills.id WHERE skills.language = "JAVA";


SELECT projectName,  cost FROM projects ORDER BY cost;


SELECT avg(developers.salary) AS avgSalary
FROM projects
INNER JOIN developers_projects
ON projects.id = developers_projects.proj_id
INNER JOIN developers
ON developers_projects.dev_id = developers.id
WHERE projects.cost = ( SELECT min(cost)FROM projects);


SELECT projects.name AS nameP,
sum(developers.salary) AS sumSalary
FROM projects
INNER JOIN developers_projects
ON projects.id = developers_projects.proj_id
INNER JOIN developers
ON developers_projects.dev_id = developers.id
GROUP BY projects.id
ORDER BY sumSalary DESC;


