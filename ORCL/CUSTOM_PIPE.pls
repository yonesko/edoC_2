create or replace package custom_pipe is
 
 -- Author  : USER
 -- Purpose : �������� � pipe
 -- ��������� ��������� ����� pipe
 procedure SendMessage( psPipe    in varchar2
                       ,psMessage in varchar2
                      );
 
 -- �������� ��������� ����� pipe
 function ReceiveMessage( psPipe    in  varchar2
                         ,psKey     in  varchar2
                         ,psMessage out varchar2
                        ) return integer;
 
end custom_pipe;