import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.*;
import java.net.URLClassLoader;
import javax.tools.*;


class B{
 List<String> stA = new ArrayList<>();

 void set(){
  stA.add("class spam{\n public static void main(String[] mungaru_male){\n");
  for(int i =0;i<99;i++) stA.add(" ");
  stA.add("            }\n}");
 }
 void insert(String st,int ind){
  stA.add(ind,st);
 }

 public void t(){
  Path p = Paths.get(System.getProperty("user.dir")+"\\SPAM","spam.java");
try{
  if(Files.exists(p)){
   Files.write(p,stA); }
 //else System.out.println("File check mado. Write part");
  }
  catch(IOException e) {
   System.out.println(e.getMessage());
  }
}
  
}

class A {
   
  public static void main(String[] a) {
     System.out.println("----------JAVA INTERACTIVE INTERPRETER--------");
     Main();
}


   public static void Main(){
    B setnew = new B();
    setnew.set();

   // <Append file> creation
     String cwd = System.getProperty("user.dir");
     Path p = Paths.get(cwd+"\\SPAM", "spam.java");
     try {
     if(!Files.exists(p))   Files.createFile(p);
    }
     catch (Exception e) {
         e.getMessage();
     }

   // loop >>> and input
   Scanner sc = new Scanner(System.in);
   int j=1;
   String st = new String();
   while(true){
    System.out.print(">>>");
    st = sc.nextLine();
    if(st.equalsIgnoreCase("EXIT")){
     System.out.println("TA-TA");
     sc.close();
     System.exit(0);
    }
    if(st.equalsIgnoreCase("exe")) break;
    j+=1;
   
    // obj to write to
    setnew.insert(st,j);
    setnew.t(); 
   
   }
    String jFP = cwd+"\\SPAM\\spam.java";
 
   try {
       // Compile the Java file
       compileJavaFile(jFP);

       // Run the compiled class
       runCompiledClass("spam");
   } catch (Exception e) {
       e.printStackTrace();
   }

   }



  private static void compileJavaFile(String filePath) {

      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

      if (compiler == null) 
          return;

      DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

      try (
          StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null)) {
          Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(filePath);

          String outputDirectory = "output";
          Files.createDirectories(Path.of(outputDirectory));

          Iterable<String> options = Arrays.asList("-d", outputDirectory);

          compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits).call();

          diagnostics.getDiagnostics().forEach(System.out::println);

        //  System.out.println("Compilation completed.");

      } catch (IOException e) {
          e.printStackTrace();
      }
  }




 private static void runCompiledClass(String className) {
        try {
            // Create a URLClassLoader with the specified classpath
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("output/").toURI().toURL()});

            // Load the class dynamically
            Class<?> loadedClass = classLoader.loadClass(className);

            // Find and invoke the main method
            Method mainMethod = loadedClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[0]);

            // Close the class loader
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main();
    }
    
}
