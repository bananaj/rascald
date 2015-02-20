create table TC_USER
(
  id varchar(32),
  username varchar(32),
  password varchar(32),
  name varchar(32),
  email varchar(64)
);

create table TC_PROJECT
(
  id varchar(32),
  name varchar(32),
  description varchar(1024),
  owner_user_id varchar(32)
);

create table TC_PROJECT_USER
(
  id varchar(32),
  project_id varchar(32),
  user_id varchar(32)
);

create table TC_VERSION
(
  id varchar(32),
  product_id varchar(32),
  name varchar(32),
);

create table TC_CASE
(
  id varchar(32),
  project_id varchar(32),
  display_id varchar(16),
  title varchar(64),
);

create table TC_CASE_INFO
(
  id varchar(32),
  case_id varchar(32),
  summary varchar(1024),
  preconditions varchar(1024)
);

create table TC_STEP
(
  id varchar(32),
  case_info_id varchar(32),
  sort_order int,
  description varchar(1024),
  expected varchar(1024)
);

create table TC_GROUP
(
  id varchar(32),
  parent_group_id varchar(32),
  title varchar(64),
  summary varchar(1024)
);


create table TC_PLAN
(
  id varchar(32),
  title varchar(64),
  summary varchar(1024)
);

create table TC_EXEC
(
  id varchar(32),
  case_info_id varchar(32),
  result_cd char(1),
  status_cd char(1),
  user_id varchar(32),
  dt timestamp,
  comments varchar(1024),
  plan_id varchar(32)
);

create table TC_EXEC_STEP
(
  id varchar(32),
  exec_id varchar(32),
  step_id varchar(32),
  result_cd char(1),
  user_id varchar(32),
  dt timestamp,
  comments varchar(1024)
);



insert into tc_user (id, username, password) values ('tcmmanager', 'tcmmanager', 'tcmmanager');

insert into TC_PROJECT(id, name, description, owner_user_id) values ('defaultproject', 'default', 'Default Project', 'tcmmanager');