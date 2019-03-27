# Você Aluga - Grupo 1
Projeto de Fundamentos de Engenharia de Software - 2019.1

### Como configurar o BD

1. Instale o MySQL e o phpMyAdmin (no windows, baixe o xampp)
2. Ligue os servidores Apache e MySQL
3. Entre no phpMyAdmin e vá na aba "SQL"
4. Copie o código do arquivo *db_script.sql* e cole no campo de texto
5. Execute e o Banco de Dados estará preparado
6. Vá no arquivo *database/ConexaoBanco.java* e edite a 19ª linha de acordo com a configuração do MySQL: 

`return DriverManager.getConnection("jdbc:mysql://dominio:porta/vocealuga?useTimezone=true&serverTimezone=UTC", "usuario", "senha");`

Obs: os valores padrões para rodar localmente em um banco com usuário root e sem senha já estão setados por padrão.

Obs2: o usuário e senha padrão para login são 'funcionario.teste' e 'senha123', ambos sem aspas.

### Como fazer uma consulta ao BD

[Tutorial Caelum](https://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/)

### Como instalar o WindowBuilder

1. Vá no menu "Help" do Eclipse
2. Escolha "Eclipse Marketplace..."
3. Na caixa de pesquisa digite 'windowbuilder' e instale a versão 1.9.1 (ou mais recente)

### Como criar abas de navegação

1. Clique no botão novo na barra do Eclipse e escolha "Others..."
2. Escolha JPanel dentro de Window Builder > Swing Designer
3. Edite a tela no editor de Design (botão "Design" logo abaixo do editor de código, caso o botão não aparece clique com o botão direito no arquivo .java e escolha Open With > WindowBuilder Editor)
4. Na classe Home, ao final da função initialize(), instancie sua nova tela e adicione ao tabbedPane, ex.:
`
    JPanel tela = new Tela();
    tabbedPane.add("Título", null, tela, null);
`
