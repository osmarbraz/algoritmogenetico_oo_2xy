# Algoritmo Genético Orientado a Objetos para a Função \(f(x,y)=2x+y\)

## 1. Descrição do projeto

Este projeto implementa um algoritmo genético em Java utilizando programação orientada a objetos (POO) para buscar cromossomos com maior valor para a função:

\[
f(x,y)=2x+y
\]

A solução é representada por um cromossomo binário de 8 genes. Os quatro primeiros bits são utilizados para representar \(x\), e os quatro últimos bits representam \(y\). A população evolui por meio de seleção por roleta, crossover de um ponto e mutação.

O programa apresenta as estatísticas da população inicial e das gerações seguintes. Ao final, exibe o melhor cromossomo da última população, incluindo sua representação binária, os valores de \(x\) e \(y\) calculados pelo código e a avaliação da função.

## 2. Objetivos

- Aplicar os conceitos de algoritmos genéticos na otimização de uma função matemática.
- Organizar a implementação com classes e objetos.
- Representar duas variáveis, \(x\) e \(y\), em um único cromossomo binário.
- Inicializar uma população aleatória.
- Calcular a aptidão dos cromossomos por meio de \(f(x,y)=2x+y\).
- Selecionar progenitores por roleta.
- Gerar descendentes por crossover de um ponto.
- Aplicar mutação aos genes.
- Acompanhar as estatísticas das gerações e identificar o melhor cromossomo final.

## 3. Organização orientada a objetos

O projeto é composto por cinco classes:

| Classe | Responsabilidade |
|---|---|
| `Principal` | Define os parâmetros da execução e inicia o algoritmo genético. |
| `AlgoritmoGenetico` | Coordena a criação, avaliação e evolução das populações. |
| `Populacao` | Gerencia os cromossomos e implementa seleção por roleta, crossover, geração de descendentes e apresentação de estatísticas. |
| `Cromossomo` | Representa a solução binária, extrai os valores de \(x\) e \(y\), calcula a avaliação e realiza mutação. |
| `Gene` | Representa um alelo binário e permite inverter seu valor entre `0` e `1`. |

### 3.1. Relacionamento entre as classes

`Principal` instancia `AlgoritmoGenetico`; este gerencia objetos `Populacao`; cada população contém objetos `Cromossomo`; e cada cromossomo contém objetos `Gene`. Essa divisão separa as responsabilidades do algoritmo e facilita o entendimento do código.

## 4. Representação do cromossomo

O tamanho configurado do cromossomo é de 8 bits. No método `getAvaliacao()` da classe `Cromossomo`, a sequência é dividida em duas partes:

| Posição na sequência | Variável | Conversão realizada em `getAvaliacao()` |
|---|---|---|
| Bits 1 a 4 (índices 0 a 3) | \(x\) | `converteBinarioParaDecimal(0, 4)` |
| Bits 5 a 8 (índices 4 a 7) | \(y\) | `converteBinarioParaDecimal(4, 8)` |

Exemplo conceitual:

```text
Cromossomo: 1010 0011
            └─x─┘ └─y─┘
```

Para esse exemplo, os quatro primeiros bits representam \(x=10\), e os quatro últimos representam \(y=3\). Portanto:

\[
f(10,3)=2(10)+3=23
\]

Cada bloco de quatro bits pode representar um número de 0 a 15. Assim, para a representação usada no cálculo da avaliação, o maior valor possível é:

\[
f(15,15)=2(15)+15=45
\]

## 5. Função de avaliação

A função implementada em `Cromossomo.getAvaliacao()` é:

\[
f(x,y)=2x+y
\]

O código extrai \(x\) dos bits de índice 0 a 3 e \(y\) dos bits de índice 4 a 7. A avaliação é utilizada para calcular a soma das aptidões da população e orientar a seleção por roleta.

