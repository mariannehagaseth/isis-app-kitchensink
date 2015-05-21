package org.isisaddons.app.kitchensink.dom.misc;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QMiscObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<MiscObject> implements PersistableExpression<MiscObject>
{
    public static final QMiscObject jdoCandidate = candidate("this");

    public static QMiscObject candidate(String name)
    {
        return new QMiscObject(null, name, 5);
    }

    public static QMiscObject candidate()
    {
        return jdoCandidate;
    }

    public static QMiscObject parameter(String name)
    {
        return new QMiscObject(MiscObject.class, name, ExpressionType.PARAMETER);
    }

    public static QMiscObject variable(String name)
    {
        return new QMiscObject(MiscObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<java.net.URL> someUrlMandatory;
    public final ObjectExpression<java.net.URL> someUrlOptional;
    public final ObjectExpression<java.net.URL> someUrlHidden;
    public final ObjectExpression<java.net.URL> someUrlDisabled;
    public final ObjectExpression<java.net.URL> someUrlWithValidation;
    public final ObjectExpression<java.net.URL> someUrlMandatoryWithChoices;
    public final ObjectExpression<java.net.URL> someUrlOptionalWithChoices;
    public final ObjectExpression<java.util.UUID> someUuidMandatory;
    public final ObjectExpression<java.util.UUID> someUuidOptional;
    public final ObjectExpression<java.util.UUID> someUuidHidden;
    public final ObjectExpression<java.util.UUID> someUuidDisabled;
    public final ObjectExpression<java.util.UUID> someUuidWithValidation;
    public final ObjectExpression<java.util.UUID> someUuidMandatoryWithChoices;
    public final ObjectExpression<java.util.UUID> someUuidOptionalWithChoices;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyMandatory;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyOptional;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyHidden;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyDisabled;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyWithValidation;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyMandatoryWithChoices;
    public final ObjectExpression<org.apache.isis.applib.value.Money> someMoneyOptionalWithChoices;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QMiscObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someUrlMandatory = new ObjectExpressionImpl<java.net.URL>(this, "someUrlMandatory");
        this.someUrlOptional = new ObjectExpressionImpl<java.net.URL>(this, "someUrlOptional");
        this.someUrlHidden = new ObjectExpressionImpl<java.net.URL>(this, "someUrlHidden");
        this.someUrlDisabled = new ObjectExpressionImpl<java.net.URL>(this, "someUrlDisabled");
        this.someUrlWithValidation = new ObjectExpressionImpl<java.net.URL>(this, "someUrlWithValidation");
        this.someUrlMandatoryWithChoices = new ObjectExpressionImpl<java.net.URL>(this, "someUrlMandatoryWithChoices");
        this.someUrlOptionalWithChoices = new ObjectExpressionImpl<java.net.URL>(this, "someUrlOptionalWithChoices");
        this.someUuidMandatory = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidMandatory");
        this.someUuidOptional = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidOptional");
        this.someUuidHidden = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidHidden");
        this.someUuidDisabled = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidDisabled");
        this.someUuidWithValidation = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidWithValidation");
        this.someUuidMandatoryWithChoices = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidMandatoryWithChoices");
        this.someUuidOptionalWithChoices = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidOptionalWithChoices");
        this.someMoneyMandatory = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyMandatory");
        this.someMoneyOptional = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyOptional");
        this.someMoneyHidden = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyHidden");
        this.someMoneyDisabled = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyDisabled");
        this.someMoneyWithValidation = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyWithValidation");
        this.someMoneyMandatoryWithChoices = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyMandatoryWithChoices");
        this.someMoneyOptionalWithChoices = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyOptionalWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QMiscObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someUrlMandatory = new ObjectExpressionImpl<java.net.URL>(this, "someUrlMandatory");
        this.someUrlOptional = new ObjectExpressionImpl<java.net.URL>(this, "someUrlOptional");
        this.someUrlHidden = new ObjectExpressionImpl<java.net.URL>(this, "someUrlHidden");
        this.someUrlDisabled = new ObjectExpressionImpl<java.net.URL>(this, "someUrlDisabled");
        this.someUrlWithValidation = new ObjectExpressionImpl<java.net.URL>(this, "someUrlWithValidation");
        this.someUrlMandatoryWithChoices = new ObjectExpressionImpl<java.net.URL>(this, "someUrlMandatoryWithChoices");
        this.someUrlOptionalWithChoices = new ObjectExpressionImpl<java.net.URL>(this, "someUrlOptionalWithChoices");
        this.someUuidMandatory = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidMandatory");
        this.someUuidOptional = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidOptional");
        this.someUuidHidden = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidHidden");
        this.someUuidDisabled = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidDisabled");
        this.someUuidWithValidation = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidWithValidation");
        this.someUuidMandatoryWithChoices = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidMandatoryWithChoices");
        this.someUuidOptionalWithChoices = new ObjectExpressionImpl<java.util.UUID>(this, "someUuidOptionalWithChoices");
        this.someMoneyMandatory = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyMandatory");
        this.someMoneyOptional = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyOptional");
        this.someMoneyHidden = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyHidden");
        this.someMoneyDisabled = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyDisabled");
        this.someMoneyWithValidation = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyWithValidation");
        this.someMoneyMandatoryWithChoices = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyMandatoryWithChoices");
        this.someMoneyOptionalWithChoices = new ObjectExpressionImpl<org.apache.isis.applib.value.Money>(this, "someMoneyOptionalWithChoices");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
