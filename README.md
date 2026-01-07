# VollMed API REST

API REST para **gerenciamento de pacientes, médicos e agendamento de consultas** em uma clínica fictícia, desenvolvida com **Java 17, Spring Boot e MySQL**.

---

## Tecnologias

- Java 17  
- Spring Boot (Web, Data JPA, Validation)  
- MySQL  
- Lombok  
- Maven  

---

## Funcionalidades

### Pacientes
- Cadastro com validações obrigatórias  
- Email, CPF e telefone **únicos**  
- Atualização permitida apenas para **nome, telefone e endereço**  

### Médicos
- Cadastro com especialidade  
- Status ativo/inativo  
- Endereço completo  

### Consultas
- Segunda a sábado, **07:00–19:00**  
- Duração fixa de **1 hora**  
- Antecedência mínima de **30 minutos**  
- Pacientes e médicos devem estar **ativos**  
- Apenas **uma consulta por paciente/dia**  
- Médico não pode ter consultas no mesmo horário  

---

## Endpoints principais

### ➕ Cadastro de paciente
`POST /pacientes`

```json
{
  "nome": "Bruno Lima",
  "email": "bruno.lima@email.com",
  "telefone": "77988222333",
  "cpf": "98765432100",
  "endereco": {
    "logradouro": "Avenida Principal",
    "bairro": "Jardim Primavera",
    "cidade": "Salvador",
    "uf": "BA",
    "cep": "40010000",
    "numero": "100",
    "complemento": null
  }
}
```

### ✏️ Atualização do paciente
`PUT /pacientes`

```json
{
  "id": 2,
  "nome": "Bruno Lima Silva",
  "telefone": "77999998888",
  "endereco": {
    "logradouro": "Avenida Principal",
    "bairro": "Centro",
    "cidade": "Salvador",
    "uf": "BA",
    "cep": "40010000",
    "numero": "200",
    "complemento": "Apto 101"
  }
}
```

### 📄 Listagem de pacientes
`GET /pacientes`

```json
{
    "content": [
        {
            "id": 2,
            "nome": "Bruno Lima",
            "email": "bruno.lima@email.com",
            "cpf": "98765432100"
        },
        {
            "id": 3,
            "nome": "Carla Mendes",
            "email": "carla.mendes@email.com",
            "cpf": "321.654.987-00"
        }
    ],
    "empty": false,
    "first": true,
    "last": false,
    "number": 0,
    "numberOfElements": 2,
    ...
}
```
---

## 📂 Estrutura do Projeto

```text
med.voll.api_rest
├── consulta
│   ├── Consulta
│   ├── ConsultaRepository
│   └── DadosCadastroConsulta
│
├── controller
│   ├── ConsultaController
│   ├── MedicoController
│   └── PacienteController
│
├── endereco
│   ├── DadosEndereco
│   └── Endereco
│
├── medico
│   ├── DadosAtualizacaoMedico
│   ├── DadosCadastroMedico
│   ├── DadosListagemMedico
│   ├── Especialidade
│   ├── Medico
│   └── MedicoRepository
│
├── paciente
│   ├── DadosAtualizacaoPaciente
│   ├── DadosCadastroPaciente
│   ├── DadosListagemPaciente
│   ├── Paciente
│   └── PacienteRepository
│
├── service
│   ├── ConsultaService
│   ├── MedicoService
│   └── PacienteService
│
├── ApiRestApplication

resources
├── db.migration
└── application.properties

