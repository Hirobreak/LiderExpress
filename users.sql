


CREATE USER 'Secretaria'@'localhost' IDENTIFIED BY 'passsecretaria';

use mysql;

select * from user;

GRANT select, update, insert ON *.* TO 'Secretaria'@'localhost';

Grant process on *.* to 'Secretaria'@'localhost';


CREATE USER 'Administrador'@'localhost' IDENTIFIED BY 'adminpass';

GRANT all ON *.* TO 'Administrador'@'localhost';

GRANT all ON *.* TO 'Secretaria'@'localhost';

Revoke delete on *.* from 'Secretaria'@'localhost';