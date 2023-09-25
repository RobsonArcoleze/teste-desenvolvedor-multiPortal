CREATE TABLE IF NOT EXISTS t_cadastro_pessoa (
         id SERIAL PRIMARY KEY,
         nome VARCHAR(255),
         ultimo_nome VARCHAR(255),
         email VARCHAR(255),
         sexo VARCHAR(10),
         ip_acesso VARCHAR(15),
         idade INTEGER,
         nascimento DATE
);