# 🏦 Bank Backend — Spring Boot (DEV)

Backend bancaire développé avec **Spring Boot 3 / Java 21**, conçu pour servir de socle à un dashboard Angular.
Ce dépôt correspond à l’état DEV stable du projet, avant migration vers PostgreSQL et industrialisation via Flyway.

# 🏦 Bank Backend – Spring Boot

Backend bancaire démonstratif implémentant :

* une **authentification JWT stateless**
* une **architecture Clean / Hexagonale**
* une base **PostgreSQL dockerisée**

Le projet est conçu pour être **reproductible**, **lisible**, et **présentable en entretien**.

---
## 🚀 Stack technique

* Java 21
* Spring Boot 3
* Spring Security (JWT)
* JPA / Hibernate
* PostgreSQL 16
* Docker / Docker Compose

---
## 🔐 Sécurité (JWT)

* Authentification via `/auth/login`
* Génération d’un token JWT signé
* Filtre JWT exécuté sur chaque requête protégée
* API **stateless** (aucune session serveur)

Flux simplifié :

```
Login → JWT → Bearer Token → Filtre → SecurityContext
```

---

## 🧱 Architecture

```
src/main/java/com/bank
│
├── api/              → Controllers métier
├── application/      → Use cases
├── domain/           → Modèle métier
├── infrastructure/
│   ├── persistence/  → JPA / Repositories
│   └── security/     → JWT / Spring Security
```

* le domaine ne dépend d’aucun framework
* la sécurité est isolée dans l’infrastructure

---

## 🐳 Lancer le projet avec Docker

### Prérequis

* Docker Desktop
* Java 21 (pour le build)

---

### ▶️ Démarrage

```bash
docker compose up -d --build
```

Services lancés :

* PostgreSQL → `localhost:5432`
* Backend → `localhost:8080`

---

### 🧪 Tester l’authentification

```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "email": "john.doe@bank.com",
  "password": "password123"
}
```

Réponse :

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 🔒 Appel d’une route protégée

```http
GET http://localhost:8080/accounts
Authorization: Bearer <TOKEN>
```

---

## 🗄️ Base de données

* PostgreSQL 16
* DB : `db_bank`
* User : `ffx`

Connexion manuelle :

```bash
docker exec -it bank-postgres psql -U ffx -d db_bank
```

---

## ⚙️ Configuration

Le projet utilise des **profils Spring** :

* `dev` → H2 / dev local
* `prod` → PostgreSQL Docker

En prod :

```yaml
spring.jpa.hibernate.ddl-auto: validate
```

---

## 🎯 Objectif du projet

Ce projet sert de :

* démonstrateur technique
* base propre pour évolution future
* support d’entretien backend / Java

---

## 👤 Auteur

Projet développé par **Stéphane Evrard**

---

## 📌 Améliorations possibles

* Flyway (migrations SQL)
* Refresh token
* Rôles avancés (ADMIN / USER)
* Dockerisation CI/CD

---

✅ Projet prêt pour démonstration et production contrôlée
