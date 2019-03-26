# Você Aluga - Grupo 1
Projeto de Fundamentos de Engenharia de Software - 2019.1

### Como configurar o Banco de Dados

1. Instale o MySQL e o phpMyAdmin (no windows, baixe o xampp)
2. Ligue os servidores Apache e MySQL
3. Entre no phpMyAdmin e vá na aba "SQL"
4. Copie o código do arquivo *db_script.sql* e cole no campo de texto
5. Execute e o Banco de Dados estará preparado
6. Vá no arquivo *database/ConexaoBanco.java* e edite a 19ª linha de acordo com a configuração do MySQL: 

`return DriverManager.getConnection("jdbc:mysql://dominio:porta/vocealuga?useTimezone=true&serverTimezone=UTC", "usuario", "senha");`

Obs: os valores padrões para rodar localmente em um banco com usuário root e sem senha já estão setados por padrão.

Obs2: o usuário e senha padrão para login são 'funcionario.teste' e 'senha123', ambos sem aspas.
