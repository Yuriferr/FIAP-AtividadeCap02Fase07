# language: pt
Funcionalidade: Cadastro de nova coleta
  Como usuário da API
  Quero cadastrar uma nova coleta
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de coleta
    Dado que eu tenha os seguintes dados da coleta:
      | campo           | valor                 |
      | endereco        | Rua das Flores, 123   |
      | quantidadeResiduos | 10                |
    Quando eu enviar a requisição para o endpoint "/api/coletas" de cadastro de coletas
    Então o status code da resposta deve ser 201

  Cenário: Cadastro de coleta com campo obrigatório ausente
    Dado que eu tenha os seguintes dados da coleta:
      | campo              | valor             |
      | endereco           |                   |
      | quantidadeResiduos | 5                 |
    Quando eu enviar a requisição para o endpoint "/api/coletas" de cadastro de coletas
    Então o status code da resposta deve ser 400
    E a mensagem de erro deve ser "Campo 'endereco' é obrigatório."

  Cenário: Cadastro de coleta com quantidade de resíduos inválida
    Dado que eu tenha os seguintes dados da coleta:
      | campo              | valor            |
      | endereco           | Rua das Rosas, 456 |
      | quantidadeResiduos | -5              |
    Quando eu enviar a requisição para o endpoint "/api/coletas" de cadastro de coletas
    Então o status code da resposta deve ser 400
    E a mensagem de erro deve ser "Quantidade de resíduos deve ser um valor positivo."
