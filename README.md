# Surveys

A simple survey tool built as a technical assessment: a Java / Spring Boot API with a Vue.js client.

## About The Project

Case study for the development of a simple survey tool. The full specification is in [case-study.pdf](./case-study.pdf).

### Built With

- Java / Spring Boot (API, Maven with `mvnw`)
- Vue.js (client, in `vue-surveys/`)
- Docker Compose

## Getting Started

### Prerequisites

- Docker installed and running

### Installation

1. Clone the repository.
2. From the project root:

```sh
make watch
```

This runs `docker-compose up --build --watch`, starting the Spring Boot server and the Vue.js client with live rebuild.

## Project structure

| Path | Role |
| --- | --- |
| `src/`, `pom.xml`, `mvnw` | Spring Boot API |
| `vue-surveys/` | Vue.js client |
| `docker-compose.yml` | Server and client orchestration |
| `case-study.pdf` | Assessment specification |

## License

No license file is present in this repository.

## Contact

Dyrits - https://github.com/Dyrits
