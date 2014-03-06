package com.fyp.guice.example.application;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;

import java.io.*;

import com.fyp.guice.example.domain.*;
import com.fyp.guice.example.domain.interfaces.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.*;

import com.fyp.guice.example.bindings.*;
import com.google.inject.*;

public class Start{
	
	public static final int PAYPAL = 0;
	public static final int GOOGLE_WALLET = 1;
	public static final int FALABELLA = 2;
	public static final int CUSTOM = 3;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		int reintentos = 0;
//		while (reintentos <= 2){
//			reintentos = reintentos + 1;
		
			String pathToJar = "/Users/sebastian/Documents/workspace/guice_exercise/lib/guice-3.0/processors.jar";
			JarFile jarFile = new JarFile(pathToJar);
	        Enumeration<JarEntry> e = jarFile.entries();
	
	        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
	        URLClassLoader cl = URLClassLoader.newInstance(urls);
	        
	        HashMap<String, Class> clases = new HashMap<String, Class>();
	
	        while (e.hasMoreElements()) {
	            JarEntry je = (JarEntry) e.nextElement();
	            if(je.isDirectory() || !je.getName().endsWith(".class")){
	                continue;
	            }
	            // -6 because of .class
	            String className = je.getName().substring(0,je.getName().length()-6);
	            if (!clases.containsKey(className))
		        {	className = className.replace('/', '.');
		            Class<?> c = cl.loadClass(className);
		            clases.put(className, c);
		        }
	        }
			
			// Leer tipo de tarjeta
			BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
			int inCardType = 0;
			System.out.println("Ingrese su tipo de tarjeta: ");
			System.out.println("0- Paypal ");
			System.out.println("1- Google Wallet ");
			System.out.println("2- Falabella ");
			System.out.println("3- Custom ");
			System.out.println(" ");
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
	
		    //if (args.length > 0) {
			//	injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)Class.forName(args[0])));
			//}
			//else{
	    	    switch (inCardType) {
	    			case PAYPAL:
	    				injector = Guice.createInjector(new BillingModule(PaypalCreditCardProcessor.class));
	    				System.out.println("Tarjeta ingresada: Paypal");
	    				break;
	    
	    			case GOOGLE_WALLET:
	    				injector = Guice.createInjector(new BillingModule(GoogleWalletProcessor.class));
	    				System.out.println("Tarjeta ingresada: GoogleWallet");
	    				break;
	    				
	    			case FALABELLA:
	    				injector = Guice.createInjector(new BillingModule(FalabellaCreditCardProcessor.class));
	    				System.out.println("Tarjeta ingresada: Falabella");
	    				break;
	    				
	    			default:
	    				System.out.println("Ingrese su clase: ");
	    				String clase = null;
	    				try {
	    					clase = lectura.readLine();
	    				} catch (IOException ex) {
	    					// TODO Auto-generated catch block
	    					ex.printStackTrace();
	    				}
	    				injector = Guice.createInjector(new BillingModule((Class<? extends CreditCardProcessor>)clases.get(clase)));
	    				System.out.println("Tarjeta ingresada: Custom");
	    				break;
				}	    
			//}
	
		    /*
		     * Now that we've got the injector, we can build objects.
		     */
		    
		    BillingService billingService = injector.getInstance(BillingService.class);
		    
		    PizzaOrder order = new PizzaOrder(100);
		    CreditCard creditCard = new CreditCard(inCardType, "Juan David Castaneda", 11, 2010);
	
		    billingService.chargeOrder(order, creditCard);
		    
		  }
//	}
	
}
