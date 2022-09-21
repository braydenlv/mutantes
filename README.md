
# Challenge mutantes
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa con un método o función en donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.
Se sabrá si un humano es mutante, si se encuentra más de una secuencia de cuatro letras iguales , de forma oblicua, horizontal o vertical.
> **Ejemplo:** ADN humano 
  
![image](https://user-images.githubusercontent.com/38684033/191415914-d9980540-9a35-41b0-ab08-ef18b4db3c1a.png)

> **Ejemplo:** ADN Mutante

![image](https://user-images.githubusercontent.com/38684033/191416160-1b652999-4023-49c0-b427-7b9614f03f9d.png)

# Desafíos
### Nivel 1:
Programar en cualquier lenguaje de programación que cumpla con el método pedido por Magneto.
### Nivel 2:
- Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,Amazon AWS, etc)
- Crear el servicio **“/mutant/”** en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:

> POST → /mutant/ 

```
{
  “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

- Si es **mutante**, debería devolver un **HTTP 200-OK**.
- Si es **humano** debería devolver un **HTTP 403-Forbidden**.
- Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico entre 100 y 1'000.000 de peticiones por segundo.

### Nivel 3:

- Anexar una base de datos, la cual guarde los ADN’s verificados con la API. **Solo 1 registro por ADN.**
- Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN.

> GET → /stats/ <br>

```
{ 
  “count_mutant_dna”: 40, “count_human_dna”: 100: “ratio”: 0.4 
}
```

- Test-Automáticos, Code coverage > 80%.

# Ejecución de la API

Para ejecutar la *API MUTANTES* se tiene dos opciones:

1.  Descargar el repositorio y crear una instancia local con el siguiente comando en una terminal (debe de estar apuntando al directorio raíz del proyecto para que funcione).
> Windows

```
mvnw.cmd spring-boot:run 
```

> Mac

```
./mvnw spring-boot:run
```

Una vez inicia la instancia puede acceder a la siguiente dirección para utilizar el servicio:
```
localhost:8080/
```
2. Consumir la API directamente desde el navegador con el siguiente Endpoint.
```
mutantes-362620.ue.r.appspot.com/
```

Se recomienda trabajar con un cliente para manejo de apis como Postman.
<p align="left">
  <img src="https://user-images.githubusercontent.com/38684033/191428061-d6544309-86bd-424a-871a-afcc1b7ab202.png" width="350" title="postman imagen">
</p>


## Metodos
|Tipo solicitud| Path        | Envío body     | Respuesta                                       | Http status                                                         |
|:------------:|:-----------:|:--------------:|:-----------------------------------------------:|:-------------------------------------------------------------------:|
|GET           |/mutant/     |Vacío           | Lista de todos los mutantes.                    |200 OK                                                               |
|GET           |/mutant/{id} |Vacío           |Único mutante del id.<br>Descripción de errores. |200 OK <br> 404 NOT FOUNT                                            |
|GET           |/stats       |Vacío           |Estadísticas de las verificaciones.              |200 OK                                                               |
|POST          |/mutant/     |Cadena DNA      | Mutante guardado. <br> Descripción de errores.  |200 OK (mutantes) <br> 403 FROBIDDEN (humanos) <br> 400 BAD REQUEST  |
|POST          |/mutant/     |Cadena DNA<br>id| Mutante guardado. <br> Descripción de errores.  |200 OK (mutantes) <br> 403 FROBIDDEN (humanos) <br> 400 BAD REQUEST  |
|DEL           |/mutant/{id} |Vacío           | Mutante eliminado . <br> Descripción de errores.|200 OK <br> 404 NOT FOUND                                            |

# Recursos

En el transcurso de la solución del challenge utilicé estos recursos como guía (obviamente me faltarán un montón 👉👈 )
- https://github.com/derjust/spring-data-dynamodb
- https://www.baeldung.com/ 
- https://start.spring.io/
- https://docs.aws.amazon.com/general/latest/gr/ddb.html
- https://docs.aws.amazon.com/code-samples/latest/catalog/javav2-usecases-creating_sns_sample_app-pom.xml.html
- https://www.springboottutorial.com/
- [Archivo excel de análisis de patrones para recorrer las matrices diagonalmente](https://docs.google.com/spreadsheets/d/18Bx6_gyejPOLZwwKB78NQ1MeEFRvGh9B/edit?usp=sharing&ouid=107918460291192949725&rtpof=true&sd=true)


# Importante
_**Como se describió con anterioridad, la API no necesita ser instanciada, se puede consumir desde cualquier lugar a cualquier hora del día ya que tiene una solución completamente servertless, pero por razones de seguridad quedará deshabilitada luego de presentar el challenge.**_
