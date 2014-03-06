package com.fyp.guice.example.application;

import java.io.*;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.*;

import com.fyp.guice.example.bindings.*;
import com.google.inject.*;

public class Start{
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		String pathToJar = "/Users/sebastian/Documents/workspace/guice_exercise/lib/guice-3.0/guiceDomain.jar";
		JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class<?> c = cl.loadClass(className);
        }
		
		// Leer tipo de tarjeta
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		int inCardType = 0;
		System.out.println("Ingrese su tipo de tarjeta: ");
		try {
			inCardType = Integer.parseInt(lectura.readLine());
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	    /*
	     * Guice.createInjector() takes your Modules, and returns a new Injector
	     * instance. Most applications will call this method exactly once, in their
	     * main() method.
	     */
		Injector injector;
		
		CardType cardType = null;
		if (inCardType == 0) cardType = CardType.Paypal;
		else if (inCardType == 1) cardType = CardType.GoogleWallet;
		else if (inCardType == 2) cardType = CardType.Falabella;
		else cardType = CardType.Custom;

	    //if (args.length > 0) {
		//	injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)Class.forName(args[0])));
		//}
		//else{
    	    switch (cardType) {
    			case Paypal:
    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
    				System.out.println("Tarjeta ingresada: Paypal");
    				break;
    
    			case GoogleWallet:
    				injector = Guice.createInjector(new BillingModule(GoogleWalletProcessor.class));
    				System.out.println("Tarjeta ingresada: GoogleWallet");
    				break;
    				
    			case Falabella:
    				injector = Guice.createInjector(new BillingModule(FalabellaCreditCardProcessor.class));
    				System.out.println("Tarjeta ingresada: Falabella");
    				break;
    				
    			default:
    				System.out.println("Ingrese su clase: ");
    				String clase = null;
    				try {
    					clase = lectura.readLine();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)Class.forName(clase)));
    				System.out.println("Tarjeta ingresada: Custom");
    				break;
			}	    
		//}

	    /*
	     * Now that we've got the injector, we can build objects.
	     */
	    
	    BillingService billingService = injector.getInstance(BillingService.class);
	    
	    PizzaOrder order = new PizzaOrder(100);
	    CreditCard creditCard = new CreditCard(cardType.getValue(), "Juan David Castaneda", 11, 2010);

	    billingService.chargeOrder(order, creditCard);
	    
	  }
	
}
