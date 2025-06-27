# Caso Trabajador v02 (POO, UML, Java con JFrame, eventos e interfaces)

## Universidad de La Frontera  
**Departamento de Ciencias e Informática**  
**Asignatura:** Programación Orientada a Objetos  
**Evidencia 12: Caso Trabajador v02**

---

### Integrantes

- Cristobal Ramos  
- Esteban Aguilera  
- Jonathan Chavéz  
- Joaquin Arriagada  

---

## Descripción General

Este repositorio corresponde al desarrollo de la evidencia "Caso Trabajador v02", que consiste en un sistema de gestión de trabajadores implementado en Java, utilizando los principios de la Programación Orientada a Objetos (POO), diagramación UML, persistencia de datos, y dos interfaces de usuario: **consola (CLI)** y **gráfica (JFrame)**. Se utilizan eventos e interfaces para la interacción con el usuario.

El objetivo es permitir la administración de información básica de los trabajadores (nombre, apellido, RUT, isapre, AFP), cubriendo las funcionalidades mínimas de agregar, editar, buscar, eliminar y listar trabajadores desde ambas interfaces.

---

## Contenidos del Repositorio

- **Diagrama de Clases UML**  
  ![image1](image1)

- **Archivo VPP (Visual Paradigm Project)**  
  - [CasoTrabajador.vpp](CasoTrabajador.vpp)  
    *Archivo fuente de los modelos UML desarrollados en Visual Paradigm, disponible en la raíz del repositorio para respaldo y revisión.*

- **Código fuente Java**  
  - Implementación modular y estructurada en tres paquetes principales:
    - `data`: Lógica de negocio y persistencia.
    - `gui`: Interfaces de usuario (CLI y JFrame).
    - `launcher`: Lanzador principal del sistema.

---

## Estructura de Carpetas

```
data/
  ├── BaseDatos.java      # Manejo de persistencia (JSON) y CRUD de trabajadores
  ├── Persona.java        # Clase base abstracta para modelar personas
  └── Trabajador.java     # Clase que extiende Persona, modelo principal

gui/
  ├── InterfazCLI.java    # Interfaz por consola (texto)
  └── MainView.java       # Interfaz gráfica con JFrame y eventos

launcher/
  └── Main.java           # Lanzador del sistema

otros archivos:
  ├── trabajadores.json   # Persistencia de datos de trabajadores en formato JSON (se genera en ejecución)
  └── CasoTrabajador.vpp  # Modelo UML en Visual Paradigm
```

---

## Requisitos y Tecnologías

- **Java 17+**
- **JDK Swing** para la interfaz gráfica
- **Google Gson** para serialización y persistencia en JSON
- **Visual Paradigm** (opcional, para abrir el archivo VPP)

---

## Cómo Ejecutar el Proyecto

### 1. Compilación

Se recomienda compilar desde la raíz del repositorio:

```bash
javac data/*.java gui/*.java launcher/*.java
```

### 2. Ejecución

#### Interfaz Gráfica (JFrame):

```bash
java launcher.Main
```

Por defecto, el sistema lanza la interfaz gráfica. Si desea utilizar la CLI, instanciar la clase `InterfazCLI` desde `Main.java`.

---

## Funcionalidades Principales

### ✅ **Agregar Trabajador**
- Desde ambas interfaces se pueden ingresar los datos de un trabajador y almacenarlos de forma persistente.

### ✅ **Buscar Trabajador**
- Permite buscar por RUT, mostrando los datos completos.

### ✅ **Modificar Datos**
- Es posible editar nombre, apellido, isapre y AFP de un trabajador existente, identificándolo por su RUT.

### ✅ **Eliminar Trabajador**
- Se elimina el registro de un trabajador por su RUT.

### ✅ **Listar Trabajadores**
- Visualización de todos los trabajadores registrados.

### ✅ **Persistencia**
- Toda la información se almacena en el archivo `trabajadores.json` en formato estructurado y legible.

---

## Detalle de Clases y Diseño

- **BaseDatos.java**  
  Encapsula la lógica de acceso, modificación y persistencia de los trabajadores utilizando archivos JSON. Permite operaciones CRUD y garantiza la actualización automática de datos.
  
- **Trabajador.java**  
  Modelo principal, hereda de Persona, almacena los datos fundamentales del trabajador.
  
- **InterfazCLI.java**  
  Permite operar el sistema por consola, ideal para entornos sin entorno gráfico.
  
- **MainView.java**  
  Interfaz gráfica intuitiva y amigable, implementada con JFrame, que incluye:
    - Formulario de ingreso
    - Tabla de visualización
    - Botones de acción
    - Diálogos de edición y confirmación

- **Main.java**  
  Punto de entrada del sistema.

---

## Modelo UML

- El diagrama de clases UML detalla la arquitectura POO, el uso de herencia, encapsulamiento y la relación entre las clases principales.
- Ver imagen incluida más arriba y el archivo `.vpp` para edición o análisis en Visual Paradigm.

---

## Notas y Recomendaciones

- El archivo `trabajadores.json` se crea automáticamente si no existe.
- El sistema previene la duplicidad de RUTs.
- Se recomienda probar todas las funcionalidades tanto en CLI como en GUI para validar la experiencia completa.

---

## Créditos y Licencia

Trabajo desarrollado por estudiantes de la Universidad de La Frontera para fines académicos.

---
