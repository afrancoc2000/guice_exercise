guice_exercise
==============

Este es un ejercicio para la clase de Frameworks y Patrones de la especializaci�n en desarrollo de software.

Para que el programa funcione correctamente despues de descargar el c�digo se debe crear una librer�a con el nombre "guice-3.0" que contenga todos los jars de guice, los jars los pueden encontrar en la carpeta de lib del proyecto.

Una vez organizado el proyecto se debe correr con la clase start en el paquete com.fyp.guice.example.application

El programa se corre por consola, ofrece 4 posibilidades, "0- Paypal", "1- Google Wallet", "2- Falabella" y "3- Custom", cuando se elige una de las 3 primeras opciones la aplicaci�n corre el BillingService con el CreditCardProcessor correspondiente a la tarjeta de cr�dito elegida, la cuarta opci�n, Custom, permite cargar un jar en el sistema y elegir una clase para correr como creditCardProcesor para que el sistema funcione correctamente, la clase debe implementar la interface CreditCardProcessor.

El error en el documento se encuentra en este parrafo:

Dependency Injection
Like the factory, dependency injection is just a design pattern. The core principal is to separate behaviour from dependency resolution. In our example, the RealBillingService is not responsible for looking up the TransactionLog and CreditCardProcessor. Instead, they're passed in as constructor parameters:

Donde dice que la inyecci�n de dependencia es un patr�n de dise�o, la inyecci�n de dependencia no es un patr�n de dise�o es la implementaci�n de la inversi�n de dependencia, un principio de programac��n de software, de SOLID, sin embargo muchos patrones utilizan la inyecci�n de dependencia, como factory method y observer.
