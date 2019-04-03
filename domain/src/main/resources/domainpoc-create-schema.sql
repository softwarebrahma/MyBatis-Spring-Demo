
CREATE SCHEMA public
  AUTHORIZATION dcmadmin;

GRANT ALL ON SCHEMA public TO dcmadmin;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public
  IS 'standard public schema';



-- Table: posts

-- DROP TABLE posts;

CREATE TABLE public.posts
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
ALTER TABLE public.posts
  OWNER TO dcmadmin;
GRANT ALL ON TABLE public.posts TO dcmadmin;
GRANT SELECT ON TABLE public.posts TO public;

-- Index: posts_id_uindex

-- DROP INDEX posts_id_uindex;

CREATE UNIQUE INDEX posts_id_uindex
  ON public.posts
  USING btree
  (id COLLATE pg_catalog."default");



-- Table: dept

-- DROP TABLE dept;

CREATE TABLE public.dept
(
  deptno integer NOT NULL,
  dname text,
  loc text,
  CONSTRAINT pk_dept PRIMARY KEY (deptno)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.dept
  OWNER TO dcmadmin;
GRANT ALL ON TABLE public.dept TO dcmadmin;
GRANT SELECT ON TABLE public.dept TO public;

  

-- Table: emp

-- DROP TABLE emp;

CREATE TABLE public.emp
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
ALTER TABLE public.emp
  OWNER TO dcmadmin;
GRANT ALL ON TABLE public.emp TO dcmadmin;
GRANT SELECT ON TABLE public.emp TO public;

-- Index: ename_idx

-- DROP INDEX ename_idx;

CREATE UNIQUE INDEX ename_idx
  ON emp
  USING btree
  (ename COLLATE pg_catalog."default");

