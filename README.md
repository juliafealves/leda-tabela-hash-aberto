# :hash: Tabela Hash - Endereçamento Aberto

> No endereçamento aberto todos os elementos são armazenados na própria tabela hash, isto é, não existem listas nem elementos armazenados fora da tabela, evitando assim o uso de ponteiros.
A vantagem de se utilizar endereçamento aberto é que a quantidade de memória utilizada para armazenar ponteiros é utilizada para aumentar o tamanho da tabela, possibilitando menos colisões e aumentando a velocidade de recuperação das informações.

> Para inserir um novo elemento, examinamos sucessivamente a tabela até encontrarmos um slot vazio onde possamos armazenar o elemento. Um ponto importante é que não percorremos sempre a tabela inteira, isto é, a busca depende do elemento a ser inserido.
[Fonte](http://www.lcad.icmc.usp.br/~nonato/ED/Hashing/node40.html)

Implementação com [espelhamento linear](http://www.lcad.icmc.usp.br/~nonato/ED/Hashing/node41.html) e [quadrático](http://www.lcad.icmc.usp.br/~nonato/ED/Hashing/node42.html).

Ilustração:

![Tabela Hash Fechado](hashing.png)

Explicação do roteiro: [Vídeo](https://youtu.be/Ajh1QvO2GjM)