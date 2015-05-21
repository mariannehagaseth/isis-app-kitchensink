package org.isisaddons.app.kitchensink.dom.layout.layout_6_0_6;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QLayout_6_0_6_Object extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Layout_6_0_6_Object> implements PersistableExpression<Layout_6_0_6_Object>
{
    public static final QLayout_6_0_6_Object jdoCandidate = candidate("this");

    public static QLayout_6_0_6_Object candidate(String name)
    {
        return new QLayout_6_0_6_Object(null, name, 5);
    }

    public static QLayout_6_0_6_Object candidate()
    {
        return jdoCandidate;
    }

    public static QLayout_6_0_6_Object parameter(String name)
    {
        return new QLayout_6_0_6_Object(Layout_6_0_6_Object.class, name, ExpressionType.PARAMETER);
    }

    public static QLayout_6_0_6_Object variable(String name)
    {
        return new QLayout_6_0_6_Object(Layout_6_0_6_Object.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final StringExpression someString1;
    public final StringExpression someString2;
    public final StringExpression someString3;
    public final StringExpression someString4;
    public final StringExpression someString5;
    public final StringExpression someString6;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.apache.isis.applib.services.clock.ClockService> clockService;

    public QLayout_6_0_6_Object(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.someString1 = new StringExpressionImpl(this, "someString1");
        this.someString2 = new StringExpressionImpl(this, "someString2");
        this.someString3 = new StringExpressionImpl(this, "someString3");
        this.someString4 = new StringExpressionImpl(this, "someString4");
        this.someString5 = new StringExpressionImpl(this, "someString5");
        this.someString6 = new StringExpressionImpl(this, "someString6");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.clockService = new ObjectExpressionImpl<org.apache.isis.applib.services.clock.ClockService>(this, "clockService");
    }

    public QLayout_6_0_6_Object(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.someString1 = new StringExpressionImpl(this, "someString1");
        this.someString2 = new StringExpressionImpl(this, "someString2");
        this.someString3 = new StringExpressionImpl(this, "someString3");
        this.someString4 = new StringExpressionImpl(this, "someString4");
        this.someString5 = new StringExpressionImpl(this, "someString5");
        this.someString6 = new StringExpressionImpl(this, "someString6");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.clockService = new ObjectExpressionImpl<org.apache.isis.applib.services.clock.ClockService>(this, "clockService");
    }
}