**Observação importante sobre o código:** os métodos `getX()` e `getY()` utilizam intervalos diferentes dos usados por `getAvaliacao()`. `getX()` converte os índices 0 a 2 e `getY()` converte os índices 4 a 6, ou seja, cada método utiliza três bits, enquanto a avaliação usa quatro bits por variável. Portanto, os valores impressos como `x` e `y` podem não corresponder aos valores efetivamente utilizados para calcular \(f(x,y)\). Esse comportamento é descrito no código fornecido e deve ser revisado caso se deseje consistência entre os valores exibidos e a avaliação.

## 6. Parâmetros de execução

A classe `Principal` configura os seguintes parâmetros:

| Parâmetro | Valor | Significado |
|---|---:|---|
| `quantidadeGeracoes` | 10 | Número de novas gerações criadas após a população inicial. |
| `tamanhoPopulacao` | 30 | Quantidade de cromossomos por população. |
| `tamanhoCromossomo` | 8 | Quantidade de genes configurada por cromossomo. |
| `percentualMutacao` | `0.05` (5%) | Probabilidade passada para o método de mutação de cada gene. |

A população inicial também é avaliada e exibida. Como são criadas 10 novas gerações após ela, o histórico pode conter 11 populações no total.

## 7. Etapas do algoritmo

1. **Configuração:** `Principal` define os parâmetros e chama `executa()`.
2. **Inicialização:** `AlgoritmoGenetico` cria a população inicial.
3. **Geração dos cromossomos:** cada cromossomo é inicializado com genes aleatórios.
4. **Avaliação:** o código divide a sequência binária em duas partes, converte cada parte para decimal e calcula \(2x+y\).
5. **Estatísticas:** a população apresenta a sequência binária, o valor decimal do cromossomo, a avaliação e informações relacionadas à roleta.
6. **Seleção:** dois índices são sorteados pela roleta com base nas avaliações.
7. **Crossover:** um ponto de corte é escolhido, combinando partes dos cromossomos progenitores para formar um descendente.
8. **Mutação:** cada gene do descendente tem a probabilidade configurada de ser invertido.
9. **Nova geração:** os descendentes são adicionados a uma nova população, que é avaliada e exibida.
10. **Resultado final:** após as gerações configuradas, o programa apresenta os dados do cromossomo com maior avaliação na última população.

## 8. Métodos importantes

| Classe | Método | Finalidade |
|---|---|---|
| `AlgoritmoGenetico` | `executa()` | Controla a execução e a evolução das populações. |
| `AlgoritmoGenetico` | `getUltimaPopulacao()` | Recupera a última população armazenada. |
| `Populacao` | `inicializaPopulacao()` | Cria os cromossomos iniciais e seus genes aleatórios. |
| `Populacao` | `avaliaCromossomos()` | Soma as avaliações dos cromossomos. |
| `Populacao` | `getNumeroRoleta()` | Seleciona um índice usando a roleta. |
| `Populacao` | `crossoverUmPonto()` | Combina dois progenitores em um descendente. |
| `Populacao` | `geracao()` | Produz a próxima população usando crossover e mutação. |
| `Populacao` | `getMelhorCromossomo()` | Localiza o índice do cromossomo com maior avaliação. |
| `Populacao` | `mostraEstatisticas()` | Imprime as estatísticas da população. |
| `Cromossomo` | `getGeneString()` | Retorna a sequência de genes em formato binário. |
| `Cromossomo` | `converteBinarioParaDecimal(int, int)` | Converte um intervalo de bits em número decimal. |
| `Cromossomo` | `getAvaliacao()` | Calcula \(2x+y\) usando os dois blocos de quatro bits. |
| `Cromossomo` | `getX()` e `getY()` | Retornam valores derivados de intervalos de bits definidos nesses métodos. |
| `Cromossomo` | `mutacao()` | Inverte genes conforme a probabilidade informada. |
| `Gene` | `mutar()` | Alterna o alelo entre `0` e `1`. |

## 9. Tecnologias utilizadas

- **Java 8 ou compatível:** linguagem utilizada na implementação.
- **Programação Orientada a Objetos:** classes, objetos, atributos, métodos e encapsulamento.
- **Collections Framework:** `List` e `LinkedList` para armazenar genes, cromossomos e populações.
- **`Math.random()` e `java.util.Random`:** geração de valores aleatórios.
- **Maven (opcional):** pode ser utilizado caso o projeto possua um `pom.xml` configurado.

