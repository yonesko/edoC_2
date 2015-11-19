truncate table jobs_test; 
insert into jobs_test(JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY)
  select JOB_ID, JOB_TITLE, MAX_SALARY, MIN_SALARY 
  from jobs; 
  commit;
  
alter table jobs_test add rowcre DATE default sysdate;
alter table jobs_test drop column rowcre;


select * from
jobs_test
order by
rowcre
;
delete JOBS_TEST
where job_id = '101'
;
commit;



select SYS.DBMS_METADATA.GET_DDL('TABLE', 'jobs_test', 'HR') from dual;

select 
LISTAGG(column_name, ', ') WITHIN GROUP (ORDER BY column_name)
from all_tab_cols
where table_name = 'JOBS_TEST'
;

