
delimiter /

create procedure crearCliente(id decimal, nombr varchar(40), ced varchar(20), empre varchar(20), rc varchar(30), tele varchar(20))
	begin 
		INSERT INTO cliente VALUES(id, nombr, ced, rc, empre, tele);
	end /

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
CREATE PROCEDURE LastIDOrden () BEGIN
	SELECT max(orden.id_orden)+1 as maxID FROM orden;
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
CREATE PROCEDURE LastIDProv () BEGIN
	SELECT max(proveedor.id_proveedor)+1 as maxID FROM proveedor;
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



/*PROCEDURES PARA CAJA*/

DELIMITER $$
CREATE PROCEDURE crearCaja(IN newID decimal, IN num VARCHAR(10), IN peso VARCHAR(10), IN estado VARCHAR(20), IN dim VARCHAR(10))
	BEGIN
	INSERT INTO caja VALUES(newID,num, peso, estado, dim);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE elimCaja(IN IDCaja decimal)
	BEGIN 
	DELETE FROM caja WHERE caja.id_caja=IDCaja;
END $$
DELIMITER ;

DELIMITER |
CREATE PROCEDURE todasCajas ()
	BEGIN
	SELECT caja.* FROM caja;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE lastIDCaja()
	BEGIN
	SELECT max(caja.id_caja)+1 as maxID FROM caja;
END |
DELIMITER ;

/*FIN PROCEDURES PARA CAJA*/

/*PROCEDURES PARA TRABAJADOR*/

DELIMITER $$
CREATE PROCEDURE crearTrabajador (IN newID DECIMAL, IN nombre VARCHAR(40), IN cedula VARCHAR(20), In puesto VARCHAR(20), IN telf VARCHAR(30), In sueldo FLOAT, IN mail VARCHAR(20))
	BEGIN
	INSERT INTO trabajador VALUES(newID, nombre, cedula, puesto, telf, sueldo, mail);
END $$
DELIMITER ;

DELIMITER |
CREATE PROCEDURE todosTrab()
	BEGIN
	SELECT trabajador.* FROM trabajador;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE lastIDTrab()
	BEGIN
	SELECT max(trabajador.id_trabajador)+1 as maxID FROM trabajador;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE takeTrabData (IN id DECIMAL) 
	BEGIN
	SELECT trabajador.* FROM trabajador WHERE trabajador.id_trabajador=id;
END |
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE modTrab (IN IDTrab DECIMAL, IN nombre VARCHAR(40), IN cedula VARCHAR(20), In puesto VARCHAR(20), IN telf VARCHAR(30), In sueldo FLOAT, IN mail VARCHAR(20))
	BEGIN
	UPDATE trabajador SET trabajador.nombre=nombre, trabajador.cedula=cedula, trabajador.puesto=puesto, trabajador.telf=telf, trabajador.sueldo=sueldo, trabajador.mail=mail WHERE trabajador.id_trabajador=IDTrab;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE elimTrab(IN IDTrab decimal)
	BEGIN 
	DELETE FROM trabajador WHERE trabajador.id_trabajador=IDTrab;
END $$
DELIMITER ;

/*FIN DE PROCEDURES PARA TRABAJADOR*/

/*PROCEDURES PARA IMPORTACION*/

DELIMITER $$
CREATE PROCEDURE crearImp (IN newID DECIMAL, IN IDTrab DECIMAL, IN IDProv DECIMAL, IN fecha DATE)
	BEGIN
	INSERT INTO importacion VALUES(newID, IDTrab, IDProv, fecha);
END $$
DELIMITER ;

DELIMITER |
CREATE PROCEDURE todasImport()
	BEGIN
	SELECT importacion.* FROM importacion;
END |
DELIMITER ;

DELIMITER |
CREATE PROCEDURE lastIDImport()
	BEGIN
	SELECT max(importacion.id_import)+1 as maxID FROM importacion;
END |
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE modImport (IN IDImport DECIMAL, IN IDTrab DECIMAL, IN IDProv DECIMAL, IN fecha DATE)
	BEGIN
	UPDATE importacion SET importacion.id_trabajador=IDTrab, importacion.id_proveedor=IDProv, importacion.fecha=fecha WHERE importacion.id_import=IDImport;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE elimImport(IN IDImport decimal)
	BEGIN 
	DELETE FROM importacion WHERE importacion.id_import=IDImport;
END $$
DELIMITER ;

/*FIN DE PROCEDURES PARA IMPORTACION*/
/*Procedures 2 febrero */

