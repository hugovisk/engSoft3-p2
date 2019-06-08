# Engenharia de Softaware - P2

## Objetivo

Ler um arquivo, extraído do SO Linux distro Ubuntu, que possui 1.000.000 de registros 
com os campos de usuário (8 possíveis), PID (número do processo), quantidade de 
CPU gasta, porcentagem de memória gasta e tempo gasto.

Criar uma aplicação eficiente que:
- Cria 8 arquivos, um para cada user, contendo os processos executados por eles;
- Exibe em um arquivo, a média, em segundos, de tempo gasto no total e por usuário;
- Exibe em um arquivo, a média de memória e cpu gasto por todos e por usuário.
- Exibe em um arquivo, apenas o usuário e o PID

## Padrao de Projeto

Foi utilizado o padrao de projeto **Master/Worker**, com a intencao dividir o processamento
do arquivo em diferemntes treads com a intecao de melhorar o aproveitamento da cpu.

## Descricao da aplicacao
Utilizada a linguagem de programacao JAVA.  
  
  A classe **Master** contem o main() e inicia a aplicacao. Nesta classe o arquivo p7 e' importado
tendo cada registro armazenado em uma uma ArrayList contendo seus campos em uma posicao e armazenado 
em uma ArrayList principal.
Nesta classe tambem sao definidos quantos Workers serao criados e feita a divisao de trabalho, apos 
a devolucao dos trabalhos de cada Worker os resultados referentes a separacao dos processos por usuario
sao concatenados, os resultados das medias recebidas sao tratados para gerar as medias totais.
Sao gerados os arquivos de saida contendo os dados solicitados nos objetivos.


  A classe **Worker** recebe a ArrayList principal e o intervalo ao qual ela fara o processamento e retorna 
uma ArrayList contendo os dados processados
  
  A classe **Averages** da apoio as outras duas classes para o calculo das medias, possui tres assinaturas de 
  construtor uma para medias por usuario, para media total e para as medias agregadas.
  
  ## Resultados da aplicaco
  #### Informacoes do ambiente de avaliacao
  - SO: UBUNTU 19.04
  - JAVA: openjdk version "11.0.3" 2019-04-16
  - CPU: Intel(R) Core(TM) i7-2600 CPU @ 3.40GHz
    - CORES: 4
    - TREADS: 8
  - MEM: 8GB DDR3 1333 MHz
  - I/O: SSD
  
  ### Tempos de processamento vs quantidade de workers
  
| Qtd. Workers  | Tempo de Processamento (ms)|
| ------------- |:--------------------------:|
| 1             | 688                        |
| 2             | 591                        |
| 3             | 561                        |
| 4             | 475                        |
| 5             | 509                        |
| 6             | 543                        |
| 7             | 436                        |
| 8             | 424                        |
| 9             | 392                        |
| 10            | 470                        |
| 15            | 496                        |
| 20            | 467                        |
| 50            | 498                        |
| 100           | 555                        |
| 1000          | 725                        |


