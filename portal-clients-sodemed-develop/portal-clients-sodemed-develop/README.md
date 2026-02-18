
# SODEMED - Portal Client

### **Descripción**
**SODEMED** es un sistema desarrollado por **Moreti**, diseñado para la **gestión de solicitudes médicas**. Este portal permite manejar eficientemente las operaciones relacionadas con solicitudes médicas, ofreciendo funcionalidades modernas y una interfaz de usuario amigable.

---

## **Estructura del Proyecto**
Este proyecto utiliza **Angular 18** como framework frontend, con soporte para la integración de bibliotecas modernas y componentes visuales avanzados.

### **Dependencias Principales**
| Dependencia           | Versión   | Descripción                                                                 |
|-----------------------|-----------|-----------------------------------------------------------------------------|
| `@angular/core`       | ^18.1.3   | Framework Angular.                                                         |
| `@fullcalendar/core`  | ^6.0.3    | Biblioteca de calendarios interactivos.                                    |
| `chart.js`            | ^3.3.2    | Creación de gráficos avanzados.                                            |
| `primeflex`           | ^3.3.1    | Utilidades de diseño responsivo y flexbox.                                 |
| `primeng`             | ^17.18.9  | Conjunto de componentes ricos en funcionalidades.                          |
| `sweetalert2`         | ^11.12.4  | Alertas visuales modernas.                                                 |
| `xlsx`                | ^0.18.5   | Manipulación de hojas de cálculo en formato Excel.                         |
| `tailwindcss`         | ^3.4.10   | Framework CSS para diseño rápido y personalizable.                         |

### **Dependencias de Desarrollo**
| Dependencia                   | Versión   | Descripción                                   |
|-------------------------------|-----------|-----------------------------------------------|
| `@angular/cli`               | ^18.1.3   | Herramienta CLI para Angular.                |
| `karma`                      | ~6.4.2    | Ejecuta pruebas unitarias en navegadores.    |
| `typescript`                 | ~5.5.4    | Lenguaje para escribir código más estructurado. |

---

## **Instalación**
Sigue estos pasos para instalar y ejecutar el proyecto:

1. Clonar el repositorio:
   ```bash
   git clone
   cd SODEMED
   ```

2. Instalar las dependencias necesarias:
   ```bash
   npm install --legacy-peer-deps
   ```

3. Ejecutar el servidor de desarrollo:
   ```bash
   npm start
   ```

El servidor estará disponible en [http://localhost:4200](http://localhost:4200).

---

## **Scripts Disponibles**
| Script               | Descripción                                              |
|----------------------|----------------------------------------------------------|
| `npm start`          | Ejecuta la aplicación en modo desarrollo.                |
| `npm run build`      | Genera la aplicación lista para producción.              |
| `npm run build:prod` | Construye la aplicación para un entorno de producción.   |

---

## **Despliegue**
Para desplegar el proyecto en un entorno de producción:

1. Construir la aplicación:
   ```bash
   npm run build:prod
   ```

2. Subir el contenido generado en la carpeta `dist/portal-admin` a un servidor web o servicio de hosting (como AWS, Firebase Hosting, o Nginx).

---

## **Contribuir**
Si deseas contribuir al proyecto:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit (`git commit -m 'Añadida nueva funcionalidad'`).
4. Sube los cambios a tu repositorio fork (`git push origin feature/nueva-funcionalidad`).
5. Crea un pull request hacia el repositorio principal.

---


## **Contacto**
Para soporte o más información, puedes escribir a: **soporte@Moreti.com**