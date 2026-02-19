# Sodomed — Sistema de Domicilios Médicos

Sistema de gestión de servicios médicos a domicilio desarrollado con Angular (portales web) y Spring Boot (API REST). Incluye portal de administración, portal de clientes, y backend con base de datos MySQL.

---

## 🧩 Arquitectura

```
┌─────────────────────────────────────────────────────────┐
│                     Traefik (Reverse Proxy)             │
│              admindomicilios.saludmedcol.com             │
│                domicilios.saludmedcol.com                │
└───────────┬──────────────────┬──────────────────────────┘
            │                  │
    ┌───────▼──────┐   ┌───────▼──────┐
    │ sodomed-admin│   │sodomed-clients│
    │   (Angular)  │   │   (Angular)   │
    │   Nginx:80   │   │   Nginx:80    │
    └───────┬──────┘   └───────┬───────┘
            │                  │  /api/*
            └────────┬─────────┘
                     │
            ┌────────▼────────┐
            │ sodomed-monolito│
            │  (Spring Boot)  │
            │   Puerto: 8080  │
            └────────┬────────┘
                     │
            ┌────────▼────────┐
            │  java_db_sodemed│
            │   (MySQL 5.7)   │
            │   Puerto: 3306  │
            └─────────────────┘
```

---

## 🚀 Stack Tecnológico

| Capa | Tecnología |
|------|-----------|
| Frontend Admin | Angular + PrimeNG |
| Frontend Clientes | Angular + PrimeNG |
| Backend | Spring Boot 3.3.3 (Java 17) |
| Base de Datos | MySQL 5.7 |
| ORM | Hibernate / Spring Data JPA |
| Autenticación | JWT |
| Reverse Proxy | Traefik v3 |
| Containerización | Docker Swarm |
| CI/CD | GitHub Actions |
| Registry | Docker Hub |
| Cloud | Azure VM |

---

## 📦 Imágenes Docker

| Servicio | Imagen |
|---------|--------|
| Portal Admin | `jhamfgit/sodomed-admin:latest` |
| Portal Clientes | `jhamfgit/sodomed-clients:latest` |
| Monolito API | `jhamfgit/sodomed-monolito:latest` |

---

## 🌐 URLs de Acceso (Producción)

| Servicio | URL |
|---------|-----|
| Portal Clientes | https://domicilios.saludmedcol.com |
| Portal Admin | https://admindomicilios.saludmedcol.com |
| API REST | https://domicilios.saludmedcol.com/api |

---

## 📁 Estructura del Repositorio

```
sodomed/
├── .github/
│   └── workflows/
│       └── build-sodomed.yml        # CI/CD: build y push a Docker Hub
├── portal-admin-sodemed-develop/    # Portal de administración (Angular)
│   └── portal-admin-sodemed-develop/
│       ├── src/
│       │   └── environments/
│       │       ├── environment.ts           # Config local
│       │       └── environment.prod.ts      # Config producción
│       └── dockerfile
├── portal-clients-sodemed-develop/  # Portal de clientes (Angular)
│   └── portal-clients-sodemed-develop/
│       ├── src/
│       │   └── environments/
│       │       ├── environment.ts
│       │       └── environment.prod.ts
│       └── dockerfile
├── sodemed-monolito-develop/        # Backend Spring Boot
│   └── sodemed-monolito-develop/
│       ├── src/
│       ├── pom.xml
│       └── dockerfile               # Multi-stage build (Maven + JRE)
└── docker-stack.yml                 # Stack de despliegue para Docker Swarm
```

---

## ⚙️ CI/CD — GitHub Actions

El workflow `.github/workflows/build-sodomed.yml` se ejecuta automáticamente cuando hay cambios en:
- `portal-admin-sodemed-develop/**`
- `portal-clients-sodemed-develop/**`
- `sodomed-monolito-develop/**`
- `docker-stack.yml`

También puede ejecutarse manualmente desde la pestaña **Actions → Run workflow**.

### Secretos requeridos en GitHub

| Secreto | Descripción |
|---------|-------------|
| `DOCKER_USERNAME` | Usuario de Docker Hub |
| `DOCKER_PASSWORD` | Token de acceso a Docker Hub |

---

## 🐳 Despliegue en Docker Swarm (Portainer)

### Prerrequisitos en la VM

1. Docker Swarm inicializado
2. Red overlay `vnetdocs` creada
3. Traefik desplegado en la misma red
4. Directorios de volúmenes creados:

```bash
sudo mkdir -p /mnt/docker-volumes/sodomed/bd
sudo mkdir -p /mnt/docker-volumes/sodomed/image
sudo chmod 777 /mnt/docker-volumes/sodomed/bd
sudo chmod 777 /mnt/docker-volumes/sodomed/image
```

### Despliegue desde Portainer

1. Ir a **Stacks → Add Stack**
2. Pegar el contenido de `docker-stack.yml`
3. Hacer clic en **"Deploy the stack"**

### Servicios del stack

| Servicio | Imagen | Puerto interno |
|---------|--------|---------------|
| `sodomed-admin` | `jhamfgit/sodomed-admin:latest` | 80 |
| `sodomed-clients` | `jhamfgit/sodomed-clients:latest` | 80 |
| `sodomed-monolito` | `jhamfgit/sodomed-monolito:latest` | 8080 |
| `java_db_sodemed` | `mysql:5.7` | 3306 |

---

## 🔧 Configuración del Backend

El backend lee su configuración desde `src/main/resources/application.properties`. Las variables relevantes:

| Propiedad | Valor por defecto |
|-----------|------------------|
| `spring.datasource.url` | `jdbc:mysql://java_db_sodemed:3306/sodemed` |
| `spring.datasource.username` | `root` |
| `spring.datasource.password` | *(vacía)* |
| `server.port` | `8080` |
| `jwt.expiration.client` | `86400000` ms (24h) |
| `jwt.expiration.employee` | `3600000` ms (1h) |

> **Nota de seguridad:** Para producción, considera usar secretos de Portainer o variables de entorno para las credenciales en lugar de valores en texto plano.

---

## 🔄 Actualizar la Aplicación

1. Hacer push de los cambios al branch `master`
2. GitHub Actions construye y sube las nuevas imágenes automáticamente
3. En Portainer: **Stacks → sodomed → Update the stack** (las imágenes se actualizan desde Docker Hub)

---

## 🩺 Verificación de Servicios

```bash
# Ver contenedores corriendo
docker ps | grep sodomed

# Logs del monolito
docker logs $(docker ps -q --filter name=sodomed-monolito) --tail 50

# Verificar MySQL
docker exec $(docker ps -q --filter name=java_db_sodemed) mysqladmin ping -h localhost
```

---

## 📝 Notas

- El `dockerfile` del monolito usa **multi-stage build**: Maven compila el JAR en la primera etapa y Eclipse Temurin lo ejecuta en la segunda.
- La base de datos usa `spring.jpa.hibernate.ddl-auto=update`, por lo que Hibernate crea/actualiza las tablas automáticamente al arrancar.
- El certificado SSL es gestionado automáticamente por **Traefik + Let's Encrypt** (`letsencryptresolver`).
