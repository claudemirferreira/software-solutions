Dados as Histórias: CRUD basicos do Sistema.. Exemplos de Teste
Narrativa:
Para realizar o cadastro dos dados basicos do sistema
Como um administrador do sistema
Desejo gerenciar os cadastros do sistema

Exemplos: 
|servico|          
|true|
|sistema|




@cadastro_de_servico.a
Cenário: 01 - cadastrar um servico sem o sistema de referencia
Dado que os servicos servico, as responsabilidades responsabilidade, os perfils perfis, os grupos grupo e os usuarios usuario estejam cadastrados no sistema
Quando cadastro o servico servicoSemSistema

Exemplos:
|servicoSemSistema|true||

Então o servico servicoSemSistema nao foi salvo no sistema

@cadastro_de_servico.b
Cenário: 02 - cadastrar um servico com sucesso
Dado que as acoes acao, as funcionalidades funcionalidade, os perfils perfis, as esferas esfera, orgaos orgao e os contatos contato estejam cadastrados no sistema
Quando cadastro uma funcionalidade funcionalidadeSemAcoes

Exemplos:
|servico|true|autenticator|
Então o servico foi salvo no sistema com sucesso