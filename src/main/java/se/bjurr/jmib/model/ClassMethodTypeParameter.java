package se.bjurr.jmib.model;

import com.squareup.javapoet.TypeName;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

public class ClassMethodTypeParameter
{
    private final TypeParameterElement delegate;

    ClassMethodTypeParameter(TypeParameterElement delegate) {
        this.delegate = delegate;
    }

    public String getName() {
        return delegate.getSimpleName().toString();
    }

    public List<? extends TypeMirror> getBounds() {
        return delegate.getBounds()!=null
                ?delegate.getBounds()
                :new ArrayList<>();
    }

    public TypeName[] getBoundTypesNames() {
        List<? extends TypeMirror> typeParameterBounds=getBounds();

        TypeName[] typeParameterBoundsNames=new TypeName[typeParameterBounds.size()];
        for (int i=0; i<typeParameterBoundsNames.length; i++) {
            typeParameterBoundsNames[i]=TypeName.get(typeParameterBounds.get(i));
        }

        return typeParameterBoundsNames;
    }

    public TypeMirror toTypeMirror() {
        return delegate.asType();
    }

    @Override
    public String toString() {
        return "ClassMethodTypeParameter{" +
                delegate.toString() +
                '}';
    }
}
