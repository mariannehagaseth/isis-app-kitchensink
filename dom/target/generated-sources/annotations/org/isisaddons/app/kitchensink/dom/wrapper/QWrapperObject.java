package org.isisaddons.app.kitchensink.dom.wrapper;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QWrapperObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<WrapperObject> implements PersistableExpression<WrapperObject>
{
    public static final QWrapperObject jdoCandidate = candidate("this");

    public static QWrapperObject candidate(String name)
    {
        return new QWrapperObject(null, name, 5);
    }

    public static QWrapperObject candidate()
    {
        return jdoCandidate;
    }

    public static QWrapperObject parameter(String name)
    {
        return new QWrapperObject(WrapperObject.class, name, ExpressionType.PARAMETER);
    }

    public static QWrapperObject variable(String name)
    {
        return new QWrapperObject(WrapperObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<java.lang.Byte> someByteWrapperMandatory;
    public final ObjectExpression<java.lang.Byte> someByteWrapperOptional;
    public final ObjectExpression<java.lang.Byte> someByteWrapperHidden;
    public final ObjectExpression<java.lang.Byte> someByteWrapperDisabled;
    public final ObjectExpression<java.lang.Byte> someByteWrapperWithValidation;
    public final ObjectExpression<java.lang.Byte> someByteWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Byte> someByteWrapperOptionalWithChoices;
    public final ObjectExpression<java.lang.Short> someShortWrapperMandatory;
    public final ObjectExpression<java.lang.Short> someShortWrapperOptional;
    public final ObjectExpression<java.lang.Short> someShortWrapperHidden;
    public final ObjectExpression<java.lang.Short> someShortWrapperDisabled;
    public final ObjectExpression<java.lang.Short> someShortWrapperWithValidation;
    public final ObjectExpression<java.lang.Short> someShortWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Short> someShortWrapperOptionalWithChoices;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperMandatory;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperOptional;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperHidden;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperDisabled;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperWithValidation;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Integer> someIntegerWrapperOptionalWithChoices;
    public final ObjectExpression<java.lang.Long> someLongWrapperMandatory;
    public final ObjectExpression<java.lang.Long> someLongWrapperOptional;
    public final ObjectExpression<java.lang.Long> someLongWrapperHidden;
    public final ObjectExpression<java.lang.Long> someLongWrapperDisabled;
    public final ObjectExpression<java.lang.Long> someLongWrapperWithValidation;
    public final ObjectExpression<java.lang.Long> someLongWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Long> someLongWrapperOptionalWithChoices;
    public final ObjectExpression<java.lang.Float> someFloatWrapperMandatory;
    public final ObjectExpression<java.lang.Float> someFloatWrapperOptional;
    public final ObjectExpression<java.lang.Float> someFloatWrapperHidden;
    public final ObjectExpression<java.lang.Float> someFloatWrapperDisabled;
    public final ObjectExpression<java.lang.Float> someFloatWrapperWithValidation;
    public final ObjectExpression<java.lang.Float> someFloatWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Float> someFloatWrapperOptionalWithChoices;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperMandatory;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperOptional;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperHidden;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperDisabled;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperWithValidation;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperMandatoryWithChoices;
    public final ObjectExpression<java.lang.Double> someDoubleWrapperOptionalWithChoices;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QWrapperObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someByteWrapperMandatory = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperMandatory");
        this.someByteWrapperOptional = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperOptional");
        this.someByteWrapperHidden = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperHidden");
        this.someByteWrapperDisabled = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperDisabled");
        this.someByteWrapperWithValidation = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperWithValidation");
        this.someByteWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperMandatoryWithChoices");
        this.someByteWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperOptionalWithChoices");
        this.someShortWrapperMandatory = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperMandatory");
        this.someShortWrapperOptional = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperOptional");
        this.someShortWrapperHidden = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperHidden");
        this.someShortWrapperDisabled = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperDisabled");
        this.someShortWrapperWithValidation = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperWithValidation");
        this.someShortWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperMandatoryWithChoices");
        this.someShortWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperOptionalWithChoices");
        this.someIntegerWrapperMandatory = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperMandatory");
        this.someIntegerWrapperOptional = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperOptional");
        this.someIntegerWrapperHidden = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperHidden");
        this.someIntegerWrapperDisabled = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperDisabled");
        this.someIntegerWrapperWithValidation = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperWithValidation");
        this.someIntegerWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperMandatoryWithChoices");
        this.someIntegerWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperOptionalWithChoices");
        this.someLongWrapperMandatory = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperMandatory");
        this.someLongWrapperOptional = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperOptional");
        this.someLongWrapperHidden = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperHidden");
        this.someLongWrapperDisabled = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperDisabled");
        this.someLongWrapperWithValidation = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperWithValidation");
        this.someLongWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperMandatoryWithChoices");
        this.someLongWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperOptionalWithChoices");
        this.someFloatWrapperMandatory = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperMandatory");
        this.someFloatWrapperOptional = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperOptional");
        this.someFloatWrapperHidden = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperHidden");
        this.someFloatWrapperDisabled = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperDisabled");
        this.someFloatWrapperWithValidation = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperWithValidation");
        this.someFloatWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperMandatoryWithChoices");
        this.someFloatWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperOptionalWithChoices");
        this.someDoubleWrapperMandatory = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperMandatory");
        this.someDoubleWrapperOptional = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperOptional");
        this.someDoubleWrapperHidden = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperHidden");
        this.someDoubleWrapperDisabled = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperDisabled");
        this.someDoubleWrapperWithValidation = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperWithValidation");
        this.someDoubleWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperMandatoryWithChoices");
        this.someDoubleWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperOptionalWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QWrapperObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someByteWrapperMandatory = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperMandatory");
        this.someByteWrapperOptional = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperOptional");
        this.someByteWrapperHidden = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperHidden");
        this.someByteWrapperDisabled = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperDisabled");
        this.someByteWrapperWithValidation = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperWithValidation");
        this.someByteWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperMandatoryWithChoices");
        this.someByteWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Byte>(this, "someByteWrapperOptionalWithChoices");
        this.someShortWrapperMandatory = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperMandatory");
        this.someShortWrapperOptional = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperOptional");
        this.someShortWrapperHidden = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperHidden");
        this.someShortWrapperDisabled = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperDisabled");
        this.someShortWrapperWithValidation = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperWithValidation");
        this.someShortWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperMandatoryWithChoices");
        this.someShortWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Short>(this, "someShortWrapperOptionalWithChoices");
        this.someIntegerWrapperMandatory = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperMandatory");
        this.someIntegerWrapperOptional = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperOptional");
        this.someIntegerWrapperHidden = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperHidden");
        this.someIntegerWrapperDisabled = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperDisabled");
        this.someIntegerWrapperWithValidation = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperWithValidation");
        this.someIntegerWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperMandatoryWithChoices");
        this.someIntegerWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Integer>(this, "someIntegerWrapperOptionalWithChoices");
        this.someLongWrapperMandatory = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperMandatory");
        this.someLongWrapperOptional = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperOptional");
        this.someLongWrapperHidden = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperHidden");
        this.someLongWrapperDisabled = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperDisabled");
        this.someLongWrapperWithValidation = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperWithValidation");
        this.someLongWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperMandatoryWithChoices");
        this.someLongWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Long>(this, "someLongWrapperOptionalWithChoices");
        this.someFloatWrapperMandatory = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperMandatory");
        this.someFloatWrapperOptional = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperOptional");
        this.someFloatWrapperHidden = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperHidden");
        this.someFloatWrapperDisabled = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperDisabled");
        this.someFloatWrapperWithValidation = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperWithValidation");
        this.someFloatWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperMandatoryWithChoices");
        this.someFloatWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Float>(this, "someFloatWrapperOptionalWithChoices");
        this.someDoubleWrapperMandatory = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperMandatory");
        this.someDoubleWrapperOptional = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperOptional");
        this.someDoubleWrapperHidden = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperHidden");
        this.someDoubleWrapperDisabled = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperDisabled");
        this.someDoubleWrapperWithValidation = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperWithValidation");
        this.someDoubleWrapperMandatoryWithChoices = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperMandatoryWithChoices");
        this.someDoubleWrapperOptionalWithChoices = new ObjectExpressionImpl<java.lang.Double>(this, "someDoubleWrapperOptionalWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
