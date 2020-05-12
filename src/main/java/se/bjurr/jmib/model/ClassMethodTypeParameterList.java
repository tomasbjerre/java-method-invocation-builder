package se.bjurr.jmib.model;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import java.util.LinkedList;
import java.util.List;
import javax.lang.model.element.TypeParameterElement;

public class ClassMethodTypeParameterList extends LinkedList<ClassMethodTypeParameter> {

    private ClassMethodTypeParameterList() {}

    public static ClassMethodTypeParameterList newInstance(List<? extends TypeParameterElement> typeParameterElements) {
        ClassMethodTypeParameterList typeParameters=new ClassMethodTypeParameterList();
        for (TypeParameterElement typeParameterElement : typeParameterElements) {
            typeParameters.add(new ClassMethodTypeParameter(typeParameterElement));
        }
        return typeParameters;
    }

    public Iterable<TypeVariableName> toTypeVariableNameList() {
        List<TypeVariableName> typeVariableNames=new LinkedList<>();
        for (ClassMethodTypeParameter typeParameter : this) {
            typeVariableNames.add(TypeVariableName.get(
                typeParameter.getName(), //the generic type name (eg: T)
                typeParameter.getBoundTypesNames())); //generic type's bounds (ex: T extends Serializable)
        }
        return typeVariableNames;
    }

    public TypeName[] toTypeNameArray() {
        int index=0;
        TypeName[] typeNames=new TypeName[size()];
        for (ClassMethodTypeParameter typeParameter : this) {
            typeNames[index++]=TypeName.get(typeParameter.toTypeMirror());
        }
        return typeNames;
    }
}
