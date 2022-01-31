## Test Java - Between/Inditex

Aplicación realizada en Java+Spring boot para el ejercicio a presentar a Between/Inditex.

---
## Tecnologías

- Java 8
- Spring Boot 2
- H2
- Swagger
- Docker
---
## Enunciado

El enunciado es el siguiente: 

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas.

**Campos**
- BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
- START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
- PRICE_LIST: Identificador de la tarifa de precios aplicable.
- PRODUCT_ID: Identificador código de producto.
- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
- PRICE: precio final de venta.
- CURR: iso de la moneda.

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:

Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los test.

---

**Assumptions:**

Habiendo leído el enunciado, procedo a especificar una lista de afirmaciones que asumo para la resolución del ejercicio:

1) Los límites de las fechas definidas van a ser incluídos. Por lo tanto si se define una fecha de las 14:00:00 hasta las 23:59:00 y deseo saber el precio para las 14:00:00, se tomará como incluido en el rango.
2) Se desea devolver precio y tarifa. Sin embargo tenemos solamente un identificador de tarifas, por lo cual asumo que lo que se quiere es devolver el valor de la tarifa que puede estar guardada en otro lugar fuera de la tabla de PRICES. Para esto asumo que existe una tabla FEES que posee la información del valor de las tarifas dado un ID.
3) Se devuelve la información del precio y tarifa pero de manera estructurada incluyendo data extra que puede ser importante (Currency Id), y de tal forma que si se necesita escalar y ampliar data de la respuesta, esto sea posible sin necesidad de cambiarla, sino solamente agregando nuevos campos en la estructura.
4) Se realizaron los tests requeridos en la clase PriceControllerTest, con nombres challengeTest1 a challengeTest5.

---

**Api Resources:**

    Endpoint: /v1/prices
    Query Params: 
    ProductId - Requerido
    BrandId - Requerido
    ApplicationDate - Requerido
    Metodo: GET
    Response Status and Body:

        Status 200 -> OK - Se encontró correctamente un precio para el product, marca y fecha dados.
        Example Body: Se devuelve la estructura de precio:
            {
                "product_id": 35455, // Identificador del producto
                "brand_id": 1, // Identificador de la cadena
                "pricing": { // Estructura con toda la información de precio y tarifa
                    "currency_id": "EUR", // Identificador de moneda
                    "perceived_value": { // Información relacionada al PVP
                        "id": 4, // Identificador del PVP (Se agregó la columna a la tabla PRICES)
                        "total": 38.95 // Valor del PVP 
                    },
                    "fee": { // Información relacionada a la tarifa
                        "id": 4, // Identificador de la tarifa
                        "total": 40.0 // Valor de la tarifa
                    },
                    "application_from": "2020-06-15-16.00.00", // Fecha de inicio del pricing
                    "application_to": "2020-12-31-23.59.59" // Fecha de fin del pricing
                }
            }
        
        Status 400 -> BAD_REQUEST - Hubo algún inconveniente con el ingreso de los datos. Los parámetros ingresados poseen un tipo o formato inválido, o no se ingresó un parámetro requerido.
        Example Body: 
            {
                "type": "/error/invalid_param/required", // String que permite ser mapeado para reconocer el tipo de error desde le cliente.
                "title": "Required param is missing.", // Titulo leible por humanos
                "status": "Bad Request", // Status name
                "code": 400, // Status code
                "detail": "Invalid param. The following param is required but it's missing: 'productId'." // Detalle leible por humanos
            }
        Errores esperados:
            "/error/invalid_param/required" -> No se envió algún query param que es requerido para realizar la consulta. Params obligatorios: ProductId, BrandId y ApplicationDate
            "/error/invalid_param/type" -> El formato del query param enviado no es correcto. Tipos de datos requeridos: ProductId y BrandId tipo numérico. ApplicationDate tipo fecha con el pattern "yyyy-MM-dd-HH.mm.ss".
        
        Status 404 -> NOT_FOUND - No se encontró registro con la información enviada.
        Example Body:
            {
                "type": "/error/not_found/price",
                "title": "Price not found.",
                "status": "Not Found",
                "code": 404,
                "detail": "Price error not found with values BrandId: '1',  ProductId: '2', ApplicationDate: '2020-06-16-01.00.00'."
            }
        Errores esperados:
            "/error/not_found/price" -> No se encontró registro de precio con la data enviada.
        
        Status 500 -> INTERNAL_SERVER_ERROR - Cualquier tipo de error desconocido del lado del servidor que no corresponda con el comportamiento esperado. (Error de parseo de fecha, error de conexión con la Base de datos, etc).
        Example Body:
            {
                "type": "/error/internal_server_error",
                "title": "Price not found.",
                "status": "Internal Server Error",
                "code": 500,
                "detail": "Error parsing string to date value: 1234"
            }
        Errores esperados:
            "/error/internal_server_error" -> Cualquier error del server desconocido es devuelto con un formato amigable.
            "/error/internal_server_error/database_access" -> Error en la conexion a la base de datos, consulta sql, transaccion, etc.

---

Para ejecutar el proyecto:

```sh
docker build -t app/test-java-1.0.0 .
docker run -p 8080:8080 app/test-java-1.0.0
```

Casos de uso:

**Obtener un precio de un producto existente**

    curl --location --request GET 'http://localhost:8080/v1/prices?productId=35455&brandId=1&applicationDate=2020-06-16-01.00.00'
    Status:  200
    Response Body: {"product_id":35455,"brand_id":1,"pricing":{"currency_id":"EUR","perceived_value":{"id":4,"total":38.95},"fee":{"id":4,"total":40.0},"application_from":"2020-06-15-16.00.00","application_to":"2020-12-31-23.59.59"}}

**Obtener un precio de un producto inexistente**

    curl --location --request GET 'http://localhost:8080/v1/prices?productId=1&brandId=1&applicationDate=2020-06-16-01.00.00'
    Status:  404
    Response Body: {"type":"/error/not_found/price","title":"Price not found.","status":"Not Found","code":404,"detail":"Price error not found with values BrandId: '1',  ProductId: '1', ApplicationDate: '2020-06-16-01.00.00'."}


**Obtener un precio de un producto con una fecha/hora que posee mas de un registro y devuelve el de mayor prioridad:**

    curl --location --request GET 'http://localhost:8080/v1/prices?productId=35455&brandId=1&applicationDate=2020-06-15-16.00.00'
    Status:  200
    Response Body: {"product_id":35455,"brand_id":1,"pricing":{"currency_id":"EUR","perceived_value":{"id":4,"total":38.95},"fee":{"id":4,"total":40.0},"application_from":"2020-06-15-16.00.00","application_to":"2020-12-31-23.59.59"}}

**Error en los parametros:**

    curl --location --request GET 'http://localhost:8080/v1/prices?productId=sss&brandId=1&applicationDate=2020-06-15-16.00.00'
    Status:  400
    Response Body: {"type":"/error/invalid_param/type","title":"Invalid type.","status":"Bad Request","code":400,"detail":"Invalid param. Param type expected: 'long'. Param: 'productId'."}

---