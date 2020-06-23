# Optical character recognition implemented in java
This was an assignment for a Multivariable calculus course I took in university. 

The goal of the project was to take an input from the user (A drawing of a single character), reduce it to an 8x8 grid and use a trained neural network to identify it.


Based on Michael Nielsen's [Neural Networks and Deep Learning](http://neuralnetworksanddeeplearning.com/index.html) book.


## About the app




## How to run it


### Run training
```
mvn clean compile exec:java -Dexec.mainClass=
```

### Execute the app
```
mvn clean compile exec:java -Dexec.mainClass= -Dexec.cleanupDaemonThreads=false
```


## Available properties

#### Mysql

| Property Name    | Default Value |
| --------------   | ------------- |
| DATABASE_USER    |               |
| DATABASE_PASS    |               |
| DATABASE_HOST    | localhost     |
| DATABASE_PORT    | 3306          |
| DATABASE_SCHEMA  | charlie       |

#### App settings

| Property Name  | Default Value |
| -------------- | ------------- |
|   APP_PORT     |     4567      |
