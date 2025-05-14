# Match Up API

## Sobre a API

A **Match Up API** é uma aplicação desenvolvida para organizar partidas esportivas, permitindo a criação e gerenciamento de partidas, jogadores e equipes. A API está documentada com **Swagger**, facilitando a exploração e o uso dos endpoints.

## Testar a API Online (Sem Instalação)

Uma versão hospedada da API está disponível para testes imediatos. Utilize o endpoint base abaixo para fazer requisições diretamente:

```bash
https://single-emmy-diegovsc42-b390a8d8.koyeb.app
```

### Acessar Documentação Swagger:
```bash
https://single-emmy-diegovsc42-b390a8d8.koyeb.app/swagger-ui/index.html
```

**Nota:** Todos os exemplos da seção [Exemplos de uso] podem ser testados substituindo `localhost:9090` por `https://single-emmy-diegovsc42-b390a8d8.koyeb.app` nas requisições.

## Como usar a API com Docker

### OPÇÃO 1: Usar imagem pré-construída do Docker Hub (Recomendado)

Para quem prefere não construir a imagem localmente, a versão mais recente da API está disponível no Docker Hub:

**Baixe a imagem diretamente do repositório oficial:**
```bash
docker pull diegovsc42/match-up-api
```

**Execute o container com o mesmo padrão de portas**
```bash
docker run -p 9090:8080 diegovsc42/match-up-api
```

### OPÇÃO 2: Clonar o repositório e executar os comandos docker localmente
Para quem prefere ter controle total sobre o processo de construção ou precisa fazer modificações no código:


### 1. Clone o repositório

```bash
git clone https://github.com/diegoVSC42/matchup-api.git
cd matchup-api
```

### 2. Construa a imagem Docker

No diretório raiz do projeto, execute o comando abaixo para construir a imagem Docker:

```bash
docker build -t matchup-api:1.0 .
```

- **`-t `**: Flag para nomear a imagem.
- **`matchup-api:1.0`**: Nomeia a imagem como `matchup-api` e define a tag `1.0`.
- **`.`**: Indica que o `Dockerfile` e os arquivos necessários estão na pasta raiz.

### 3. Execute o container

Após construir a imagem, execute o container com o seguinte comando:

```bash
docker run -p 9090:8080 matchup-api:1.0
```

- **`-p 9090:8080`**: Mapeia a porta `8080` do container para a porta `9090` do seu host, caso queira, troque `9090` pela porta desejada.
- **`matchup-api:1.0`**: Nome da imagem e tag que será usada para criar o container.

Isso iniciará o container e permitirá que você acesse a API no endereço `http://localhost:9090`.

### 4. Explore a documentação Swagger

A API está documentada com **Swagger**, permitindo que você visualize e teste os endpoints diretamente no navegador. Para acessar a documentação, abra o seguinte endereço:

```bash
http://localhost:9090/swagger-ui/index.html
```

Na interface do Swagger, você poderá:

- Visualizar todos os endpoints disponíveis.
- Testar os endpoints diretamente na interface.
- Consultar os modelos de dados utilizados pela API.

Aqui está a reformulação do trecho do markdown com base na descrição da sua API:

### Exemplos de uso

Caso prefira não utilizar a interface Swagger UI, você pode interagir com a API diretamente através de ferramentas como Postman, Insomnia, cURL, etc.

#### Formatar uma lista de jogadores


Esse método transforma qualquer tipo de lista não formatada em uma lista formatada e aceitada pela API. Para utilizá-lo faça uma requisição `POST` para o endpoint `/lista/formatar-lista` com um texto contendo os nomes dos jogadores.

#### Exemplo: 

**Corpo da requisição**
```
Vôlei Segunda 19h30 às 21h30

1. Pedro  
2. Lívia  
3. Rafael  
4. Júlia  
5. Caio  
6. Nina  
7. Vinícius  
8. Lorena  
9. Igor  
10. Letícia  
11. Marcos  
12. Clara  
13. Gustavo  
14. Beatriz  
15. Lucas 
```

