guice_exercise
==============

Este es un ejercicio para la clase de Frameworks y Patrones de la especializaci�n en desarrollo de software.

Para que el programa funcione correctamente despues de descargar el c�digo se debe crear una librer�a con el nombre "guice-3.0" que contenga todos los jars de guice, los jars los pueden encontrar en la carpeta de lib del proyecto.

Una vez organizado el proyecto se debe correr con la clase start en el paquete com.fyp.guice.example.application

El error en el documento se encuentra en este parrafo:

Dependency Injection
Like the factory, dependency injection is just a design pattern. The core principal is to separate behaviour from dependency resolution. In our example, the RealBillingService is not responsible for looking up the TransactionLog and CreditCardProcessor. Instead, they're passed in as constructor parameters:

Donde dice que la inyecci�n de dependencia es un patr�n de dise�o, la inyecci�n de dependencia no es un patr�n de dise�o es la implementaci�n de la inversi�n de dependencia, un principio de programac��n de software, de SOLID, sin embargo muchos patrones utilizan la inyecci�n de dependencia, como factory method y observer.
