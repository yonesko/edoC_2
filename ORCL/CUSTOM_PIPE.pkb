create or replace package body custom_pipe is
 
 procedure SendMessage( psPipe    in varchar2
                       ,psMessage in varchar2
                      )
 is
   i integer;
 begin
   dbms_pipe.pack_message(psMessage);
   i := dbms_pipe.send_message(psPipe, 30);
   if i != 0 then
     raise_application_error(-20101, 'Ошибка при посылке сообщения клиенту!');
   end if;
 end SendMessage;
 
 function ReceiveMessage( psPipe    in  varchar2
                         ,psKey     in  varchar2
                         ,psMessage out varchar2
                        ) return integer
 is
   call_stat   integer := 0;
   next_item   integer := 0;
   temp_varchar2  VARCHAR2(2000);
   temp_date      DATE;
   temp_number    NUMBER;
   temp_rowid     ROWID;
   temp_raw       RAW(2000);
   vnResult integer := 0;
 begin
   psMessage := '';
   call_stat := DBMS_PIPE.RECEIVE_MESSAGE(psPipe, 0);
   if (call_stat = 0) then
     next_item := DBMS_PIPE.NEXT_ITEM_TYPE;
     while next_item > 0 loop
       if next_item = 9 then
         DBMS_PIPE.UNPACK_MESSAGE(temp_varchar2);
       elsif next_item = 6 then
         DBMS_PIPE.UNPACK_MESSAGE(temp_number);
         temp_varchar2 := TO_CHAR(temp_number);
       elsif next_item = 11 then
         DBMS_PIPE.UNPACK_MESSAGE_ROWID(temp_rowid);
         temp_varchar2 := ROWIDTOCHAR(temp_rowid);
       elsif next_item = 12 then
         DBMS_PIPE.UNPACK_MESSAGE(temp_date);
         temp_varchar2 := TO_CHAR(temp_date,'DD.MM.YYYY HH24:MI:SS');
       elsif next_item = 23 then
         DBMS_PIPE.UNPACK_MESSAGE_RAW(temp_raw);
         temp_varchar2 := RAWTOHEX(temp_raw);
       else
         temp_varchar2 := '';
--          temp_varchar2 := 'Не верный тип элемента: '||TO_CHAR(next_item); 
       end if;
       psMessage := substr(psMessage || temp_varchar2, 1, 4000);
       next_item := DBMS_PIPE.NEXT_ITEM_TYPE;
       if (upper(temp_varchar2) = upper(psKey) ) then
         vnResult := 1;
         exit;
       end if;
     end loop;
   elsif (call_stat = 1) then
     null;
   elsif (call_stat in (2,3) ) then
     raise_application_error(-20100, 'Ошибка при получении сообщения!');
   end if;
   return(vnResult);
 end ReceiveMessage;
 
end custom_pipe;