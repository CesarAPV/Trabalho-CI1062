# Trabalho Prático (20 Pontos)

## Introdução
É um jogo para jogar em duas pessoas. O objetivo é percorrer 
setores da memória de um computador infectado por vírus. O jogador
vai assumir o papel de antivírus, que deve avançar pelos setores
eliminando os vírus que encontrar, até que o setor com a fonte de
infecçção seja alcançado. Mas deve ter cuidado, pois o vírus pode
te destruir no caminho.

## Regras do Jogo
O jogo possui dois personagens principais, os **jogadores** 
podendo ser de *simples ou de suporte* e os **inimigos**
(*vírus*), sendo baseado a jogabilidade em **turnos** para 
combate. 

### Jogadores
- Cada jogador possui um valor de ataque **ATK** e um valor de 
defesa **DEF**, sendo que o poder de ataque e defesa dos jogadores
são pré-inicializados (fixo), simples (ATK/DEF - 2/6) e de suporte
(ATK/DEF - 1/7).

- Cada jogador terá uma posição POS, com duas coordenadas (X,Y).
- O jogador simples não possui habilidades especiais, já o de
suporte, possui a capacidade de recuperar 2 pontos de sua defesa
ou da defesa de outro jogador **do mesmo setor**.
- **O jogador de suporte deve sempre possuir ataque menor que o**
**jogador simples**.
- **O jogador de suporte não pode existir sem um jogador simples**
- O jogador simples pode: **movimentar, atacar e procurar**
- O jogador de suporte pode: **movimentar, atacar, procurar e**
**recuperar defesa**.

### Inimigos
- Cada inimigo possui um valor de ataque **ATK** e um valor de 
defesa **DEF**, sendo que o poder de ataque e defesa **devem**
**ser inicialmente iguais**, com valores entre 1 e 3.
- Cada inimigo possui ocupa uma posição no tabuleiro **POS**, com
duas coordenadas (X,Y).
- Os valores ATK/DEF devem ser definidos aleatoriamente, no seu 
momento de **criação**.
- Só podem assumir a ação de **atacar**.
- Os inimigos não se movimentam, não efetuam procuras ou recuperam 
defesa (somente os jogadores).

### Tabuleiro do jogo
-> Formado por um **tabuleiro 5x5** (matriz)
- Os jogadores iniciam o jogo no centro do tabuleiro POS 
"SETOR" [3,3].
- A fonte de infecção de vírus, que consiste no objetivo final,
deve ser aleatoriamente posicionada em qualquer posição do 
tabuleiro, exceto a cetral.
- Cada posição do tabuleiro é um SETOR que o jogador deve 
percorrer, até encontrar a fonte de infecção do vírus.
- Cada setor possui uma posição do tabuleiro [x,y] e quatro lados
que podem ser portas (-*-) ou paredes (---)
- Cada setor possui até 4 portas para que um jogador se movimente
entre os setores. Devem ser definidas em tempo de compilação.
- As partes externas do tabuleiro, que delimitam o quadrado, são
sempre paredes.
- Podem existir 3 tipos de Setor
* Setor Normal, não existe nenhuma restrição, toda as ações dos
jogadores P1 e P2 pode ser executadas. Os inimigos sempre recebem
o dano de um ataque e a procura pode ser efetuada normalmente.
* Setor Oculto, a restrição neste setor é que quando o jogador 
efetuar um **ataque**, o vírus pode ou não ser eliminado 
(aleatório), pois o jogador não sabe alocalizaçõ do vírus no setor.
* Setor Privado, a restrição é que os jogadores não podem executar
a ação de **prourar** no setor.

### Turno
-> O jogo deve funcionar em sistemas de turno, alternando entre 
jogadores e inimigos.
- Os jogadores possuem até 25 ciclos para encontar a fonte de 
infecção do vírus. Sendo cada ciclo composto por turnos P1, P2 e 
inimigos.
- Os jogadores iniciam no Setor Inicial (C) no turno 0 e devem
escolher uma das portas de C para se movimentar para um novo
setor. O primeiro sendo sempre P1 e depois P2.
- Quando um jogador se movimentar para um setor ainda não 
visualizado, duas ações devem ocorrer:
* As portas do tabuleiro do setor visitado se tornam visivéis.
* Podem ser criados de 1 a 3 inimigos no setor, com ATK/DEF já 
explicados. Ambos as quantidades devem ser geradas aleatoriamente.
- Após entrar no setor e gerar os inimigos, o turno inicia com o
P1, que pode executar duas ações: 
* **Atacar**, ao atacar um inimigo no setor o valor de ATK de P1 é
subtraido do valor de DEF do inimigo hitado.
* **Procurar**, sortea-se um número entre 1 a 6:
-> 
-> 
->
->
- Após as duas ações de P1, o turno de P2 é iniciado, podendo
fazer as mesmas ações de P1, além de optar por usar sua habilidade
especial no lugar das ações **atacar e procurar**. Essa 
habilidade de cura consiste em aumentar em 2 a DEF dele ou em 1 a
DEF de P1, desde que ambos estejam no mesmo setor.
- Após o fim dos turnos dos jogadores, inicia-se os do inimigos, 
onde cada inimigo ataca os jogadores (somente aqueles que 
estiverem no mesmo setor). Para cada ataque um número entre 1 a 6
é sorteado aleatoriametne, se for par o ataque é realizado, c.c 
nada acontece. 
- Após todos os inimgos do setor finalizarem seus ataques, um novo
ciclo é iniciado.
- Os jogadores só podem se movimentar para outro setor quando não
houver mais inimigos no setor onde eles se encontram.
- Setores já visitados não geram novos inimigos, porém ao passar 
por este setor, o jogador pode usar o seu turno para procurar 
itens (permitido aos jogadores P1 e P2) ou recuperar defesa 
(permitido apenas ao jogador P2).
- o jogo termina quando:
* Um jogador entrar no setor que se encontra a fonte de infecção.
Nesse caso o jogador que entrar no setor será o vencedor.
* Quando exceder 25 ciclos 
* Quando todos os jogadores forem eliminados pelos inimigos 
(DEF<=0)
* **quando p1 morrer**


## Avaliação

## Observações

## Dicas
