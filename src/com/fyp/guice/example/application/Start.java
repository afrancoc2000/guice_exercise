package com.fyp.guice.example.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.fyp.guice.example.bindings.BillingModule;
import com.fyp.guice.example.domain.BillingService;
import com.fyp.guice.example.domain.CreditCard;
import com.fyp.guice.example.domain.FalabellaCreditCardProcessor;
import com.fyp.guice.example.domain.GoogleWalletProcessor;
import com.fyp.guice.example.domain.PaypalCreditCardProcessor;
import com.fyp.guice.example.domain.PizzaOrder;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Start{
	
	public static final int PAYPAL = 0;
	public static final int GOOGLE_WALLET = 1;
	public static final int FALABELLA = 2;
	public static final int CUSTOM = 3;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
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
	    				
	    				System.out.println("Ingrese la ruta al jar que contiene su clase: ");
	    				System.out.println("Recuerde que su clase debe implementar la interface CreditCardProcessor ");
	    				String pathToJar = null;
	    				try {
	    					pathToJar = lectura.readLine();
	    				} catch (IOException ex) {
	    					// TODO Auto-generated catch block
	    					ex.printStackTrace();
	    				}
	    				
	    				JarFile jarFile = new JarFile(pathToJar);
	    		        Enumeration<JarEntry> e = jarFile.entries();
	    		
	    		        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
	    		        URLClassLoader cl = URLClassLoader.newInstance(urls);
	    		        
	    		        HashMap<String, Class> clases = new HashMap<String, Class>();
	    		
	    		        while (e.hasMoreElements()) {
	    		            JarEntry je = e.nextElement();
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
	    		        jarFile.close();	    				
	    				
	    				System.out.println("Ingrese su clase: ");
	    				String clase = null;
	    				try {
	    					clase = lectura.readLine();
	    				} catch (IOException ex) {
	    					// TODO Auto-generated catch block
	    					ex.printStackTrace();
	    				}
	    				injector = Guice.createInjector(new BillingModule(clases.get(clase)));
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
