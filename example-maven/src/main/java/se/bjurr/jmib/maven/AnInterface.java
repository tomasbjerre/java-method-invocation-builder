package se.bjurr.jmib.maven;

import static se.bjurr.jmib.anotations.BuilderStyle.SUPPLY_INSTANCE_AS_INVOKE_PARAMETER;

import java.math.BigDecimal;
import se.bjurr.jmib.anotations.Default;
import se.bjurr.jmib.anotations.GenerateMethodInvocationBuilder;

@GenerateMethodInvocationBuilder(style = SUPPLY_INSTANCE_AS_INVOKE_PARAMETER)
public interface AnInterface {
  String bigDecimalInParameter( //
      BigDecimal customType);

  BigDecimal bigDecimalInReturn( //
      GenericType<String> customType);

  String customType( //
      CustomType customType);

  String defaultValue( //
      @Default("PROJ") String projectKey);

  String defaultValues( //
      @Default("PROJ") String projectKey, //
      @Default("other") String other, //
      String at);

  String genericTypeParam( //
      GenericType<String> customType);

  GenericType<String> genericTypeReturn( //
      String customType);

  String intInParameter( //
      int customType);

  int intInReturn( //
      BigDecimal customType);
}