**Resposta:**
```json
[
  "Pedro",
  "Lívia",
  "Rafael",
  "Júlia",
  "Caio",
  "Nina",
  "Vinícius",
  "Lorena",
  "Igor",
  "Letícia",
  "Marcos",
  "Clara",
  "Gustavo",
  "Beatriz",
  "Lucas"
]
```


---

#### Iniciar uma partida

Cria novas configurações de equipes baseadas no tamanho especificado. Faça uma requisição `POST` para o endpoint `/partidas/iniciar`:

**Parâmetros:**
| Campo          | Descrição                      | Tipo     |
| -------------- | ------------------------------ | -------- |
| tamanhoEquipes | Número de jogadores por equipe | Integer  |
| jogadores      | Lista completa de jogadores    | String[] |

**Mecânica:**
1. Equipe A recebe os primeiros `N` jogadores da lista
2. Equipe B recebe os próximos `N` jogadores subsequentes
3. Excedentes são alocados na reserva

##### Exemplo de uso:
*Envio de lista com 21 jogadores e tamanho de equipe definido para 6 jogadores.*

**Corpo da requisição:**
```json
{
  "tamanhoEquipes": 6,
  "jogadores": [
    "Marco",
    "Leandro",
    "Renato",
    "Rogério",
    "Caio",
    "Nina",
    "Letícia",
    "Daniel",
    "Eduardo",
    "Camila",
    "Otávio",
    "Fernanda",
    "Gustavo",
    "Rafael",
    "Beatriz",
    "Pedro",
    "Carolina",
    "Vinicius",
    "Júlia",
    "Thiago",
    "Larissa"
  ]
}
```

**Resposta:**
```json
{
  "equipeA": {
    "tamanho": 6,
    "jogadores": [
      "Marco",
      "Leandro",
      "Renato",
      "Rogério",
      "Caio",
      "Nina"
    ]
  },
  "equipeB": {
    "tamanho": 6,
    "jogadores": [
      "Letícia",
      "Daniel",
      "Eduardo",
      "Camila",
      "Otávio",
      "Fernanda"
    ]
  },
  "reserva": {
    "tamanho": 9,
    "jogadores": [
      "Gustavo",
      "Rafael",
      "Beatriz",
      "Pedro",
      "Carolina",
      "Vinicius",
      "Júlia",
      "Thiago",
      "Larissa"
    ]
  }
}
```
---

#### Atualizar uma partida

Rotaciona os jogadores após uma partida, movendo parte da equipe perdedora para a reserva. Faça uma requisição `PUT` para o endpoint `/partidas/atualizar`:

**Parâmetros:**
| Campo           | Descrição                                      | Tipo   |
| --------------- | ---------------------------------------------- | ------ |
| equipePerdedora | Identificador da equipe perdedora (`A` ou `B`) | String |
| partida         | Objeto completo com o estado atual da partida  | Object |

**Mecânica:**
1. Verifica o tamanho atual da reserva
2. Move X jogadores (onde X é o menor valor entre: tamanho da reserva ou tamanho da equipe perdedora)
   - Remove os X últimos jogadores da equipe perdedora
   - Adiciona esses jogadores no final da reserva
   - Pega os X primeiros jogadores da reserva
   - Insere esses jogadores no início da equipe perdedora
3. Mantém a estrutura original de tamanhos das equipes

**Casos especiais:**
- Se reserva vazia: não faz alterações
- Se reserva menor que equipe: troca apenas a quantidade disponível
- Preserva sempre o tamanho original das equipes

##### Exemplo de uso:
*Rotaciona jogadores após derrota da Equipe A em uma partida com equipes de 6 jogadores e reserva contendo 9 jogadores.*

**Corpo da requisição:**
```json
{
  "equipePerdedora": "A",
  "partida": {
    "equipeA": {
      "tamanho": 6,
      "jogadores": [
        "Marco",
        "Leandro",
        "Renato",
        "Rogério",
        "Caio",
        "Nina"
      ]
    },
    "equipeB": {
      "tamanho": 6,
      "jogadores": [
        "Letícia",
        "Daniel",
        "Eduardo",
        "Camila",
        "Otávio",
        "Fernanda"
      ]
    },
    "reserva": {
      "tamanho": 9,
      "jogadores": [
        "Gustavo",
        "Rafael",
        "Beatriz",
        "Pedro",
        "Carolina",
        "Vinicius",
        "Júlia",
        "Thiago",
        "Larissa"
      ]
    }
  }
}
```

