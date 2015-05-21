package org.isisaddons.app.kitchensink.dom.javamath;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QJavaMathObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<JavaMathObject> implements PersistableExpression<JavaMathObject>
{
    public static final QJavaMathObject jdoCandidate = candidate("this");

    public static QJavaMathObject candidate(String name)
    {
        return new QJavaMathObject(null, name, 5);
    }

    public static QJavaMathObject candidate()
    {
        return jdoCandidate;
    }

    public static QJavaMathObject parameter(String name)
    {
        return new QJavaMathObject(JavaMathObject.class, name, ExpressionType.PARAMETER);
    }

    public static QJavaMathObject variable(String name)
    {
        return new QJavaMathObject(JavaMathObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerMandatory;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerOptional;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerHidden;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerDisabled;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerWithValidation;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerMandatoryWithChoices;
    public final ObjectExpression<java.math.BigInteger> someBigIntegerOptionalWithChoices;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalMandatory;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalOptional;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalHidden;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalDisabled;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalWithValidation;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalMandatoryWithChoices;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimalOptionalWithChoices;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimal92;
    public final ObjectExpression<java.math.BigDecimal> someBigDecimal124;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QJavaMathObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someBigIntegerMandatory = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerMandatory");
        this.someBigIntegerOptional = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerOptional");
        this.someBigIntegerHidden = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerHidden");
        this.someBigIntegerDisabled = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerDisabled");
        this.someBigIntegerWithValidation = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerWithValidation");
        this.someBigIntegerMandatoryWithChoices = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerMandatoryWithChoices");
        this.someBigIntegerOptionalWithChoices = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerOptionalWithChoices");
        this.someBigDecimalMandatory = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalMandatory");
        this.someBigDecimalOptional = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalOptional");
        this.someBigDecimalHidden = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalHidden");
        this.someBigDecimalDisabled = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalDisabled");
        this.someBigDecimalWithValidation = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalWithValidation");
        this.someBigDecimalMandatoryWithChoices = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalMandatoryWithChoices");
        this.someBigDecimalOptionalWithChoices = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalOptionalWithChoices");
        this.someBigDecimal92 = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimal92");
        this.someBigDecimal124 = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimal124");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QJavaMathObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someBigIntegerMandatory = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerMandatory");
        this.someBigIntegerOptional = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerOptional");
        this.someBigIntegerHidden = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerHidden");
        this.someBigIntegerDisabled = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerDisabled");
        this.someBigIntegerWithValidation = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerWithValidation");
        this.someBigIntegerMandatoryWithChoices = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerMandatoryWithChoices");
        this.someBigIntegerOptionalWithChoices = new ObjectExpressionImpl<java.math.BigInteger>(this, "someBigIntegerOptionalWithChoices");
        this.someBigDecimalMandatory = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalMandatory");
        this.someBigDecimalOptional = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalOptional");
        this.someBigDecimalHidden = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalHidden");
        this.someBigDecimalDisabled = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalDisabled");
        this.someBigDecimalWithValidation = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalWithValidation");
        this.someBigDecimalMandatoryWithChoices = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalMandatoryWithChoices");
        this.someBigDecimalOptionalWithChoices = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimalOptionalWithChoices");
        this.someBigDecimal92 = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimal92");
        this.someBigDecimal124 = new ObjectExpressionImpl<java.math.BigDecimal>(this, "someBigDecimal124");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
