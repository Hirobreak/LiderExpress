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