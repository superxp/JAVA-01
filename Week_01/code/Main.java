import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author necho.duan
 * @title: Main
 * @description:
 */
public class Main {
     public void execUte(){
         MyClassLoader myClassLoader = new MyClassLoader(this.getClass().getResource("./").getPath());
         try {
             Class classX  =      myClassLoader.loadClass("Hello");
             Object obj =  classX.newInstance();
             Method[] methods =     classX.getDeclaredMethods();
             for(Method method :methods){
                 Object[] param = new Object[]{};
                 method.invoke(obj,param);
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         }
     }


     public static void main(String [] args ){
       new Test().execUte();
     }
}
