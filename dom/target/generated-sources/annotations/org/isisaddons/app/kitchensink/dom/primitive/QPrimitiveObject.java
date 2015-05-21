package org.isisaddons.app.kitchensink.dom.primitive;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QPrimitiveObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<PrimitiveObject> implements PersistableExpression<PrimitiveObject>
{
    public static final QPrimitiveObject jdoCandidate = candidate("this");

    public static QPrimitiveObject candidate(String name)
    {
        return new QPrimitiveObject(null, name, 5);
    }

    public static QPrimitiveObject candidate()
    {
        return jdoCandidate;
    }

    public static QPrimitiveObject parameter(String name)
    {
        return new QPrimitiveObject(PrimitiveObject.class, name, ExpressionType.PARAMETER);
    }

    public static QPrimitiveObject variable(String name)
    {
        return new QPrimitiveObject(PrimitiveObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ByteExpression someByte;
    public final ByteExpression someByteHidden;
    public final ByteExpression someByteDisabled;
    public final ByteExpression someByteWithValidation;
    public final ByteExpression someByteWithChoices;
    public final NumericExpression<Short> someShort;
    public final NumericExpression<Short> someShortHidden;
    public final NumericExpression<Short> someShortDisabled;
    public final NumericExpression<Short> someShortWithValidation;
    public final NumericExpression<Short> someShortWithChoices;
    public final NumericExpression<Integer> someInt;
    public final NumericExpression<Integer> someIntHidden;
    public final NumericExpression<Integer> someIntDisabled;
    public final NumericExpression<Integer> someIntWithValidation;
    public final NumericExpression<Integer> someIntWithChoices;
    public final NumericExpression<Long> someLong;
    public final NumericExpression<Long> someLongHidden;
    public final NumericExpression<Long> someLongDisabled;
    public final NumericExpression<Long> someLongWithValidation;
    public final NumericExpression<Long> someLongWithChoices;
    public final NumericExpression<Float> someFloat;
    public final NumericExpression<Float> someFloatHidden;
    public final NumericExpression<Float> someFloatDisabled;
    public final NumericExpression<Float> someFloatWithValidation;
    public final NumericExpression<Float> someFloatWithChoices;
    public final NumericExpression<Double> someDouble;
    public final NumericExpression<Double> someDoubleHidden;
    public final NumericExpression<Double> someDoubleDisabled;
    public final NumericExpression<Double> someDoubleWithValidation;
    public final NumericExpression<Double> someDoubleWithChoices;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QPrimitiveObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someByte = new ByteExpressionImpl(this, "someByte");
        this.someByteHidden = new ByteExpressionImpl(this, "someByteHidden");
        this.someByteDisabled = new ByteExpressionImpl(this, "someByteDisabled");
        this.someByteWithValidation = new ByteExpressionImpl(this, "someByteWithValidation");
        this.someByteWithChoices = new ByteExpressionImpl(this, "someByteWithChoices");
        this.someShort = new NumericExpressionImpl<Short>(this, "someShort");
        this.someShortHidden = new NumericExpressionImpl<Short>(this, "someShortHidden");
        this.someShortDisabled = new NumericExpressionImpl<Short>(this, "someShortDisabled");
        this.someShortWithValidation = new NumericExpressionImpl<Short>(this, "someShortWithValidation");
        this.someShortWithChoices = new NumericExpressionImpl<Short>(this, "someShortWithChoices");
        this.someInt = new NumericExpressionImpl<Integer>(this, "someInt");
        this.someIntHidden = new NumericExpressionImpl<Integer>(this, "someIntHidden");
        this.someIntDisabled = new NumericExpressionImpl<Integer>(this, "someIntDisabled");
        this.someIntWithValidation = new NumericExpressionImpl<Integer>(this, "someIntWithValidation");
        this.someIntWithChoices = new NumericExpressionImpl<Integer>(this, "someIntWithChoices");
        this.someLong = new NumericExpressionImpl<Long>(this, "someLong");
        this.someLongHidden = new NumericExpressionImpl<Long>(this, "someLongHidden");
        this.someLongDisabled = new NumericExpressionImpl<Long>(this, "someLongDisabled");
        this.someLongWithValidation = new NumericExpressionImpl<Long>(this, "someLongWithValidation");
        this.someLongWithChoices = new NumericExpressionImpl<Long>(this, "someLongWithChoices");
        this.someFloat = new NumericExpressionImpl<Float>(this, "someFloat");
        this.someFloatHidden = new NumericExpressionImpl<Float>(this, "someFloatHidden");
        this.someFloatDisabled = new NumericExpressionImpl<Float>(this, "someFloatDisabled");
        this.someFloatWithValidation = new NumericExpressionImpl<Float>(this, "someFloatWithValidation");
        this.someFloatWithChoices = new NumericExpressionImpl<Float>(this, "someFloatWithChoices");
        this.someDouble = new NumericExpressionImpl<Double>(this, "someDouble");
        this.someDoubleHidden = new NumericExpressionImpl<Double>(this, "someDoubleHidden");
        this.someDoubleDisabled = new NumericExpressionImpl<Double>(this, "someDoubleDisabled");
        this.someDoubleWithValidation = new NumericExpressionImpl<Double>(this, "someDoubleWithValidation");
        this.someDoubleWithChoices = new NumericExpressionImpl<Double>(this, "someDoubleWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QPrimitiveObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someByte = new ByteExpressionImpl(this, "someByte");
        this.someByteHidden = new ByteExpressionImpl(this, "someByteHidden");
        this.someByteDisabled = new ByteExpressionImpl(this, "someByteDisabled");
        this.someByteWithValidation = new ByteExpressionImpl(this, "someByteWithValidation");
        this.someByteWithChoices = new ByteExpressionImpl(this, "someByteWithChoices");
        this.someShort = new NumericExpressionImpl<Short>(this, "someShort");
        this.someShortHidden = new NumericExpressionImpl<Short>(this, "someShortHidden");
        this.someShortDisabled = new NumericExpressionImpl<Short>(this, "someShortDisabled");
        this.someShortWithValidation = new NumericExpressionImpl<Short>(this, "someShortWithValidation");
        this.someShortWithChoices = new NumericExpressionImpl<Short>(this, "someShortWithChoices");
        this.someInt = new NumericExpressionImpl<Integer>(this, "someInt");
        this.someIntHidden = new NumericExpressionImpl<Integer>(this, "someIntHidden");
        this.someIntDisabled = new NumericExpressionImpl<Integer>(this, "someIntDisabled");
        this.someIntWithValidation = new NumericExpressionImpl<Integer>(this, "someIntWithValidation");
        this.someIntWithChoices = new NumericExpressionImpl<Integer>(this, "someIntWithChoices");
        this.someLong = new NumericExpressionImpl<Long>(this, "someLong");
        this.someLongHidden = new NumericExpressionImpl<Long>(this, "someLongHidden");
        this.someLongDisabled = new NumericExpressionImpl<Long>(this, "someLongDisabled");
        this.someLongWithValidation = new NumericExpressionImpl<Long>(this, "someLongWithValidation");
        this.someLongWithChoices = new NumericExpressionImpl<Long>(this, "someLongWithChoices");
        this.someFloat = new NumericExpressionImpl<Float>(this, "someFloat");
        this.someFloatHidden = new NumericExpressionImpl<Float>(this, "someFloatHidden");
        this.someFloatDisabled = new NumericExpressionImpl<Float>(this, "someFloatDisabled");
        this.someFloatWithValidation = new NumericExpressionImpl<Float>(this, "someFloatWithValidation");
        this.someFloatWithChoices = new NumericExpressionImpl<Float>(this, "someFloatWithChoices");
        this.someDouble = new NumericExpressionImpl<Double>(this, "someDouble");
        this.someDoubleHidden = new NumericExpressionImpl<Double>(this, "someDoubleHidden");
        this.someDoubleDisabled = new NumericExpressionImpl<Double>(this, "someDoubleDisabled");
        this.someDoubleWithValidation = new NumericExpressionImpl<Double>(this, "someDoubleWithValidation");
        this.someDoubleWithChoices = new NumericExpressionImpl<Double>(this, "someDoubleWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
