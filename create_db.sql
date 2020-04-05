CREATE ROLE bouncer;
CREATE USER bouncer_admin WITH
  NOSUPERUSER
  NOCREATEDB
  PASSWORD 'changeme'
  IN ROLE bouncer;

CREATE DATABASE bouncer WITH OWNER bouncer_admin;
GRANT CONNECT ON DATABASE bouncer TO bouncer;
