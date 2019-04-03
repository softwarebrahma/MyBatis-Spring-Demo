
CREATE SCHEMA public
  AUTHORIZATION dcmadmin;

GRANT ALL ON SCHEMA public TO dcmadmin;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public
  IS 'standard public schema';



-- Table: posts

-- DROP TABLE posts;

CREATE TABLE posts
(
  id character varying(150) NOT NULL,
  title character varying(255),
  description text,
  creationdate date,
  author character varying(150),
  CONSTRAINT posts_id_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE posts
  OWNER TO dcmadmin;
GRANT ALL ON TABLE posts TO dcmadmin;
GRANT SELECT ON TABLE posts TO dcmuser;

-- Index: posts_id_uindex

-- DROP INDEX posts_id_uindex;

CREATE UNIQUE INDEX posts_id_uindex
  ON posts
  USING btree
  (id COLLATE pg_catalog."default");



-- Table: dept

-- DROP TABLE dept;

CREATE TABLE dept
(
  deptno integer NOT NULL,
  dname text,
  loc text,
  CONSTRAINT pk_dept PRIMARY KEY (deptno)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dept
  OWNER TO dcmadmin;

  

-- Table: emp

-- DROP TABLE emp;

CREATE TABLE emp
(
  empno integer NOT NULL,
  ename text,
  job text,
  mgr integer,
  hiredate date,
  sal integer,
  comm integer,
  deptno integer,
  CONSTRAINT pk_emp PRIMARY KEY (empno),
  CONSTRAINT fk_deptno FOREIGN KEY (deptno)
      REFERENCES dept (deptno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE emp
  OWNER TO dcmadmin;

-- Index: ename_idx

-- DROP INDEX ename_idx;

CREATE UNIQUE INDEX ename_idx
  ON emp
  USING btree
  (ename COLLATE pg_catalog."default");

