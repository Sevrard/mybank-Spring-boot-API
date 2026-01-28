# 🏦 Bank Backend — Spring Boot (DEV)

Backend bancaire développé avec **Spring Boot 3 / Java 21**, conçu pour servir de socle à un dashboard Angular.
Ce dépôt correspond à l’état DEV stable du projet, avant migration vers PostgreSQL et industrialisation via Flyway.

## 🎯 Objectif du projet

- Construire un **backend bancaire réaliste**
- Modéliser correctement les entités métier :
    - User
    - Account
    - Transaction
- Fournir une API REST claire et prête à être consommée par un front Angular
- Montrer une **évolution maîtrisée** du projet (DEV → PROD)

## 🧱 État actuel (DEV)

✔ Architecture propre (inspirée Clean Architecture)  
✔ Spring Boot 3.2.x / Java 21  
✔ JPA / Hibernate  
✔ Base **H2 (in-memory)** pour le développement  
✔ Entities Account & Transaction déjà en place  
✔ Repositories + mappers fonctionnels  
✔ Profils Spring séparés (`dev`, `test`, `prod`)

> ⚠️ La base PostgreSQL et Flyway seront intégrés dans une étape ultérieure (voir roadmap).

## 🧠 Architecture (simplifiée)

domain
├─ model
├─ repository (ports)
application
├─ usecases
infrastructure
├─ persistence (JPA entities, adapters)
├─ config

## 🛠️ Stack technique

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security (prévu)
- H2 (DEV / TEST)
- PostgreSQL (prévu)
- Maven

## ▶️ Lancer le projet (DEV)

bash
mvn spring-boot:run

## 🔜 Roadmap

->Socle backend DEV stable (H2)
->Modélisation Account / Transaction
->Ajout User + relations
->Endpoints REST complets
->Authentification JWT
->Migration PostgreSQL
->Flyway
->Connexion avec le front Angular

## 👤 Auteur

Stéphane Evrard
Développeur Full-stack
📍 Annecy – Genève
