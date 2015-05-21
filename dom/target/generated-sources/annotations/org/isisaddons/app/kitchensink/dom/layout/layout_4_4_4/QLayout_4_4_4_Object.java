package org.isisaddons.app.kitchensink.dom.layout.layout_4_4_4;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QLayout_4_4_4_Object extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Layout_4_4_4_Object> implements PersistableExpression<Layout_4_4_4_Object>
{
    public static final QLayout_4_4_4_Object jdoCandidate = candidate("this");

    public static QLayout_4_4_4_Object candidate(String name)
    {
        return new QLayout_4_4_4_Object(null, name, 5);
    }

    public static QLayout_4_4_4_Object candidate()
    {
        return jdoCandidate;
    }

    public static QLayout_4_4_4_Object parameter(String name)
    {
        return new QLayout_4_4_4_Object(Layout_4_4_4_Object.class, name, ExpressionType.PARAMETER);
    }

    public static QLayout_4_4_4_Object variable(String name)
    {
        return new QLayout_4_4_4_Object(Layout_4_4_4_Object.class, name, ExpressionType.VARIABLE);
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

    public QLayout_4_4_4_Object(PersistableExpression parent, String name, int depth)
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

    public QLayout_4_4_4_Object(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
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
