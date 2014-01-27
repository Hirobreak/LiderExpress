delimiter $$

create procedure crearCliente(id decimal, nombr varchar(40), ced varchar(20), empre varchar(20), rc varchar(30), tele varchar(20))
	begin 
		INSERT INTO cliente VALUES(id, nombr, ced, rc, empre, tele);
	end$$

delimiter ;

delimiter $$

create procedure takeClientData(id decimal)
	begin 
		SELECT * FROM cliente WHERE cliente.id_cliente=id;
	end$$

delimiter ;

delimiter $$

create procedure updateClient(id decimal, nombr varchar(40), ced varchar(20), empre varchar(20), rc varchar(30), tele varchar(20))
	begin 
		UPDATE cliente SET cliente.nombre=nombr, cliente.cedula=ced, cliente.ruc=ruc, cliente.compania=empre , cliente.telf=tele WHERE cliente.id_cliente=id;
	end$$

delimiter ;

delimiter $$

create procedure deleteClient(id decimal)
	begin 
		DELETE FROM cliente WHERE cliente.id_cliente=id;
	end$$

delimiter ;

delimiter $$

create procedure allClient()
	begin 
		SELECT * FROM cliente;
	end$$

delimiter ;

delimiter $$

create procedure maxClient()
	begin 
		SELECT max(cliente.id_cliente)+1 as maxID FROM cliente;
	end$$

delimiter ;

delimiter $$

create procedure crearMerca(id decimal, est varchar(20), marca varchar(20), descr varchar(20), com varchar(20), cant decimal(5), ori varchar(20), pven float, pcom float, idord decimal(5))
	begin 
		INSERT INTO mercaderia VALUES(id, est, marca, descr, com, cant, ori, pven, pcom, idord);
	end$$

delimiter ;

delimiter $$

create procedure takeMercaData(id decimal)
	begin 
		SELECT * FROM mercaderia WHERE mercaderia.id_merca=id;
	end$$

delimiter ;

delimiter $$

create procedure updateMerca(id decimal, est varchar(20), marca varchar(20), descr varchar(20), com varchar(20), cant decimal(5), ori varchar(20), pven float, pcom float, idord decimal(5))
	begin 
		UPDATE mercaderia SET mercaderia.estilo=est , mercaderia.marca= marca, mercaderia.dsc=descr, mercaderia.compos=com, mercaderia.cantidad=cant, mercaderia.origen=ori, mercaderia.precio_venta=pven, mercaderia.precio_compra=pcom, mercaderia.id_orden=idord WHERE mercaderia.id_merca=id;
	end$$

delimiter ;

delimiter $$

create procedure deleteMerca(id decimal)
	begin 
		DELETE FROM mercaderia WHERE mercaderia.id_merca=id;
	end$$

delimiter ;

delimiter $$

create procedure allMerca()
	begin 
		SELECT * FROM mercaderia;
	end$$

delimiter ;

delimiter $$

create procedure maxMerca()
	begin 
		SELECT max(mercaderia.id_merca)+1 as maxID FROM mercaderia;
	end$$

delimiter ;

/*PROCEDURES PARA ORDEN*/

DELIMITER |
CREATE PROCEDURE LastIDOrden (OUT maxID INTEGER) BEGIN
	SELECT max(orden.id_orden)+1 INTO maxID FROM orden;
END |
DELIMITER ;

delimiter $$
create procedure crearOrden(IN newID INTEGER,IN IDCliente INTEGER, IN pais VARCHAR(30), IN ciudad VARCHAR(30), IN fecha DATE, IN tiempo INTEGER, IN estado VARCHAR(30), IN numero INTEGER)
	begin
	INSERT INTO orden VALUES(newID,IDCliente,pais,ciudad,fecha,tiempo,estado,numero);
END $$
delimiter ;

DELIMITER | 
CREATE PROCEDURE todasOrdenes() begin
	SELECT orden.* FROM orden;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE takeOrdenData (IN id INTEGER) 
	BEGIN
	SELECT orden.* FROM orden WHERE orden.id_orden=id;
END |
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE modOrden(IN IDOrden decimal, IN IDCliente DECIMAL, IN OPais VARCHAR(20), IN OCiudad VARCHAR(20), IN fecha DATE, IN tiempo INTEGER, IN estado VARCHAR(20), IN rastreo VARCHAR(20))
	BEGIN 
	UPDATE orden SET orden.id_cliente=IDCliente, orden.pais=OPais, orden.ciudad=OCiudad, orden.fecha=fecha, orden.t_entrega=tiempo, orden.estado=estado, orden.num_rastreo=rastreo WHERE orden.id_orden=IDOrden;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE elimOrden(IN IDOrden DECIMAL)
	BEGIN 
	DELETE FROM orden WHERE orden.id_orden=IDOrden;
END $$
DELIMITER ;
/*FIN DE PROCEDURES PARA ORDEN*/

/*PROCEDURES PARA PROVEEDOR*/

DELIMITER $$
CREATE PROCEDURE crearProv(IN newID DECIMAL, IN comp VARCHAR(40), IN rup VARCHAR(20), IN pais VARCHAR(20), IN ciudad VARCHAR(30), IN dueño VARCHAR(20), IN telf VARCHAR(20))
	BEGIN
	INSERT INTO proveedor VALUES(newID,comp,rup,pais,ciudad,dueño,telf);
END $$
DELIMITER ;

DELIMITER | 
CREATE PROCEDURE todosProv() begin
	SELECT * FROM proveedor;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE LastIDProv (OUT maxID INTEGER) BEGIN
	SELECT max(proveedor.id_proveedor)+1 INTO maxID FROM proveedor;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE takeProvData (IN id DECIMAL) 
	BEGIN
	SELECT proveedor.* FROM proveedor WHERE proveedor.id_proveedor=id;
END |
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE modProv(IN id decimal, IN Com VARCHAR(40), IN RUP VARCHAR(20), IN Pais VARCHAR(20), IN Ciudad VARCHAR(30), IN Dueño VARCHAR(20), IN Telf VARCHAR(20))
	BEGIN 
	UPDATE proveedor SET proveedor.compania=Com, proveedor.rup=RUP, proveedor.pais=Pais, proveedor.ciudad=Ciudad, proveedor.dueño=Dueño, proveedor.telf=Telf WHERE proveedor.id_proveedor=id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE elimProv(IN IDProv DECIMAL)
	BEGIN 
	DELETE FROM proveedor WHERE proveedor.id_proveedor=IDProv;
END $$
DELIMITER ;
/*FIN DE PROCEDURES PARA PROVEEDOR*/