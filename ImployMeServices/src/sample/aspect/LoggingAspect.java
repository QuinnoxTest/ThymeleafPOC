package sample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

		public static final String aspectPackages = "execution(* me.imploy.springboot.controllers.*Controller.*(..))";
		
		/*@Pointcut(aspectPackages)
		private void selectAll(){
			System.out.println("Loaded Aspect");
		}*/
		
		/*@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	    public void requestMapping() {
			System.out.println("requestMapping");
		}*/

	    @Pointcut(aspectPackages)
	    public void methodPointcut() {
	    	System.out.println("AOP Loaded");
	    }
		
		/*@Before("selectAll()")
	    public void beforeAdvice(JoinPoint jp){
	    System.out.println("Before Advice "+jp.getTarget().getClass().getName());
	    }*/
	    
		/*
		@After("selectAll()")
	    public void afterAdvice(JoinPoint jp){
	        System.out.println("After Advice "+jp.getTarget().getClass().getName());
	    }*/
	    
	    @AfterThrowing(pointcut = aspectPackages,throwing="error")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
	    	System.err.println("Error Caught in AOP");
	    	//error.printStackTrace();
	    	System.err.println(error.getMessage());
	    	System.err.println(error.getCause().toString());
	    }
	    
	    @After("methodPointcut()")
	    public void duringAdvice(JoinPoint jp){
	    	System.out.println("After Controller ");
	    } 
	    
	    @Before("methodPointcut()")
	    public void beforeAdvice(JoinPoint jp){
	    	System.out.println("Before Controller "+jp.getSignature());
	    }
	    
}