## 10. Requisitos

- JDK instalado e configurado.
- Os cinco arquivos `.java` do projeto.
- Os arquivos devem estar no mesmo diretório caso não utilizem declarações de pacote.
- Maven apenas se houver configuração de projeto Maven.

Verifique a instalação com:

```bash
java -version
javac -version
```

## 11. Estrutura dos arquivos

Considerando que todas as classes estejam no pacote padrão:

```text
AlgoritmoGeneticoFuncao2xMaisY/
├── Principal.java
├── AlgoritmoGenetico.java
├── Populacao.java
├── Cromossomo.java
└── Gene.java
```

Os nomes dos arquivos devem corresponder aos nomes das classes públicas. Renomeie os arquivos recebidos com sufixos, como `Principal(5).java`, para os nomes esperados antes de compilar com `javac`.

## 12. Compilação e execução

### 12.1. Compilação direta com `javac`

No diretório que contém os arquivos, execute:

```bash
javac Principal.java AlgoritmoGenetico.java Populacao.java Cromossomo.java Gene.java
```

Em seguida:

```bash
java Principal
```

### 12.2. Maven

Se houver um `pom.xml` configurado, o projeto pode ser compilado com:

```bash
mvn clean package
```

O comando de execução por Maven depende da configuração do plugin Exec e do nome da classe principal no `pom.xml`. Como o arquivo `pom.xml` não foi fornecido, não é possível confirmar um comando Maven específico para executar o programa.

## 13. Saída esperada

A execução apresenta as estatísticas da população inicial, as estatísticas das 10 gerações seguintes e os dados do melhor cromossomo da última população. A saída inclui:

- Índice do indivíduo.
- Representação binária do cromossomo.
- Valor decimal retornado pelo método de conversão do cromossomo.
- Avaliação calculada por \(f(x,y)=2x+y\).
- Probabilidade associada à avaliação e índice obtido pela roleta.
- Valores de \(x\) e \(y\) apresentados no resumo final.

Como os genes, os pontos de corte, a seleção e a mutação são aleatórios, os resultados podem mudar a cada execução.

## 14. Observações técnicas sobre o código

- **Quantidade de genes:** o construtor de `Cromossomo` já cria `tamanho` objetos `Gene`. Depois, `gerarGenesAleatorios()` acrescenta outros `tamanho` genes à lista. Assim, a lista pode conter 16 genes quando `tamanhoCromossomo` é 8. Isso pode afetar a conversão binária e as operações posteriores.
- **Intervalos de bits:** `getAvaliacao()` utiliza os intervalos `[0,4)` e `[4,8)`, enquanto `getX()` e `getY()` usam `[0,3)` e `[4,7)`. Os valores exibidos de \(x\) e \(y\) podem, portanto, diferir dos usados na avaliação.
- **Seleção por roleta:** o método depende da soma das avaliações da população. Se essa soma for zero, a seleção não representa probabilidades úteis.
- **Tamanho da população:** alguns métodos percorrem os cromossomos usando o tamanho configurado (`getTamanho()`), pressupondo que esse valor corresponda à quantidade real de elementos da lista.
- **Crossover:** o ponto de corte pode ser zero, fazendo com que o descendente receba todos os genes de um dos progenitores, conforme a orientação sorteada.
- **Interpretação da otimização:** a implementação seleciona o cromossomo com maior avaliação na população final; não há garantia de que uma execução aleatória encontre sempre a solução máxima.

## 15. Limitações

A implementação é didática e ilustra os componentes básicos de um algoritmo genético orientado a objetos. Antes de utilizar os resultados como validação de otimização, recomenda-se corrigir a inicialização duplicada de genes, alinhar os intervalos usados para calcular e exibir \(x\) e \(y\), validar os tamanhos das listas e tratar casos em que a soma das avaliações seja zero.

