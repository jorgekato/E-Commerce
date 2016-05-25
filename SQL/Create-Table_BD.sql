drop table tb_produtos
drop table tb_categoria
drop table tb_artesanatos
drop table tb_estoque
drop table tb_clientes
drop table tb_logins
drop table tb_enderecos
drop table tb_personalizacao
drop table tb_pedidos cascade
drop table tb_itens_pedidos
drop table tb_status
drop table tb_troca_devolucao cascade
drop table tb_relatorios
drop table tb_creditos
--criado 28/03
--alterado 16/04 - 3 ultimos dados
create table tb_clientes(
	cli_id				serial primary key,
	cli_nome 			varchar(50),
	cli_sexo			varchar(20),
	cli_email			varchar(50) constraint Cliente_uk_email unique,
	cli_cpf				varchar(14) constraint Cliente_uk_cpf unique,--para não permitir a duplicidade de dados
	cli_flg_ativo			boolean,
	cli_dt_nascimento		date,	
	cli_dt_cadastro			date NOT NULL,
	cli_nivel			varchar(15),	
	cli_telefone			varchar(15)
);

--criado 28/03
create table tb_logins(
	log_id					serial primary key,
	log_senha				varchar(50),
	log_dt_ultimo_acesso			date,
	--log_dt_cadastro			date,--retirado em 03/05/16 por redundancia
	cli_id					int references tb_Clientes (cli_id)
);

create table tb_enderecos(
	end_id				serial primary key,	
	cli_id				int not null references tb_Clientes(cli_id),	--inserido em 02/05/2016
	end_cidade			varchar(50),
	end_estado			varchar(50),
	end_bairro			varchar(50),
	end_logradouro			varchar(50),
	end_numero			varchar(10),
	end_complemento			varchar(20),	--adicionado 04/05
	end_cep				varchar(9)
	--end_dt_cadastro			date --redundante pois ao cadastrar um cliente a data de cadastro esta incluso
);

--aterado dia 15/03/2016
create table tb_categorias(
      cat_id 				serial primary key,
      cat_nome_categoria  	varchar (50) not null,
      cat_descricao			varchar(50),
      cat_flg_ativo			boolean not null,
      cat_dt_cadastro 		date NOT NULL
);

--criado em 29/03 //alterado 02/04
create table tb_produtos(
	pro_id				serial primary key,
	pro_nome 			varchar(50),
	pro_marca			varchar(50),
	pro_modelo			varchar(50),
	pro_valor_unit		numeric(10,2),
	pro_qtde_estoque	int,
	pro_estoque_min		int,	--inserido em 02/05/2016	
	pro_qtde_max_venda	int not null,
	pro_descricao		varchar(200),
	pro_flg_ativo		boolean not null,
	pro_dt_cadastro		date NOT NULL,
	cat_id				int references tb_Categorias(cat_id)
	
);



--criado 19/03 --modificado 07/04
create table tb_artesanatos(
      art_id 			serial primary key,
      art_nome 			varchar (50) not null,
      cat_id			int references tb_Categorias (cat_id), --tem que ser o id chave estrangeira
      art_valor_unit	numeric(10,2),
      art_cores			varchar(30),
      art_descricao		varchar(250) not null,
      art_flg_ativo		boolean not null,
      art_dt_cadastro 	date NOT NULL
);

--criado 07/04
create table Personalizacao(
	pers_id			serial primary key,
	pers_descricao	varchar(50),
	art_id			int references tb_Artesanatos(art_id)
);

--alterado 09/04/16 retirado ped_num_boleto e inserido ped_total
create table tb_pedidos(
	ped_id			serial primary key,
	cli_id			int references tb_Clientes(cli_id),
	ped_forma_pgto	varchar(20),
	ped_tipo_envio	varchar(20),
	ped_dt_compra	date,
	ped_status		varchar(20),
	ped_total		numeric(7,2)
);

--alterado 10/04/16
create table tb_itens_pedidos(
	ped_id		int,
	pro_id		int,
	ite_qtde	int,
	ite_valor_unit	numeric(7,2),--para não haver atualizacoes no valor de um item comprado caso haja atualizacao no valor do item
	item_status	varchar(20),
	item_tipo	varchar(20),
	constraint Ite_Ped_pk primary key(ped_id,pro_id)
);

--criado 10/04/16
create table tb_status(
	sta_id			serial primary key,
	sta_descricao	varchar(30)
);

--criado 10/04/16
create table tb_troca_devolucao(
	td_id			serial primary key,
	ped_id			int, 
	pro_id			int,
	td_quantidade		int,
	td_dt_solicitacao	date,
	td_motivo		varchar(100),
	td_status		varchar(30),
	td_anotacao		varchar(500),
	td_acao			varchar(30),	--para saber que tipo de ação sera tomada.Ex: Reembolso, troca, credito...
	td_dt_ultima_modificacao date,	--ultima mudanca de status
	constraint fk_td_ped foreign key (ped_id) references tb_pedidos(ped_id),
	constraint fk_td_pro foreign key (pro_id) references tb_produtos(pro_id)
	
);

--criado 09/05/16
create table tb_relatorios(
	rel_id			serial primary key,
	rel_dt_registro		date,
	rel_comentario		varchar(100),
	rel_status		varchar(20),
	td_id			int,
	constraint fk_rel_td foreign key (td_id) references tb_troca_devolucao(td_id)
);

create table tb_creditos(
	cre_id			serial primary key,
	cre_codigo		varchar(20),
	cre_saldo		numeric(7,2),
	cre_dt_cadastro		date,
	cre_dt_validade		date,
	cre_flg_ativo		boolean,
	cli_id			int,
	constraint fk_cre_cli foreign key (cli_id) references tb_clientes(cli_id)


);


create table tb_historico_pedido(
	his_id			serial primary key,
	his_dt_registro		date,
	his_comentario		varchar(100),
	his_status		varchar(20),
	ped_id			int,
	constraint fk_rel_ped foreign key (ped_id) references tb_pedidos(ped_id)
);

create table tb_formas_pagamentos(
	fpg_id		serial,
	fpg_nome	varchar(30),
	constraint tb_formas_pagamentos_pk Primary key (fpg_id)
);

create table tb_pgtos_cartao_credito(
	ccr_id		serial,
	ccr_nome	varchar(100),
	ccr_numero	varchar(25), --verificar a qtde certa
	ccr_validade	date,
	ccr_seguranca	varchar(4),
	ccr_qtde_parce	int,
	ccr_bandeira	varchar(20),
	constraint ccr_id_pk primary key (ccr_id)
);