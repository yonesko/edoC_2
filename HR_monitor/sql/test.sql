truncate table jobs_test; 
insert into jobs_test(JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY)
  select JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY 
  from jobs; 
  commit;
  
  
  
alter table JOBS_TEST add rowcre DATE default sysdate;
alter table jobs_test drop column rowcre;

alter table jobs_test add CONSTRAINT jobs_test_PK PRIMARY KEY (JOB_ID) ;

select 
--'crs.add((ArrayList<String>)Arrays.asList("'||JOB_ID||'"', ',"'||JOB_TITLE||'"', ',"'||MIN_SALARY||'"', ',"'||MAX_SALARY||'"));'
*
from
jobs_test
;
delete JOBS_TEST
where job_id = '101'
;
commit;

select * from user_objects
;


select SYS.DBMS_METADATA.GET_DDL('TABLE', 'JOBS_TEST', 'HR') from dual;

select 
LISTAGG(column_name, ', ') WITHIN GROUP (ORDER BY column_name)
from all_tab_cols
where table_name = 'JOBS_TEST'
;

select HIBERNATE_SEQUENCE.currval from dual;

