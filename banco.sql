CREATE TABLE CLIENTE(
    cli_cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    cli_nome VARCHAR(45) NOT NULL
);
CREATE TABLE CONTA(
    con_numero VARCHAR(9) PRIMARY KEY NOT NULL,
    con_saldo DOUBLE NOT NULL,
    con_data DATE NOT NULL,
    con_status BOOLEAN NOT NULl,
    con_cli_cpf VARCHAR(11) NOT NULL,
    FOREIGN KEY (con_cli_cpf) REFERENCES CLIENTE(cli_cpf)
)