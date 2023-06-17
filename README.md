# Lab 3 Informe

Integrantes:

Guillermo de Ipola

Emanuel Nicolas Herrador

Juan Bratti

- [Introducción](#introducción)
    - [Código 1: Guillermo de Ipola](#código-1-guillermo-de-ipola)
    - [Código 2: Juan Bratti](#código-2-juan-bratti)
    - [Código 3: Emanuel Nicolas Herrador](#código-3-emanuel-nicolas-herrador)
- [Desarrollo Segunda Parte](#desarrollo-segunda-parte)

# Introducción

En esta segunda parte del laboratorio 3, lo que hicimos fue ver el trabajo individual de los miembros del grupo y elegir la mejor alternativa para implementar las funcionalidades de la entrega grupal.

Hubo tres caminos distintos para poder adaptar el código del laboratorio al framework Spark. Esos códigos los detallaremos aquí abajo con sus ventajas y sus desventajas.

## Código 1: Guillermo De Ipola

En esta primera alternativa se configuró el contexto de Spark con SparkCOnf y JavaSparkContext.

```cpp
SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local[*]");
JavaSparkContext sc = new JavaSparkContext(conf);
```

La adaptación de Spark se concentro en el archivo Main.java y fue la siguiente:

Se optó por paralelizar de forma distribuida principalmente la lista de suscripciones en una variable `rSubList` para luego con un `flatMap` aplicado sobre la misma, obtener una lista de pares conteniendo por un lado los **feeds** de cada suscriptción y, por otro lado, cualquier error que pueda haber surgido en el procesamiento de los feeds. Devolvemos un iterador sobre estas tuplas. Luego, filtramos de los pares obtenidos, aquellos feeds que pudieron ser procesados correctamente.

```cpp

var feeds = rSubList.flatMap((simpleSubscription -> {
            List<Function0<Tuple2<Feed, String>>> frs = new ArrayList<>();

            for (int j = 0, szj = simpleSubscription.getUrlParametersSize(); j < szj; j++) {
                final int i = j;
                frs.add(() -> {
                    try {
                        return new Tuple2(simpleSubscription.parse(i), null);
                    } catch (InvalidUrlTypeToFeedException e) {
                        return new Tuple2(null,
                                "Invalid URL Type to get feed in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (HttpRequestException e) {
                        return new Tuple2(null,
                                "Error in connection: " + e.getMessage() + " "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (EmptyFeedException e) {
                        return new Tuple2(null,
                                "Empty Feed in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (MalformedURLException e) {
                        return new Tuple2(null,
                                "Malformed URL exception en subscripcion "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (IOException e) {
                        return new Tuple2(null,
                                "IO exception en subscripcion " + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (ParserConfigurationException e) {
                        return new Tuple2(null,
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (ParseException e) {
                        return new Tuple2(null,
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (SAXException e) {
                        return new Tuple2(null,
                                "SAX Exception in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    }
                });
            }

            return frs.iterator();
        })).mapToPair(Function0::apply);

        var parsedFeeds = feeds.filter((feedErrorTuple) -> {
            return feedErrorTuple._2() == null && feedErrorTuple._1() != null;
        }).map(Tuple2::_1);
```

Luego de la obtención de los feeds, se decide si lo que se quiere obtener es un print normal de las noticias, u obtener sus named entities. 

1. En el primer caso, simplemente se utiliza el método `foreach` sobre los feeds obtenidos para hacer un `prettyPrint` de los mismos.
2. En el segundo caso, se vuelve a utilizar el método `flatMap` para obtener un RDD que contenga los artículos de cada feed. Si la lista de artículos no es nula, se devuelve un iterador sobre los mismos. Si es nula, se devuelve Collections.emptyIterator() para descartar esos feeds devolviendo un iterador vacío.
    
    Luego aplicamos otro `flatMap` para obtener las entidades nombradas de cada artículos. Aquí se utiliza `computeNamedEntities`. Si el procesamiento de las entidades no es exitosa, también se devuelve `Collections.emptyIterator()` para descartar esos artículos.
    
    A estas entidades obtenidas, se les aplica un `filter` para descartar las que sean iguales a null.
    
    Luego, con `mapToPair` y `reduceByKey`, creamos pares clave-valor para cada entidad y su frecuencia, y reducirlas al sumarlas.
    
    Finalmente, con `map` y `foreach` se obtienen la información de cada entidad y se imprime por pantalla.
    

```cpp
if(normalPrint) {
            // Filter out feeds and print them
            // Print feed to user
            parsedFeeds.foreach(Feed::prettyPrint);
        } else {
            parsedFeeds.flatMap(feed -> {
                if(feed.getArticleList() != null) {
                    return feed.getArticleList().iterator();
                } else {
                    return Collections.emptyIterator();
                }
            }).flatMap(article -> {
                Heuristic heur = new QuickHeuristic();
                article.computeNamedEntities(heur);
                if(article.getNamedEntityList() != null) {
                    return article.getNamedEntityList().iterator();
                } else {
                    return Collections.emptyIterator();
                }
            }).filter(Objects::nonNull).mapToPair(namedEntity -> new Tuple2<>(namedEntity.getName(), namedEntity)).reduceByKey((n1, n2) -> {
                var n = new NamedEntity(n1.getName(), n1.getCategory(), n1.getFrequency() + n1.getFrequency());
                n.setTheme(n1.getTheme());
                return n;
            }).map(Tuple2::_2).foreach(namedEntity -> {
                System.out.println(namedEntity.getName());
                System.out.println(namedEntity.getFrequency());
                System.out.println(namedEntity.getCategory());
                System.out.println(namedEntity.getTheme());
                System.out.println(namedEntity.getClass().toString());
                System.out.println("-----------");
            });
        }
```

A los errores obtenidos en los procesamientos de los feeds y los artículos, se los imprime al final del programa:

```cpp
// Filter out Errors and print them
        var subscriptionErrors = feeds.filter((feedErrorTuple) -> {
            return feedErrorTuple._2() != null;
        }).map(Tuple2::_2);
        if(!subscriptionErrors.isEmpty()) {
            System.out.println("==================================================");
            System.out.println(
                    "There was a total of " + subscriptionErrors.count() + " errors in the creation of the Feeds:");
            subscriptionErrors.foreach((s) -> {
                System.out.print("  - ");
                System.out.println(s);
            });
        }
```

### Ventajas

Se paraleliza el procesamiento de los feeds, los artículos y se aprovecha reduce para poder computar la frecuencia de las entidades nombradas eficientemente. Buen aprovechamiento de las capacidades de Spark en relación a la computación distribuida.

### Desventajas

SI bien se utilizan las capacidades de Spark en la búsqueda de entidades nombradas desde Main.java, podría haberse expandido su uso a otros objetos y clases como por ejemplo, para la función `computeNamedEntities` que hace el cómputo en sí de las entidades nombradas en la clase *Article*, o para el procesamiento de las suscripciones en las clases del archivo Subscriptions.java.

Si bien tal vez esta adición hubiera ralentizado el funcionamiento en general del programa en nuestro contexto, en un cluster de tamaño considerable podría haber significado una ventaja sobre la complejidad.

## Código 2: Juan Bratti

En esta alternativa se decidió usar SparkConf y JavaSparkContext para configurar el contexto de Spark.

Además, se hizo uso de una clase extra llamada SparkContextHolder que lo que permite es poder trasladar nuestro contexto de spark utilizado en el archivo Main.java a otros objetos y clases. Esta clase lo que hace es tener métodos para poder guardar el respectivo contexto, además de métodos para poder cerrarlo y obtenerlo.

Inicialización de Spark:

```cpp
SparkConf conf = new SparkConf().setAppName("NamedEntity Recognizer").setMaster("local[*]");
SparkContextHolder sparkHolder = new SparkContextHolder();
JavaSparkContext sparkContext = new JavaSparkContext(conf);
sparkHolder.setSparkContext(sparkContext);
```

Clase de Holder extra:

```java
public class SparkContextHolder implements Serializable{
    private static transient JavaSparkContext sparkContext;

    public JavaSparkContext getSparkContext() {
        return sparkContext;
    }

    public void setSparkContext(JavaSparkContext context) {
        sparkContext = context;
    }

    public void closeSparkContext() {
        if (sparkContext != null) {
            sparkContext.close();
        }
    }
}
```

A partir de esto, lo que se hizo fue utilizar Spark en el parseo de suscripciones del archivo JSON que se encuentra en el *filepath* dado, en el procesamiento de los feeds y artículos, y en el cómputo de las entidades nombradas.

Para el parseo de las suscripciones, se creó un objeto de Subscriptions y se llamo a parse, pasando como argumento el filepath y además el objeto que contiene nuestro contexto de Spark.

```java
Subscriptions subscriptions = new Subscriptions();
       try {
            subscriptions.parse(subscriptionsFilePath, sparkHolder);
       } catch (IOException e) {
                subscriptionErrors.add("Error parsing subscriptions file: " + e.getMessage());
       }
```

En el método parse de Subscriptions, se obtuvo el contexto del holder, y se creó un RDD para paralelizar el procesamiento del contenido de nuestro archivo JSON en el *filepath*. Para ello, en nuestro RDD jsonData se carga el contenido del archivo usando métodos de Spark y se aplica `flatMap` al RDD para extraer, usando la librería Gson, el contenido del archivo JSON en una lista de objetos de tipo Subscription (clase intermedia que se usa como auxiliar, tal vez innecesaria).

Luego de esta transformación, se vuelve aplicar `flatMap` para poder pasar los datos de esta clase auxiliar, a nuestra clase `SimpleSubscription`, que es la que usamos a lo largo de toda la implementación. Finalmente se llama a `collect()` para recolectar todos las transformaciones realizadas en cada `SimpleSubscription`, y guardar las mismas en la lista `subscriptionsList` definida en Subscription.Java para ser accedidas más tarde en nuestro programa.

```
JavaSparkContext sparkContext = sparkHolder.getSparkContext();

        JavaRDD<String> jsonData = sparkContext.wholeTextFiles(subscriptionsFilePath).values();

        JavaRDD<Subscription> subscriptionsRDD = jsonData.flatMap(json -> {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Subscription>>() {}.getType();
            List<Subscription> subscriptions = gson.fromJson(json, type);
            return subscriptions.iterator();
        });

        // Extract the required fields
        JavaRDD<SimpleSubscription> simpleSubscriptionsRDD = subscriptionsRDD.map(subscription -> {
            String url = subscription.getUrl();
            String urlType = subscription.getUrlType();
            List<String> urlParams = subscription.getUrlParams();

            SimpleSubscription simpleSubscription = new SimpleSubscription();
            simpleSubscription.setUrl(url);
            simpleSubscription.setUrlType(urlType);

            if (urlType.equals("rss")) {
                simpleSubscription.setParser(new RssParser());
            } else if (urlType.equals("reddit")) {
                simpleSubscription.setParser(new RedditParser());
            }

            for (String param : urlParams) {
                simpleSubscription.addUrlParameter(param);
            }

            return simpleSubscription;
        });

        List<SimpleSubscription> simpleSubscriptions = simpleSubscriptionsRDD.collect();
        for (SimpleSubscription simpleSubscription : simpleSubscriptions) {
            this.addSimpleSubscription(simpleSubscription);
        }
```

Luego de parsear las suscripciones, se prosigue con el parseo de los feeds de cada suscripción.

Para esto, se crea un RDD que contiene la lista de suscripciones y se aplica `flatMap` para que en cada suscripción de la lista, se extraiga su feed con el método parse del objeto `SimpleSubscription`. Se hacen un par de control de errores en el caso de que el parseo del feed no haya sido exitoso.

```java
JavaRDD<SimpleSubscription> subscriptionsRDD = sparkContext.parallelize(subscriptions.getSubscriptionList());

            JavaRDD<Feed> feedsRDD = subscriptionsRDD.flatMap(subscription -> {
                List<Feed> feeds = new ArrayList<>();
                for (int j = 0, szj = subscription.getUrlParametersSize(); j < szj; j++) {
                    try {
                    Feed feed = subscription.parse(j);
                    feeds.add(feed);
                    } catch (InvalidUrlTypeToFeedException e) {
                        subscriptionErrors.add(
                                "Invalid URL Type to get feed in " + subscription.getFormattedUrlForParameter(j));
                    } catch (HttpRequestException e) {
                        subscriptionErrors.add(
                                "Error in connection: " + e.getMessage() + " " + subscription.getFormattedUrlForParameter(j));
                    } catch (EmptyFeedException e) {
                        subscriptionErrors.add(
                                "Empty Feed in " + subscription.getFormattedUrlForParameter(j));
                    } catch (MalformedURLException e) {
                        subscriptionErrors.add(
                                "Malformed URL exception en subscription " + subscription.getFormattedUrlForParameter(j));
                    } catch (IOException e) {
                        subscriptionErrors.add(
                                "IO exception en subscription " + subscription.getFormattedUrlForParameter(j));
                    } catch (ParserConfigurationException e) {
                        subscriptionErrors.add(
                                "Parse error in " + subscription.getFormattedUrlForParameter(j));
                    } catch (SAXException e) {
                        subscriptionErrors.add(
                                "SAX Exception in " + subscription.getFormattedUrlForParameter(j));
                    }
                }
                return feeds.iterator();
            });
```

Luego del `flatMap`, que devuelve un iterador sobre los feeds extraídos, se decide qué hacer de acuerdo a las necesidades del usuario:

1. Si se quiere printear sólo los feeds, se utiliza `foreach` y `prettyPrint` sobre cada uno de ellos para imprimirlos.
    
    ```cpp
    feedsRDD.foreach(feed -> feed.prettyPrint());
    ```
    
2. Si se desean buscar y printear las entidades nombradas:
    
    Usamos un RDD para los artículos, en donde aplicamos la `flatMap` al RDD que contiene los feeds para extraer todos los artículos. Luego, usando un RDD para la lista de las entidades nombradas, se aplica a cada articulo en el RDD anteriormente mencionado, la función `processNamedEntities` con `flatMap` , pasandole el artículo siendo procesado en ese momento + la heurística + el holder del contexto de Spark.
    
    ```java
    JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());
    JavaRDD<List<NamedEntity>> namedEntitiesRDD = articlesRDD.map(article -> processNamedEntities(article, heur, sparkHolder));
    ```
    
    La función `processNamedEntities` llama a computeNamedEntities pasando así el holder con nuestro contexto de Spark. Retorna la lista de entidades nombradas del artículo pasado.
    
    ```java
    public static List<NamedEntity> processNamedEntities(Article article, Heuristic heur, SparkContextHolder sparkHolder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
            // Procesar las entidades nombradas en el artículo utilizando la heurística proporcionada
            article.computeNamedEntities(heur, sparkHolder);
            // Devolver la lista de entidades nombradas encontradas en el artículo
            return article.getNamedEntityList();
        }
    ```
    
    Finalmente, se le pasa el sparkHolder a `computeNamedEntities` para poder usar los métodos mapToPair y reduceByKey en la búsqueda de las entidades. Para ello se tiene un RDD con las palabras del artículo en cuestión, y usando filter, mapToPair y reduceByKey se ponen todas las palabras en una tupla nombre-frecuencia de forma tal que se reduzcan las instancias de las palabras iguales, sumando entre sí sus frecuencias, teniendo una contabilización de todas las entidades distintas en nuestro artículo. Luego se aplica el proceso para ver si la palabra es una entidad o no, categorizandola según la heurística.
    
    ```java
    JavaRDD<String> wordsRDD = sparkContext.parallelize(Arrays.asList(text.split(" ")));
            
    JavaPairRDD<String, Integer> entityWordsRDD = wordsRDD
                .filter(s -> h.isEntity(s))
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((freq1, freq2) -> freq1 + freq2);
    
                JavaRDD<NamedEntity> namedEntitiesRDD = entityWordsRDD.map(tuple -> {
                String name = tuple._1();
                NamedEntity ne = this.getNamedEntity(name);
    
                if (ne == null){
                    Class<? extends NamedEntity> categoryClass = h.getCategory(name);
                    if(categoryClass == null) {
                        categoryClass = OtherEntityOtherThemes.class;
                    }
                    ne = categoryClass.getDeclaredConstructor().newInstance();
                    ne.setFrequency(tuple._2());
                    ne.setName(name);
                }
                return ne;
            });
    ```
    
    Finalmente se llama a `collect()` para poder devolver la lista de entidades del artículo.
    
    ```
    List<NamedEntity> namedEntities = namedEntitiesRDD.collect();
    this.namedEntityList.addAll(namedEntities);
    ```
    
    y en Main.java se imprime esta lista con usando `foreach` en cada elemento del RDD que contiene la lista de entidades nombradas
    
    ```
    namedEntitiesRDD.foreach(namedEntitiesList -> {
                        for (NamedEntity namedEntity : namedEntitiesList) {
                            System.out.println(namedEntity.getName());
                            System.out.println(namedEntity.getFrequency());
                            System.out.println(namedEntity.getCategory());
                            System.out.println(namedEntity.getTheme());
                            System.out.println(namedEntity.getClass().toString());
                            System.out.println("-----------");
                        }
                    });
    ```
    

### Ventajas

Se pudo aplicar el contexto de Spark a varios de los objetos y clases relacionados con el procesamiento de algún tipo de dato: de suscripciones, de feeds y artículos, de entidades nombradas.

### Desventajas

El código podría haberse escrito de forma más corta, clara y eficiente, además de que creemos que es la implementación que más tarda en procesar lo pedido debido a que se usa Spark en muchos objetos y clases (por ejemplo, “estaría de más” usarlo en `computeNamedEntities`).

## Código 3: Emanuel Nicolas Herrador

En esta implementación se decidió usar SparkSession para configurar el contexto de Spark. Ésta decisión fue para poder facilitar el uso del mismo contexto de Spark en otros objetos y clases que no sean aquellos incluidos en el archivo de Main.java.

```cpp
// Configuración de la sesión de Spark
SparkSession sparkSession = SparkSession
          .builder()
          .appName("feedReader")
          .master("local[100]")
          .getOrCreate();
JavaSparkContext spark = new JavaSparkContext(sparkSession.sparkContext());
```

El approach que hubo en este código fue intentar expandir las funcionalidades de Spark a otros archivos, no sólo a Main.java. Para ello, también se adaptó el código de el parseo de las suscripciones en el archivo Subscriptions.java.

Lo primero que se hizo fue crear un objeto Subscriptions para hacer el posterior parseo del archivo que contiene las urls, paralelizando el poder de cómputo.

```cpp
Subscriptions subscriptions = new Subscriptions(sparkSession);
subscriptions.parse(subscriptionsFilePath);
```

Veamos que se le pasa la sesión de Spark a la llamada de Subscriptions. Ésto es porque se incluyó un campo del objeto para contener a nuestro contexto de Spark y poder utilizarla en el método *****parse*****.

```java
public Subscriptions(SparkSession sparkSession) {
        super();
        this.subscriptionsList = new ArrayList<>();
				// Nuevo campo!
        this.sparkSession = sparkSession;
    }
```

De esta forma, a la hora de llamar al método parse de subscriptions, si el contexto de Spark no es null, podemos paralelizar el funcionamiento de la función de la siguiente forma:

Se agrega a `arrObjString` la representación JSON de cada objeto dentro de nuestro archivo que se encuentra en el *filepath* (archivo del que queremos sacar los campos que nos interesan) y con esa información se crea una Dataset de Spark para poder convertir la lista `arrObjString` en algo que Spark pueda paralelizar.

Con esta nueva variable `objStringDataset` y su posterior transformación con `flatMap`, podemos empezar a crear las SimpleSubscriptions, extrayendo de los strings en el dataset, los campos que son de nuestro interés. Con todas las SimpleSubscriptions hacemos una lista de las mismas que se devuelve al llamar `collect()` .

```java
// Preparo la lista de JSONObject a paralelizar
// Considero Strings porque JSONObject no es Serializable
List<String> arrObjString = new ArrayList<>();
for (int i = 0, szi = arr.length(); i < szi; i++)
    arrObjString.add(arr.getJSONObject(i).toString());
    JavaRDD<String> objStringDataset = sparkSession.createDataset(arrObjString, Encoders.bean(String.class)).javaRDD();

    List<SimpleSubscription> simpleSubscriptionList = objStringDataset
    // Creo la simpleSubscription y la instancio
         .flatMap(objString -> {
             JSONObject obj = new JSONObject(objString);

             SimpleSubscription simpleSubscription = new SimpleSubscription();
             simpleSubscription.setUrl(obj.getString("url"));
             String urlType = obj.getString("urlType");
             simpleSubscription.setUrlType(urlType);

             // Inyectar parser adecuado
             if (urlType.equals("rss")) {
                  simpleSubscription.setParser(new RssParser());
             } else if (urlType.equals("reddit")) {
                  simpleSubscription.setParser(new RedditParser());
             }

             JSONArray arrUrlParams = obj.getJSONArray("urlParams");
             for (int j = 0, szj = arrUrlParams.length(); j < szj; j++)
                  simpleSubscription.addUrlParameter(arrUrlParams.getString(j));

             return Collections.singletonList(simpleSubscription).iterator();
         })
         // Obtengo la lista de simpleSubscriptions
         .collect();

    // Seteo la lista obtenida
    setSubscriptionsList(simpleSubscriptionList);
```

Así es como se adaptó Spark para el parseo de las suscripciones.

Luego, se utilizó un RDD  de SimpleSubscriptions para poder paralelizar la extracción de los feeds de la lista de suscripciones. Se uso `flatMap` para poder transformar los datos del RDD tuplas del tipo feed-error y así extraer los feeds de cada suscripción, expresando así en la tupla si se produce un error en el parseo. 

```java
// Paralelizo la lista de las subscripciones para hacerlo de forma concurrente
JavaRDD<SimpleSubscription> subscriptionList = spark.parallelize(subscriptions.getSubscriptionList());
// Obtengo todos los feeds
// Se consideran tuplas (feed, error). Una es null y la otra es dato (se usa
// para diferenciar)
JavaRDD<Tuple2<Feed, String>> feeds = subscriptionList
       // Separo las subscripciones por sus parámetros
       .flatMap(simpleSubscription -> {
            List<Tuple2<SimpleSubscription, Integer>> feedConstructorOptionsList = new ArrayList<>();
            for (int i = 0, szi = simpleSubscription.getUrlParametersSize(); i < szi; i++)
               feedConstructorOptionsList.add(new Tuple2<>(simpleSubscription, i));
            return feedConstructorOptionsList.iterator();
        })
        // Obtengo el feed en base a los parámetros considerados (subscripción y
        // urlParameter)
        // Se devuelve en el formato (feed, error) siendo solo una null en cada tupla
        .flatMap(feedOptions -> {
             try {
                Feed actualFeed = feedOptions._1().parse(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(actualFeed, null)).iterator();
                    } catch (InvalidUrlTypeToFeedException e) {
                        String actualError = "Invalid URL Type to get feed in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (IOException e) {
                        String actualError = "IO exception in subscription "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (HttpRequestException e) {
                        String actualError = "Error in connection: " + e.getMessage() + " "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (ParserConfigurationException | ParseException e) {
                        String actualError = "Parse error in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (SAXException e) {
                        String actualError = "SAX Exception in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (EmptyFeedException e) {
                        String actualError = "Empty Feed in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    }
                });
```

De los pares feed-error obtenidos, se pule el RDD resultante para eliminar aquellos feeds nulos o que contengan errores.

```java
// Preparo la lista de feeds obtenidos
        JavaRDD<Feed> feedList = feeds
                .filter(actualFeed -> actualFeed._1() != null)
                .flatMap(actualFeed -> Collections.singletonList(actualFeed._1()).iterator());

        // Preparo la lista de errores que sucedieron
        JavaRDD<String> errorList = feeds
                .filter(actualFeed -> actualFeed._2() != null)
                .flatMap(actualFeed -> Collections.singletonList(actualFeed._2()).iterator());
```

Finalmente, si se desea únicamente hacer un printeo de los feeds, se llama a `prettyPrint` para cada feed no nulo usando `foreach`.

```java
// Muestra los feeds al usuario
feedList.foreach(Feed::prettyPrint);
```

Si se desea obtener las entidades nombradas, se procesan las entidades en un RDD aparte que nace de la transformación del RDD que contiene la lista de feeds, luego de aplicarles `flatMap` a cada articulo de la lista de artículos de cada feed. Luego se procede a imprimir por pantalla la información de cada entidad.

```java
// Heurística en uso
            Heuristic heuristicUsed = new QuickHeuristic();

            JavaRDD<namedEntity.entities.NamedEntity> namedEntities = feedList
                    // Obtengo todos los artículos
                    .flatMap(feed -> feed.getArticleList().iterator())
                    // Obtengo las namedEntity
                    .flatMap(article -> {
                        article.computeNamedEntities(heuristicUsed);
                        return article.getNamedEntityList().iterator();
                    });

            // Muestro las namedEntity en pantalla
            namedEntities.foreach(namedEntity -> {
                System.out.println(namedEntity.getName());
                System.out.println(namedEntity.getFrequency());
                System.out.println(namedEntity.getCategory());
                System.out.println(namedEntity.getTheme());
                System.out.println(namedEntity.getClass().toString());
                System.out.println("-----------");
            });
```

Finalmente se imprimen los errores que se hayan generado.

```java
if (!errorList.isEmpty()) {
            System.out.println("==================================================");
            System.out.println(
                    "There was a total of " + errorList.count() + " errors in the creation of the Feeds:");
            errorList.foreach(error -> System.out.println("  - " + error));
        }
```

### Ventajas

La ventaja de este código es que se también se ha podido expandir el uso de Spark en otros contextos que no sean los del archivo *********Main.java*********. Por ejemplo, se pudo paralelizar el parseo de las suscripciones, por lo que en un cluster de máquinas decente, se podría haber aumentado aún más la eficiencia de nuestro programa.

Además, se cree que si bien se expandió el uso de Spark en otros objetos, también la complejidad de la misma es la más óptima.

# Desarrollo Segunda Parte

El código que decidimos utilizar fue el código 3 de nuestro compañero Emanuel Nicolas Herrador. 

Sobre la implementación de nuestro compañero, los cambios principales que realizamos fueron en Main.java; se agregó la funcionalidad que busca los artículos que contengan una determinada palabra o entidad nombrada.

Recapitulando la implementación de nuestro compañero, se obtienen los feeds en un RDD de artículos (luego del parseo ya mencionado y detallado anteriormente):

```cpp
JavaRDD<Article> articleList = feedList
// Obtengo todos los artículos
                .flatMap(feed -> feed.getArticleList().iterator());
```

y se decide lo siguiente, implementando la nueva funcionalidad:

1. Si lo que se quiere es obtener los artículos de los feeds impresos, se indicará que se debe ingresar una oración/palabra/entidad para buscar los artículos que contengan esa oración o palabra clave.
    
    Para esto, se usa un objeto Scanner para leer lo ingresado por el usuario. Lo que se haya ingresado, se guarda en una variable `rawSearchTerms` que luego se utiliza para dividir la cadena contenida en palabras y almacenar cada una de ellas en un set de strings `searchTerms`.
    
    ```cpp
    // Obtener el input de búsqueda sobre los feeds por parte del usuario
    System.out.println("=====================  ¿Qué quiere buscar? Escríbalo en una oración y aprete Enter =====================");
    Scanner scanner = new Scanner(System.in);
    String rawSearchTerms = scanner.nextLine();
    Set<String> searchTerms = new java.util.HashSet<>(Collections.emptySet());
    
    var terms = rawSearchTerms.split(" ");
    Collections.addAll(searchTerms, terms);
    
    scanner.close();
    System.out.println("===================== Solicitud recibida con éxito. La estamos procesando. =====================");
    ```
    
    Luego, usando `flatMap` sobre el RDD que contiene nuestros artículos, se aplica a cada artículo la función `computeNamedEntities`. Además, se crea para cada entidad nombrada un par artículo-entidad_nombrada. Estos pares se guardan en la lista `namedEntityForArticleList` . La llamada a `flatMap` devuelve un iterador para cada elemento de `namedEntityForArticleList` y guarda esos iteradores en la variable `sortedArticle`. 
    
    ```cpp
    List<Article> sortedArticles = articleList
                        // Obtengo pares (artículo, entidad)
                        .flatMap(article ->{
                            // Computo las entidades del artículo
                            article.computeNamedEntities(heuristicUsed);
                            
                            List<NamedEntity> namedEntityFullList = article.getNamedEntityList();
                            List<Tuple2<Article, NamedEntity>> namedEntityForArticleList = new ArrayList<>();
                            
                            for(NamedEntity ne : namedEntityFullList){
                                namedEntityForArticleList.add(new Tuple2<>(article, ne));
                            }
    
                            return namedEntityForArticleList.iterator();
                        })
    ```
    
    A esta variable (`sortedArticle`) luego le aplicamos:
    
    - filter, para poder filtrar los pares de los artículos que tienen en sus entidades algunas de las palabras ingresadas por el usuario.
        
        ```cpp
        // Filtro aquellos pares cuya entidad esté en la búsqueda del usuario
        .filter(entityForArticle -> searchTerms.contains(entityForArticle._2().getName()))
        ```
        
    - mapToPair para poder cambiar en los pares restantes, el campo de entidad nombrada por su frecuencia.
        
        ```cpp
        // Cambio esa NamedEntity por su frecuencia
        .mapToPair(entityForArticle -> new Tuple2<>(entityForArticle._1(), entityForArticle._2().getFrequency()))
        ```
        
    - reduceByKey para poder sumar las frecuencias de cada artículo y obtener el número total de instancias por artículo en los pares
        
        ```cpp
        // Sumo las frecuencias para cada artículo y obtengo su número para ordenar
        .reduceByKey(Integer::sum)
        ```
        
    - mapToPair y sortByKey de nuevo para poder ordenar los pares según el número de ocurrencias (frecuencia) de cada artículo de forma descendente
        
        ```cpp
        // Swapeo para poder ordenar basándonos en el número de ocurrencias
        .mapToPair(Tuple2::swap)
        // Ordeno descendentemente
        .sortByKey(false)
        ```
        
    - y finalmente se usa map para quedarme sólo con los artículos y printearlos, y collect para obtener la lista de artículos respectiva.
        
        ```cpp
        // Me quedo solo con los artículos a printear
        .map(Tuple2::_2)
        // Obtengo la lista para printear
        .collect();
        ```
        
    
    Luego, se muestran los artículos ordenados (usando `prettyPrint`) según la cantidad de veces que aparecen las palabras ingresadas por el usuario en relación a las entidades nombradas de cada artículo.
    
    ```cpp
    // Muestra los artículos al usuario
    for(Article article : sortedArticles){
       article.prettyPrint();
    }
    ```
    
2. Si lo que se quiere es printear solamente las entidades nombradas de todos los artículos, simplemente lo que se hace es usar `flatMap` para iterar sobre cada elemento del RDD que contiene los artículos (`articleList`), y aplicar `computeNamedEntities` con la heurística deseada para obtener la lista de entidades nombradas de cada artículo, y con `collect` y un `for`, imprimir todas ellas.
    
    ```cpp
    // Obtengo las namedEntity
    List<NamedEntity> namedEntities = articleList
             .flatMap(article -> {
                  article.computeNamedEntities(heuristicUsed);
                  return article.getNamedEntityList().iterator();
              })
              // Obtengo la lista de las entidades
              .collect();
                
    // Muestro las namedEntity en pantalla
    for(NamedEntity namedEntity : namedEntities){
       System.out.println(namedEntity.getName());
       System.out.println(namedEntity.getFrequency());
       System.out.println(namedEntity.getCategory());
       System.out.println(namedEntity.getTheme());
       System.out.println(namedEntity.getClass().toString());
       System.out.println("-----------");
    }
    ```
    

Para el desarrollo de esta parte, no se hizo uso de inteligencias artificiales ni tampoco nos basamos en proyectos de ejemplo.