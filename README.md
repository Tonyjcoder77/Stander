# Code Challenge: Ganho de Capital
Para fazer o "cache" foi usado o framework Caffeine.

Caffeine é uma biblioteca de cache de alto desempenho na memória para Java que
é frequentemente integrada com aplicativos Spring Boot para melhorar o
desempenho, reduzindo a necessidade de buscar dados repetidamente de fontes
mais lentas, como bancos de dados ou APIs externas.

A segurança implementada com Oauth2 faz uso do JWT para gerar o token. Em
situações reais de ambiente de produção o token tem validade e com uma
frequencia definida ele deverá ser renovado com o auxílio do jwt, informando
o algoritmo a ser usado.
Para gerar ou renovar o token do oauth2 acesse o site: https://www.jwt.io/

A tabela do db é criada na memoria. Trata-se de bases H2. Para acessar a 
use: http://localhost:8080/h2-console/

Para calculo das distáncias entre o usuário e as agências, como as posições
são identificadas em um plano cartesiano, é usada a formulá classica para 
calculo entre dois pontos em um plano cartesiano.

distancia = RaisQuadradaDe [(x1-x2)^2 + (y1-y2)^2] 

DESCRITIVO DO CHALLENGE
OBJETIVOS:

Contexto: Criação de uma API REST em Spring Boot & OAuth2 que realizará 
cadastros de Agências em sua posição X e Y, onde o usuário ao realizar 
uma consulta deverá retornar a distância das agências cadastradas, pegando 
de um Cache ou, caso o tempo do Cache expire, retornando diretamente do 
banco e recriando um novo cache.

Criação de um END-POINT de cadastramento de uma AGÊNCIA em coordenadas X e Y

Criação de um END-POINT de pesquisa da DISTÂNCIA entre o USUÁRIO e as AGÊNCIAS 
CADASTRADAS

CADASTRAMENTO DE AGÊNCIAS:

Para cadastrar uma agência deve ser criado um POST com contexto de "/desafio/cadastrar"
Exemplo:
URL: localhost:8080/desafio/cadastrar

Passando em seu BODY um JSON, com a posição X e Y da agência.
Exemplo: Body(JSON):

{
"posX": 10,
"posY": -5
}

As agências cadastradas devem ser colocadas em um banco de dados.
O cadastramento e a consulta deverão passar por autenticação.
O Cache deverá ser renovado a cada 10 automaticamente ou após 5 minutos de uma 
consulta (END-POINT 2).

USUÁRIO E DISTÂNCIA ENTRE A AGÊNCIA:

Na procura de agência mais próxima do Usuário deve ser criado um GET com contexto
de "/desafio/distancia/",
devendo receber como parâmetro de URL a posição X e a posição Y do USUÁRIO.

Exemplo:
URL: localhost:8080/desafio/distancia?posX=10&posY=5

Ao ser executado o GET deve ser retornada a DISTÂNCIA entre o USUÁRIO e as 
AGÊNCIAS cadastradas em um JSON.
O retorno deve ser ordenado pela distância mais próxima para a mais distante.

Exemplo:

{
"AGENCIA_2": "distância = 2.2",
"AGENCIA_1": "distância = 10",
"AGENCIA_3": "distância = 37.42"
}

Para fazer o "cache" foi usado o framework Caffeine.

Caffeine é uma biblioteca de cache de alto desempenho na memória para Java que 
é frequentemente integrada com aplicativos Spring Boot para melhorar o 
desempenho, reduzindo a necessidade de buscar dados repetidamente de fontes 
mais lentas, como bancos de dados ou APIs externas.

A segurança implementada com Oauth2 faz uso do JWT para gerar o token. Em 
situações reais de ambiente de produção o token tem validade e com uma
frequencia definida ele deverá ser renovado com o auxílio do jwt, informando 
o algoritmo a ser usado.
