package se.bjurr.jmib.testcases;

import java.math.BigDecimal;

import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder
public interface AnInterface {
 String bigDecimalInParameter(//
   BigDecimal customType);

 BigDecimal bigDecimalInReturn(//
   GenericType<String> customType);

 String customType(//
   CustomType customType);

 String defaultValue(//
   @Default("PROJ") String projectKey);

 String defaultValues(//
   @Default("PROJ") String projectKey,//
   @Default("other") String other,//
   String at);

 String genericTypeParam(//
   GenericType<String> customType);

 GenericType<String> genericTypeReturn(//
   String customType);

 String intInParameter(//
   int customType);

 int intInReturn(//
   BigDecimal customType);

}
