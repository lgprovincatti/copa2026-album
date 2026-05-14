# ⚽ Copa do Mundo 2026 - Controle de Figurinhas
Sistema desktop para controle de figurinhas do álbum da Copa do Mundo 2026.
Desenvolvido por Lucas Gorla Provinciatti

## Tecnologias

- Java 21
- Spring Boot
- JavaFX
- PostgreSQL
- Maven

## Funcionalidades

- Dashboard geral
- Controle de figurinhas
- Seleções
- Grid interativo
- Exportação de faltantes
- Estatísticas em tempo real

## Estrutura

```text
backend/
frontend/
database/
docs/
```

## Como executar backend

```powershell
cd backend

.\mvnw.cmd spring-boot:run
```

## Como executar frontend
```powershell
cd frontend

..\backend\mvnw.cmd javafx:run
```