**Resposta:**
```json
{
  "equipeA": {
    "tamanho": 6,
    "jogadores": [
      "Gustavo",
      "Rafael",
      "Beatriz",
      "Pedro",
      "Carolina",
      "Vinicius"
    ]
  },
  "equipeB": {
    "tamanho": 6,
    "jogadores": [
      "Letícia",
      "Daniel",
      "Eduardo",
      "Camila",
      "Otávio",
      "Fernanda"
    ]
  },
  "reserva": {
    "tamanho": 9,
    "jogadores": [
      "Júlia",
      "Thiago",
      "Larissa",
      "Marco",
      "Leandro",
      "Renato",
      "Rogério",
      "Caio",
      "Nina"
    ]
  }
}
```
--- 

#### Separar jogadores em uma partida

Este método redistribui jogadores entre as equipes de acordo com estratégias específicas. Faça uma requisição `PUT` para o endpoint `/partidas/separar` com os seguintes parâmetros:

**Parâmetros:**
- `quantidadeMovida`: Número de jogadores a serem movidos (inteiro positivo)
- `tipoSeparacao`: Estratégia de redistribuição (`ALEATORIO`, `PRIMEIROS`, `MEIO`, `ULTIMOS` ou `EXTREMOS`)
- `partida`: Objeto completo da partida atual

**Tipos de separação:**
| Valor     | Descrição                                                                 |
| --------- | ------------------------------------------------------------------------- |
| ALEATORIO | Troca N jogadores aleatórios entre as equipes                             |
| PRIMEIROS | Troca os N primeiros jogadores da Equipe A com os N primeiros da Equipe B |
| MEIO      | Troca N jogadores a partir do meio de cada lista                          |
| ULTIMOS   | Troca os N últimos jogadores de cada equipe                               |
| EXTREMOS  | Troca N últimos da Equipe A com N primeiros da Equipe B (e vice-versa)    |

##### Exemplo de uso:

*Redistribui 3 jogadores entre as equipes usando a estratégia EXTREMOS, trocando os 3 últimos da Equipe A com os 3 primeiros da Equipe B em uma partida com equipes de 6 jogadores cada.* 

**Corpo da requisição:**
```json
{
  "quantidadeMovida": 3,
  "tipoSeparacao": "EXTREMOS",
  "partida": {
    "equipeA": {
      "tamanho": 6,
      "jogadores": [
        "Lucas",
        "Mariana",
        "João",
        "Ana",
        "Carlos",
        "Paulo"
      ]
    },
    "equipeB": {
      "tamanho": 6,
      "jogadores": [
        "Beatriz",
        "Felipe",
        "Larissa",
        "Gabriel",
        "Juliana",
        "Renato"
      ]
    },
    "reserva": {
      "tamanho": 3,
      "jogadores": [
        "Rafael",
        "Camila",
        "Thiago"
      ]
    }
  }
}
```

**Resposta:**
```json
{
  "equipeA": {
    "tamanho": 6,
    "jogadores": [
      "Lucas",
      "Mariana",
      "João",
      "Beatriz",
      "Felipe",
      "Larissa"
    ]
  },
  "equipeB": {
    "tamanho": 6,
    "jogadores": [
      "Ana",
      "Carlos",
      "Paulo",
      "Gabriel",
      "Juliana",
      "Renato"
    ]
  },
  "reserva": {
    "tamanho": 3,
    "jogadores": [
      "Rafael",
      "Camila",
      "Thiago"
    ]
  }
}
```

---

## Conclusão

A **Match Up API** é uma solução prática para organizar partidas esportivas. Com o uso de Docker, você pode facilmente executar a aplicação em qualquer ambiente. Explore a documentação Swagger para conhecer todos os recursos disponíveis e começar a usar a API. 

---
