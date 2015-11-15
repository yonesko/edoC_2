truncate table jobs_test; insert into jobs_test select * from jobs; commit;

select * from
jobs_test
;
delete jobs_test
where job_id = '101'
;
commit;

update jobs_test
set job_title = 'PRIVET'
where job_id = 'AD_PRES'
;


select * from
SYS.ALL_CONSTRAINTS
where owner = user
and table_name = 'jobs_test'
;

select SYS.DBMS_METADATA.GET_DDL('TABLE', 'jobs_test', 'HR') from dual;


