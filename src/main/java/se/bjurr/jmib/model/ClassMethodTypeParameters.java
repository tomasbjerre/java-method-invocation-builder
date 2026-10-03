package se.bjurr.jmib.model;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

/** The type parameters declared on a {@link ClassMethod}. */
public interface ClassMethodTypeParameters {

  boolean isEmpty();

  Iterable<TypeVariableName> toTypeVariableNameList();

  TypeName[] toTypeNameArray();
}
