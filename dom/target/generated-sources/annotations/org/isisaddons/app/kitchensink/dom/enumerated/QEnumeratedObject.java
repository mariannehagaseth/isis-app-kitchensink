package org.isisaddons.app.kitchensink.dom.enumerated;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QEnumeratedObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<EnumeratedObject> implements PersistableExpression<EnumeratedObject>
{
    public static final QEnumeratedObject jdoCandidate = candidate("this");

    public static QEnumeratedObject candidate(String name)
    {
        return new QEnumeratedObject(null, name, 5);
    }

    public static QEnumeratedObject candidate()
    {
        return jdoCandidate;
    }

    public static QEnumeratedObject parameter(String name)
    {
        return new QEnumeratedObject(EnumeratedObject.class, name, ExpressionType.PARAMETER);
    }

    public static QEnumeratedObject variable(String name)
    {
        return new QEnumeratedObject(EnumeratedObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final BooleanExpression someBoolean;
    public final BooleanExpression someBooleanHidden;
    public final BooleanExpression someBooleanDisabled;
    public final BooleanExpression someBooleanWithValidation;
    public final ObjectExpression<java.lang.Boolean> someBooleanWrapperMandatory;
    public final ObjectExpression<java.lang.Boolean> someBooleanWrapperOptional;
    public final ObjectExpression<java.lang.Boolean> someBooleanWrapperHidden;
    public final ObjectExpression<java.lang.Boolean> someBooleanWrapperDisabled;
    public final ObjectExpression<java.lang.Boolean> someBooleanWrapperWithValidation;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3> someEnumOf3Mandatory;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3> someEnumOf3Optional;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3> someEnumOf3Hidden;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3> someEnumOf3Disabled;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3> someEnumOf3WithValidation;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4> someEnumOf4Mandatory;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4> someEnumOf4Optional;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4> someEnumOf4Hidden;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4> someEnumOf4Disabled;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4> someEnumOf4WithValidation;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8> someEnumOf8Mandatory;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8> someEnumOf8Optional;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8> someEnumOf8Hidden;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8> someEnumOf8Disabled;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8> someEnumOf8WithValidation;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QEnumeratedObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someBoolean = new BooleanExpressionImpl(this, "someBoolean");
        this.someBooleanHidden = new BooleanExpressionImpl(this, "someBooleanHidden");
        this.someBooleanDisabled = new BooleanExpressionImpl(this, "someBooleanDisabled");
        this.someBooleanWithValidation = new BooleanExpressionImpl(this, "someBooleanWithValidation");
        this.someBooleanWrapperMandatory = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperMandatory");
        this.someBooleanWrapperOptional = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperOptional");
        this.someBooleanWrapperHidden = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperHidden");
        this.someBooleanWrapperDisabled = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperDisabled");
        this.someBooleanWrapperWithValidation = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperWithValidation");
        this.someEnumOf3Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Mandatory");
        this.someEnumOf3Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Optional");
        this.someEnumOf3Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Hidden");
        this.someEnumOf3Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Disabled");
        this.someEnumOf3WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3WithValidation");
        this.someEnumOf4Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Mandatory");
        this.someEnumOf4Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Optional");
        this.someEnumOf4Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Hidden");
        this.someEnumOf4Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Disabled");
        this.someEnumOf4WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4WithValidation");
        this.someEnumOf8Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Mandatory");
        this.someEnumOf8Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Optional");
        this.someEnumOf8Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Hidden");
        this.someEnumOf8Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Disabled");
        this.someEnumOf8WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8WithValidation");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QEnumeratedObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someBoolean = new BooleanExpressionImpl(this, "someBoolean");
        this.someBooleanHidden = new BooleanExpressionImpl(this, "someBooleanHidden");
        this.someBooleanDisabled = new BooleanExpressionImpl(this, "someBooleanDisabled");
        this.someBooleanWithValidation = new BooleanExpressionImpl(this, "someBooleanWithValidation");
        this.someBooleanWrapperMandatory = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperMandatory");
        this.someBooleanWrapperOptional = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperOptional");
        this.someBooleanWrapperHidden = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperHidden");
        this.someBooleanWrapperDisabled = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperDisabled");
        this.someBooleanWrapperWithValidation = new ObjectExpressionImpl<java.lang.Boolean>(this, "someBooleanWrapperWithValidation");
        this.someEnumOf3Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Mandatory");
        this.someEnumOf3Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Optional");
        this.someEnumOf3Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Hidden");
        this.someEnumOf3Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3Disabled");
        this.someEnumOf3WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf3>(this, "someEnumOf3WithValidation");
        this.someEnumOf4Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Mandatory");
        this.someEnumOf4Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Optional");
        this.someEnumOf4Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Hidden");
        this.someEnumOf4Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4Disabled");
        this.someEnumOf4WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf4>(this, "someEnumOf4WithValidation");
        this.someEnumOf8Mandatory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Mandatory");
        this.someEnumOf8Optional = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Optional");
        this.someEnumOf8Hidden = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Hidden");
        this.someEnumOf8Disabled = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8Disabled");
        this.someEnumOf8WithValidation = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.enumerated.EnumOf8>(this, "someEnumOf8WithValidation");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
