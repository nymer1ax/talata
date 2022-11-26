# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**






## API ENDPOINTS

#### GET Listado de peliculas populares: La paginación esta por defecto de cero a 20, page = 0, size = 20 as default params

Este endpoint acepta los siguientes query params
- ?page=1, este query params se refiere a la pagina que quieres usar del servicio de Movie Database Api.
- ?pagePagination=0, En la paginación de este query, esta se refiere a la pagina seleccionada
- ?size=20, En la paginación de este query, size se refiera al numero de elementos mostrados en cada pagina.

- Endpoint: **GET http://localhost:8080/api/movies**
- Response de ejemplo:
```
{
    "codigoResultado": "200",
    "descripcionRespuesta": "Este es el listado de las peliculas más populares ahora mismo: ",
    "fecha": "2022-11-26T14:55:58.997297",
    "result": {
        "content": [
            {
                "adult": false,
                "backdrop_path": "/67HggiWaP9ZLv5sPYmyRV37yAJM.jpg",
                "genre_ids": [
                    35,
                    18,
                    10749
                ],
                "id": 13,
                "original_language": "en",
                "original_title": "Forrest Gump",
                "overview": "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.",
                "popularity": 57.8,
                "poster_path": "/saHP97rTPS5eLmrLQEcANmKrsFl.jpg",
                "release_date": "1994-06-23",
                "title": "Forrest Gump",
                "video": false,
                "vote_average": 8.5,
                "vote_count": 23639
            },
            {
                "adult": false,
                "backdrop_path": "/lXhgCODAbBXL5buk9yEmTpOoOgR.jpg",
                "genre_ids": [
                    12,
                    14,
                    28
                ],
                "id": 122,
                "original_language": "en",
                "original_title": "The Lord of the Rings: The Return of the King",
                "overview": "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor from Sauron's forces. Meanwhile, Frodo and Sam take the ring closer to the heart of Mordor, the dark lord's realm.",
                "popularity": 96.836,
                "poster_path": "/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
                "release_date": "2003-12-01",
                "title": "The Lord of the Rings: The Return of the King",
                "video": false,
                "vote_average": 8.5,
                "vote_count": 20580
            },
            {
                "adult": false,
                "backdrop_path": "/sw7mordbZxgITU877yTpZCud90M.jpg",
                "genre_ids": [
                    18,
                    80
                ],
                "id": 769,
                "original_language": "en",
                "original_title": "GoodFellas",
                "overview": "The true story of Henry Hill, a half-Irish, half-Sicilian Brooklyn kid who is adopted by neighbourhood gangsters at an early age and climbs the ranks of a Mafia family under the guidance of Jimmy Conway.",
                "popularity": 42.989,
                "poster_path": "/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg",
                "release_date": "1990-09-12",
                "title": "GoodFellas",
                "video": false,
                "vote_average": 8.5,
                "vote_count": 10689
            },
            {
                "adult": false,
                "backdrop_path": "/fQq1FWp1rC89xDrRMuyFJdFUdMd.jpg",
                "genre_ids": [
                    10749,
                    35
                ],
                "id": 761053,
                "original_language": "en",
                "original_title": "Gabriel's Inferno: Part III",
                "overview": "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard. Watch Here : https://classic-blog.udn.com/mobile/aaef2970/177226874",
                "popularity": 40.811,
                "poster_path": "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                "release_date": "2020-11-19",
                "title": "Gabriel's Inferno: Part III",
                "video": false,
                "vote_average": 8.5,
                "vote_count": 993
            },
            {
                "adult": false,
                "backdrop_path": "/zoVeIgKzGJzpdG6Gwnr7iOYfIMU.jpg",
                "genre_ids": [
                    18,
                    10749
                ],
                "id": 11216,
                "original_language": "it",
                "original_title": "Nuovo Cinema Paradiso",
                "overview": "A filmmaker recalls his childhood, when he fell in love with the movies at his village's theater and formed a deep friendship with the theater's projectionist.",
                "popularity": 27.047,
                "poster_path": "/8SRUfRUi6x4O68n0VCbDNRa6iGL.jpg",
                "release_date": "1988-11-17",
                "title": "Cinema Paradiso",
                "video": false,
                "vote_average": 8.5,
                "vote_count": 3521
            }
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 20,
            "paged": true,
            "unpaged": false
        },
        "totalPages": 1,
        "totalElements": 20,
        "last": true,
        "size": 20,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "numberOfElements": 20,
        "first": true,
        "empty": false
    }
}
```

