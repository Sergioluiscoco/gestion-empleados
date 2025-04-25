Gestión de Empleados y Empresas

	Proyecto Intermodular en Java que permite gestionar información de empresas y empleados con un frontend JSP/HTML/CSS/JavaScript y un 	backend Java/JDBC, siguiendo patrones MVC y buenas prácticas de POO.

📋 Índice:


- 🎯 Objetivo

- 🚀 Tecnologías

- 🔧 Prerrequisitos

- ⚙️ Configuración del entorno

- 📦 Instalación y despliegue

- 🗂 Estructura del proyecto

- 📊 Diagrama de la base de datos

- 🛠️ Uso de la aplicación

- 🌐 Ramas y flujo de Git

- 📢 Licencia y contribuciones



🎯 Objetivo

	Desarrollar una aplicación web en Java para realizar operaciones CRUD (Create, Read, Update, Delete) sobre dos entidades estrechamente 			relacionadas: Empresas y Empleados, utilizando:

		- Java y Jakarta EE (Servlets y JSP)

		- JDBC con PreparedStatement para interacción segura con la base de datos MySQL

		- HTML5, CSS3 y JavaScript (interactividad: filtros, validaciones, toasts, modo claro/oscuro)

		- Control de versiones con Git y hospedaje en GitHub


🚀 Tecnologías

	- Backend: Java 17, Jakarta EE 10 (Servlet API), Maven

	- Base de datos: MySQL 8

	- Persistencia: JDBC con PreparedStatement

	- Frontend: JSP, HTML5, CSS3, JavaScript (Vanilla)

	- Servidor de aplicaciones: Apache Tomcat 10

	- Control de versiones: Git, GitHub


🔧 Prerrequisitos

	- Java JDK 17 o superior instalado en tu sistema.

	- Maven 3+ para construcción del proyecto.

	- MySQL Server corriendo localmente (puede usarse Docker o XAMPP).
 
	- Apache Tomcat 10 configurado.

	- Git instalado y configurado (git config --global user.name, git config --global user.email).


⚙️ Configuración del entorno

	- Crear la base de datos en MySQL:

		CREATE DATABASE gestion_empleados;
		USE gestion_empleados;
		-- Ejecutar scripts de creación de tablas en docs/diagrama-bbdd.png


	- Credenciales de la base de datos: editar en BBDDConnector.java:

		private static final String JDBC_URL = "jdbc:mysql://localhost:3307/gestion_empleados?autoReconnect=true&useSSL=false";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "1234");


	- Variables de entorno (opcional):

		Si prefieres no hardcodear credenciales, puedes usar un archivo config.properties y cargarlo en BBDDConnector.


📦 Instalación y despliegue

	- Clonar el repositorio:

		git clone https://github.com/[TU_USUARIO]/gestion-empleados.git
		cd gestion-empleados


	- Construir el WAR:

		mvn clean package


	- Desplegar:

		Copiar target/gestion-empleados.war dentro de la carpeta webapps de tu Tomcat.


	- Iniciar Tomcat: TOMCAT_HOME/bin/startup.sh (Linux/macOS) o startup.bat (Windows).


	- Acceder:

		http://localhost:8080/gestion-empleados/


🗂 Estructura del proyecto

	gestion-empleados/
	├── .git/                        # Historial de Git
	├── .gitignore                   # Archivos ignorados
	├── .gitattributes               # Configuración de linguist (GitHub)
	├── README.md                    # Este archivo
	├── pom.xml                      # Configuración de Maven
	├── src/main/java/...            # Código fuente Java (modelos, DAOs, controllers)
	├── src/main/webapp/
	│   ├── css/style.css            # Estilos (modo claro/oscuro)
	│   ├── images/                  # Fondos e iconos
	│   ├── index.jsp                # Página principal
	│   ├── empresas/                # JSPs de empresas
	│   │   ├── list-empresas.jsp
	│   │   └── empresas-form.jsp
	│   └── empleados/               # JSPs de empleados
	│       ├── list-empleados.jsp
	│       └── empleados-form.jsp
	│   └── WEB-INF/web.xml          # Despliegue y configuraciones
	└── docs/
    └── diagrama-bbdd.png        # Diagrama ER de la base de datos
	└── video/demo.mp4               # Vídeo demostrativo


📊 Diagrama de la base de datos

	Crear carpeta docs en la raiz del proyecto con la imagen: diagrama-bbdd.png


🛠️ Uso de la aplicación

	- Navegación principal

		Empresas:

			- Listado: /empresas?action=list

			- Crear: /empresas?action=new

			- Editar: /empresas?action=edit&id=[id]

			- Eliminar: /empresas?action=delete&id=[id]


		Empleados:

			- Listado: /empleados?action=list

			- Crear: /empleados?action=new

			- Editar: /empleados?action=edit&id=[id]

			- Eliminar: /empleados?action=delete&id=[id]


		Modo claro/oscuro: botón en la barra de navegación


		Buscador: filtra empresas/empleados por nombre al vuelo


		Toasts: notificaciones emergentes de éxito/error tras cada operación CRUD
	

🌐 Ramas y flujo de Git

	main: rama estable para producción o entrega final

	dev: rama de desarrollo continuo

		# Crear y subir rama dev
		git checkout -b dev
		git push -u origin dev

	
	Para fusionar en main una vez estable:

		git checkout main
		git merge dev
		git push


🎥 Demo

	Vídeo demostrativo: /gestionEmpleados/video/demo.mp4


🤝 Contribuciones

	Si quieres aportar mejoras o reportar bugs, abre un issue o un pull request en este repositorio.


📜 Licencia y contribuciones

Este proyecto está bajo la licencia MIT.
Cualquier aportación es bienvenida: abre un issue o envía un pull request.



© 2025 [Tu Nombre] - Proyecto Intermodular







