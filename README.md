# Optical character recognition implemented in java
This was an assignment for a Multivariable calculus course I took in university. 

The goal of the project was to take an input from the user (A drawing of a single character), reduce it to an 8x8 grid and use a trained neural network to identify it.

The most popular and simple approach to OCR problem is based on feed forward neural network with backpropagation learning. The main idea is that we should first prepare a training set and then train a neural network to recognize patterns from the training set. In the training step we teach the network to respond with desired output for a specified input. For this purpose each training sample is represented by two components: possible input and the desired network's output for the input. After the training step is done, we can give an arbitrary input to the network and the network will form an output, from which we can resolve a pattern type presented to the network.


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