#### Get pelicula por Id
Para este ejemplo hemos usado la pelicula: 505177, como id 
- Endpoint: **GET http://localhost:8080/api/movies/:id**
```
{
    "codigoResultado": "202",
    "descripcionRespuesta": "Se ha encontrado con exito la pelicula: 505177",
    "fecha": "2022-11-26T14:48:27.766470",
    "result": {
        "adult": false,
        "backdrop_path": "/9QWBstBOwv36cuv0hK4esNyFnvo.jpg",
        "genre_ids": null,
        "id": 505177,
        "original_language": "en",
        "original_title": "10 x 10",
        "overview": "Lewis is an outwardly ordinary guy, but in reality he is hiding an obsession – revenge – against Cathy. Lewis kidnaps Cathy in broad daylight and takes her to his home, where he locks her in a soundproof cell and attempts to extract a dark secret from her past.",
        "popularity": 15.617,
        "poster_path": "/egMETBYual2JtfFGigXTA0tGkME.jpg",
        "release_date": "2018-04-13",
        "title": "10x10",
        "video": false,
        "vote_average": 5.258,
        "vote_count": 463
    }
}
```

#### POST Y DELETE
Importante, antes de poder dar rating a una pelcula se debe tener un guest_session_id,

La cual se crea de la siguiente manera, 
Se llama al endpoint /authentication y se le agrega un query params con el api key del proyecto, el lo encuentras en el properties.
**IMPORTANTE** Este guest_session se vence cada 24, por lo que si se vence debes generar un nuevo. Y para ejecutar operaciones /POST o /DELETE es necesario. 
- Endpoint: **GET https://api.themoviedb.org/3/authentication/guest_session/new?api_key=b367c4d7f17840d073f27e0d2ed45f0d**

RESPONSE
```
{
    "success": true,
    "guest_session_id": "a33ccfbbcf478f75fceae0aaaafd49c4",
    "expires_at": "2022-11-27 20:05:18 UTC"
}  
  
```

POST Dar Rating a una pelicula


if we want to filter we should use
- ENDPOINT: **POST http://localhost:8080/api/movies/:id/rating**
**Path variable:**
-id: Se refiere al id de la pelicula a la cual se le dará rating. para este ejemplo el id será 505177

Query Params: Por defecto esta el siguiente, si se vence se debe realizar lo mencionado arriba.
?guestSessionId = 8f1529e1ab26cf1926f9c934ca239df7

El **BODY** a enviar en esta petición es de tipo JSON y debe ser así. 
```
{
    "value": 8
}
```

RESPONSE
```
{
    "codigoResultado": "201",
    "descripcionRespuesta": "Se ha creado con éxito el rating para la pelicula 505177",
    "fecha": "2022-11-26T15:16:27.355383",
    "result": {
        "adult": false,
        "backdrop_path": "/9QWBstBOwv36cuv0hK4esNyFnvo.jpg",
        "genre_ids": null,
        "id": 505177,
        "original_language": "en",
        "original_title": "10 x 10",
        "overview": "Lewis is an outwardly ordinary guy, but in reality he is hiding an obsession – revenge – against Cathy. Lewis kidnaps Cathy in broad daylight and takes her to his home, where he locks her in a soundproof cell and attempts to extract a dark secret from her past.",
        "popularity": 15.617,
        "poster_path": "/egMETBYual2JtfFGigXTA0tGkME.jpg",
        "release_date": "2018-04-13",
        "title": "10x10",
        "video": false,
        "vote_average": 5.258,
        "vote_count": 463
    }
}
```

DELETE Rating
Si deseamos eliminar el rating de una pelicula podemos utilizar el siguiente endpoint

PathVariable:
-id: Se refiere al id de la pelicula a la cual se le dará rating. para este ejemplo el id será 505177

Query Params: Por defecto esta el siguiente, si se vence se debe realizar lo mencionado arriba.
?guestSessionId = 8f1529e1ab26cf1926f9c934ca239df7


Endpoint:

DELETE  http://localhost:8080/api/movies/:id/rating

RESPONSE
```
{
    "codigoResultado": "201",
    "descripcionRespuesta": "se ha eliminado con exito el rating para la pelicula: 505177",
    "fecha": "2022-11-26T14:45:53.952013",
    "result": {
        "adult": false,
        "backdrop_path": "/9QWBstBOwv36cuv0hK4esNyFnvo.jpg",
        "genre_ids": null,
        "id": 505177,
        "original_language": "en",
        "original_title": "10 x 10",
        "overview": "Lewis is an outwardly ordinary guy, but in reality he is hiding an obsession – revenge – against Cathy. Lewis kidnaps Cathy in broad daylight and takes her to his home, where he locks her in a soundproof cell and attempts to extract a dark secret from her past.",
        "popularity": 15.617,
        "poster_path": "/egMETBYual2JtfFGigXTA0tGkME.jpg",
        "release_date": "2018-04-13",
        "title": "10x10",
        "video": false,
        "vote_average": 5.258,
        "vote_count": 463
    }
}
```