delimiter $$
create procedure searchClient(nombr varchar(40), ced varchar(20), tele varchar(20))
	begin
		if (ced="" and nombr="") then
			Select * from cliente where cliente.telf=tele;
		elseif (tele="" and nombr="") then
			Select * from cliente where cliente.cedula=ced;
		elseif (tele="" and ced="") then
			Select * from cliente where cliente.nombre=nombr;
		elseif(nombr="") then
			Select * from cliente where cliente.cedula=ced and cliente.telf=tele;
		elseif (ced="") then
			Select * from cliente where cliente.nombre=nombr and cliente.telf=tele;
		elseif (tele="") then
			Select * from cliente where cliente.nombre=nombr and cliente.cedula=ced;
		end if;
	end$$

delimiter ;


delimiter $$

create procedure searchOrden(clien varchar(40), fech date, est varchar(20), numr varchar(20))
	begin
		if (fech="0-0-0" and est="" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien;
		elseif (clien="" and est="" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.fecha=fech;
		elseif (fech="0-0-0" and clien="" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.estado=est;
		elseif (fech="0-0-0" and est="" and clien="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.num_rastreo=numr;
		elseif (fech="0-0-0" and est="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.num_rastreo=numr;
		elseif (fech="0-0-0" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.estado=est;
		elseif (est="" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.fecha=fech;
		elseif (clien="" and est="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.num_rastreo=numr and orden.fecha=fech;
		elseif (clien="" and numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.estado=est and orden.fecha=fech;
		elseif (clien="" and fech="0-0-0") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.estado=est and orden.num_rastreo=numr;
		elseif(clien="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and orden.fecha=fech and orden.estado=est and orden.num_rastreo=numr;
		elseif (numr="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.estado=est and orden.fecha=fech;
		elseif (est="") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.fecha=fech and orden.num_rastreo=numr;
		elseif (fech="0-0-0") then
			Select orden.* from orden, cliente where orden.id_orden=cliente.id_cliente and cliente.nombre=clien and orden.estado=est and orden.num_rastreo=numr;
		end if;
	end$$

delimiter ;

delimiter $$

create procedure searchMerca(style varchar(40), marc varchar(40), cantma int, cantmen int)
	begin
		if (style="%%" and marc="%%" and cantma=0) then
			Select * from mercaderia m where m.cantidad>cantmen;
		elseif (style="%%" and cantmen=0 and cantma=0) then
			Select * from mercaderia m where m.marca like marc;
		elseif (style="%%" and marc=0 and cantmen=0) then
			Select * from mercaderia m where m.cantidad<cantma;
		elseif (marc="%%" and cantmen=0 and cantma=0) then
			Select * from mercaderia m where m.estilo like style;
		elseif (style="%%" and marc="%%") then
			Select * from mercaderia m where m.cantidad>cantmen and m.cantidad<cantma;
		elseif (style="%%" and cantma=0) then
			Select * from mercaderia m where m.cantidad>cantmen and m.marca like marc;
		elseif (style="%%" and cantmen=0) then
			Select * from mercaderia m where m.cantidad<cantma and m.marca like marc;
		elseif (marc="%%" and cantma=0) then
			Select * from mercaderia m where m.cantidad>cantmen and m.estilo like style;
		elseif (marc="%%" and cantmen=0) then
			Select * from mercaderia m where m.cantidad<cantma and m.estilo like style;
		elseif (cantma=0 and cantmen=0) then
			Select * from mercaderia m where m.estilo like style and m.marca like marc;
		elseif(style="%%") then
			Select * from mercaderia m where m.cantidad>cantmen and m.cantidad<cantma and m.marca like marc;
		elseif (marc="%%") then
			Select * from mercaderia m where m.cantidad>cantmen and m.cantidad<cantma and m.estilo like style;
		elseif (cantma=0) then
			Select * from mercaderia m where m.estilo like style and m.marca like marc and m.cantidad<cantma;
		elseif (cantmen=0) then
			Select * from mercaderia m where m.estilo like style and m.marca like marc and m.cantidad>cantmen;
		end if;
	end$$

delimiter ;


delimiter $$

create procedure searchEmpaq(merca varchar(40), box varchar(20), est varchar(20))
	begin
		if (box="" and merca="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and e.estado=est;
		elseif (box="" and est="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and m.estilo=merca;
		elseif (merca="" and est="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and c.num=box;
		elseif(box="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and e.estado=est and m.estilo=est;
		elseif (merca="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and e.estado=est and c.num=box;
		elseif (est="") then
			Select e.* from empaquetado e, caja c, mercaderia m where e.id_caja=c.id_caja and m.id_merca=e.id_merca and m.estilo=est and c.num=box;
		end if;
	end$$

delimiter ;


delimiter $$

create procedure searchImpo(trab varchar(40), prov varchar(20), fech DATE)
	begin
		if (trab="" and prov="") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and i.fecha=fech;
		elseif (trab="" and fech="0-0-0") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and p.compania=prov;
		elseif (prov="" and fech="0-0-0") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and trab.cedula=trab;
		elseif(trab="") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and i.fecha=fech and p.compania=prov;
		elseif (prov="") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and i.fecha=fech and trab.cedula=trab;
		elseif (fech="") then
			Select i.* from importacion i, trabajador t, proveedor p where i.id_trabajador=t.id_trabajador and p.id_proveedor=i.id_import and p.compania=prov and trab.cedula=trab;
		end if;
	end$$

delimiter ;

/*PROCEDURES CROBBY*/

delimiter $$

create procedure buscarTrab(email varchar(20), nom varchar(40), ced varchar(20), pues varchar(20), sal varchar(10), telef varchar(30))
	begin
		Select trabajador.* from trabajador where mail like email and nombre like nom and cedula like ced and puesto like pues and sueldo like sal and telf like telef;
	end$$

delimiter ;

delimiter $$

create procedure buscarProv(com varchar(40), newRUP varchar(20), newPais varchar(20), ciud varchar(30), due varchar(20), telef varchar(20))
	begin
		Select proveedor.* from proveedor where compania like com and rup like newRUP and pais like newPais and ciudad like ciud and dueño like due and telf like telef;
	end$$

delimiter ;

delimiter $$

create procedure crearCont(id int, dim varchar(20), peso varchar(10), estado varchar(20), id_impo int)
	begin
		insert into contenedor values (id, dim, peso, estado, id_impo);
	end$$
delimiter ;

/* Factura 1*/

delimiter /

create procedure crearFac(id decimal, vp float, iv float, des float, fech date, vf float, idimpo decimal)
	begin 
		INSERT INTO factura1 VALUES(id, vp, iv, des, fech, vf, idimpo);
	end /

delimiter ;

delimiter /

create trigger factimpo after insert on importacion
	for each row begin
	declare id int;
	set id=(SELECT max(f.id_factura)+1 as maxID FROM factura1 f);
	if id is null then 
		set id=1;
	end if;
	call crearFac(id, 0, 0, 0, new.fecha, 0, new.id_import);
end/

delimiter ;

delimiter $$

create procedure editFac1(id decimal)
	begin 
		declare valorp float;
		set valorp=(Select sum(m.precio_compra*m.cantidad) from mercaderia m, empaquetado e, contenedor c, importacion i where m.id_merca=e.id_merca and e.id_contenedor=c.id_contenedor and c.id_import=i.id_import and e.id_contenedor=id);
		update factura1 set valor_p=valorp, iva=valorp*0.12, valor_final=valorp*1.12  where id_import in (select c.id_import from contenedor c where c.id_contenedor=id);
	end$$

delimiter ;

use liderexpress;



DELIMITER |
CREATE PROCEDURE lastIDfact1()
	BEGIN
	SELECT max(f.id_factura)+1 as maxID FROM factura1 f;
END |
DELIMITER ;

delimiter /

create trigger edfactimpo after insert on empaquetado
	for each row begin
	call editFac1(new.id_contenedor);
end/

delimiter ;


Select * from mercaderia m, empaquetado e, contenedor c, importacion i where m.id_merca=e.id_merca and e.id_contenedor=c.id_contenedor and c.id_import=i.id_import and e.id_contenedor=1;
call editFac1(1);
select c.id_import from contenedor c where c.id_contenedor=1;

delimiter $$

create procedure searchfact1(id decimal)
	begin 
		SELECT p.compania, f.* FROM factura1 f, importacion i, proveedor p WHERE f.id_import=id and i.id_import=f.id_import and p.id_proveedor=i.id_proveedor;
	end$$

delimiter ;

delimiter $$

create procedure factiMerca(id decimal)
	begin 
		SELECT m.estilo, m.dsc, m.cantidad, m.precio_compra FROM mercaderia m, empaquetado e, contenedor c, importacion i where m.id_merca=e.id_merca and e.id_contenedor=c.id_contenedor and c.id_import=i.id_import and i.id_import=id;
	end$$

delimiter ;
/* factura 2 */

delimiter /

create procedure crearFac2(id decimal, vp float, iv float, des float, fech date, vf float, idimpo decimal)
	begin 
		INSERT INTO factura2 VALUES(id, vp, iv, des, fech, vf, idimpo);
	end /

delimiter ;

DELIMITER |
CREATE PROCEDURE lastIDfact2()
	BEGIN
	SELECT max(f.id_factura)+1 as maxID FROM factura2 f;
END |
DELIMITER ;

delimiter /

create trigger factorden after insert on orden
	for each row begin
	declare id int;
	set id=(SELECT max(f.id_factura)+1 as maxID FROM factura2 f);
	if id is null then 
		set id=1;
	end if;
	call crearFac2(id, 0, 0, 0, new.fecha, 0, new.id_orden);
end/

delimiter ;


delimiter $$

create procedure editFac2(id decimal)
	begin 
		declare valorp float;
		set valorp=(Select sum(m.precio_venta*m.cantidad) from mercaderia m, orden o where m.id_orden=o.id_orden and o.id_orden=id);
		update factura2 f set f.valor_p=valorp, f.iva=0.12*valorp, valor_final=valorp*1.12 where f.id_orden=id;
	end$$

delimiter ;

delimiter /

create trigger edfactord after insert on mercaderia
	for each row begin
	call editFac2(new.id_orden);
end/

delimiter ;

delimiter /

create trigger edfactordup after update on mercaderia
	for each row begin
	call editFac2(new.id_orden);
end/

delimiter ;

delimiter /

create trigger edfactorddel after delete on mercaderia
	for each row begin
	call editFac2(old.id_orden);
end/

delimiter ;

delimiter $$

create procedure deletefact2(id decimal)
	begin 
		DELETE FROM factura2 WHERE factura2.id_orden=id;
	end$$

delimiter ;

delimiter /

create trigger delfactord before delete on orden
	for each row begin
	call deleteFac2(old.id_orden);
end/

delimiter ;


/* devuelve los campos a mostrar en la factura */
delimiter $$

create procedure searchfact2(id decimal)
	begin 
		SELECT c.nombre, f.* FROM factura2 f, orden o, cliente c WHERE f.id_orden=id and o.id_orden=f.id_orden and c.id_cliente=o.id_cliente;
	end$$

delimiter ;



delimiter $$

create procedure factMerca(id decimal)
	begin 
		SELECT m.estilo, m.dsc, m.cantidad, m.precio_venta FROM orden o, mercaderia m WHERE o.id_orden=id and o.id_orden=m.id_orden;
	end$$

delimiter ;

delimiter /
create procedure crearPago1(id decimal, cant decimal, tip varchar(20), id_fac decimal, fech date)
	begin 
		INSERT INTO pago1 VALUES(id, cant, tip, id_fac, fech);
	end /
delimiter ;

delimiter /
create procedure crearPago2(id decimal, cant decimal, tip varchar(20), inter float, fech date, id_fac decimal)
	begin 
		INSERT INTO pago2 VALUES(id, cant, tip, inter, fech, id_fac);
	end /
delimiter ;

delimiter $$
create procedure deletePago1(id decimal)
	begin 
		DELETE FROM pago1 WHERE pago1.id_pago=id;
	end$$
delimiter ;

delimiter $$
create procedure deletePago2(id decimal)
	begin 
		DELETE FROM pago2 WHERE pago2.id_pago=id;
	end$$
delimiter ;

DELIMITER $$
CREATE PROCEDURE modPago1 (in id decimal, in cant decimal, in tip varchar(20), in id_fac decimal, in fech date)
	BEGIN
	UPDATE pago1 SET pago1.id_pago=id, pago1.cantidad=cant, pago1.tipo=tip, pago1.id_factura=id_fac, pago1.fecha=fech WHERE pago1.id_pago=id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE modPago2 (in id decimal, in cant decimal, in tip varchar(20), in inter float, in fech date, in id_fac decimal)
	BEGIN
	UPDATE pago2 SET pago2.id_pago=id, pago2.cantidad=cant, pago2.tipo=tip, pago2.id_factura=id_fac, pago2.fecha=fech, pago2.interes=inter where pago2.id_pago=id;
END $$
DELIMITER ;

delimiter $$

create procedure maxPago1()
	begin 
		SELECT max(pago1.id_pago)+1 as maxID FROM pago1;
	end$$

delimiter ;

delimiter $$

create procedure maxPago2()
	begin 
		SELECT max(pago2.id_pago)+1 as maxID FROM pago2;
	end$$

delimiter ;

SELECT m.estilo, m.dsc, m.cantidad, m.precio_venta FROM orden o, mercaderia m WHERE o.id_orden=3 and o.id_orden=m.id_orden;

call factMerca(3);
SELECT c.nombre, f.* FROM factura2 f, orden o, cliente c WHERE f.id_orden=3 and o.id_orden=f.id_orden and c.id_cliente=o.id_cliente;

delete from contenedor where id_contenedor=2;
drop trigger factorden;
drop trigger delfactord;
call crearImp(20, 1, 1, '2013-12-12');
drop trigger edfactord;
drop procedure editFac2;
call factiMerca(21);
drop procedure factiMerca;
Select sum(m.precio_venta*m.cantidad) from mercaderia m, orden o where m.id_orden=o.id_orden and o.id_orden=3;
call editFac2(3);
update factura2 set valor_p=1 where id_orden=3;
drop trigger factimpo;
drop procedure searchfact1;
call searchfact1(21);
drop procedure editFac1;