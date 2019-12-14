CREATE DATABASE ficha_treino;

CREATE TABLE login (id int PRIMARY KEY AUTO_INCREMENT,usuario VARCHAR(50),senha VARCHAR(50));

INSERT INTO login(usuario,senha) VALUES ('admin','123');

CREATE TABLE lista_exercicio (id int PRIMARY KEY AUTO_INCREMENT,nome_exercicio VARCHAR (50), nome_Lista VARCHAR (50), numero_series int, numero_repeticoes int,fk_usuario int (50));

Alter table lista_exercicio add constraint fk_usuario foreign key (fk_usuario) references login(id);

INSERT INTO lista_exercicio(nome_exercicio,nome_Lista,numero_series,numero_repeticoes,fk_usuario) VALUES ('Supino','Peito e Triceps',4,10,1);

INSERT INTO lista_exercicio(nome_exercicio,nome_Lista,numero_series,numero_repeticoes,fk_usuario) VALUES ('Fexao','Peito e Triceps',4,10,1);

INSERT INTO lista_exercicio(nome_exercicio,nome_Lista,numero_series,numero_repeticoes,fk_usuario) VALUES ('Lift','Quadriceps',4,13,1);

INSERT INTO lista_exercicio(nome_exercicio,nome_Lista,numero_series,numero_repeticoes,fk_usuario) VALUES ('Crucifixo','Peito e Triceps',4,12,1);