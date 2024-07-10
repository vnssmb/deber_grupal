# DEBER GRUPAL
Se muestra una tabla de constructores de Fórmula 1 con la capacidad de filtrar los datos por año.
![Captura de pantalla 2024-07-09 235720](https://github.com/vnssmb/deber_grupal/assets/142614155/4fb42827-fa02-4228-92cf-de687919bb23)

# Método utilizados
## main(String[] args)
Este método es el punto de entrada principal de la aplicación Java.

## start(Stage primaryStage)
Configura los controles como ComboBox y TableView, carga los datos desde un archivo CSV y establece la escena principal.

## cargarDatosDesdeCSV(String fileName)
Utiliza un BufferedReader para leer línea por línea, divide cada línea en campos utilizando coma como delimitador, y crea objetos Constructor que se agregan a la lista constructorData y al ComboBox allYears si el año no está ya presente.

## filtrarDatos()
Crea una lista filtrada (filteredData) de objetos Constructor basándose en el año seleccionado y actualiza la tabla con estos datos filtrados.

## Clase Constructor
lase interna estática que define la estructura de un constructor de Fórmula 1. Contiene getters y setters para cada atributo (constructorId, name, nationality, url, year) que son utilizados para configurar las celdas de la TableView.

