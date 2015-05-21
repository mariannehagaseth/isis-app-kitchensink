package org.isisaddons.app.kitchensink.dom.layout.layout_6_0_6_12C;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QLayout_6_0_6_12C_Object extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Layout_6_0_6_12C_Object> implements PersistableExpression<Layout_6_0_6_12C_Object>
{
    public static final QLayout_6_0_6_12C_Object jdoCandidate = candidate("this");

    public static QLayout_6_0_6_12C_Object candidate(String name)
    {
        return new QLayout_6_0_6_12C_Object(null, name, 5);
    }

    public static QLayout_6_0_6_12C_Object candidate()
    {
        return jdoCandidate;
    }

    public static QLayout_6_0_6_12C_Object parameter(String name)
    {
        return new QLayout_6_0_6_12C_Object(Layout_6_0_6_12C_Object.class, name, ExpressionType.PARAMETER);
    }

    public static QLayout_6_0_6_12C_Object variable(String name)
    {
        return new QLayout_6_0_6_12C_Object(Layout_6_0_6_12C_Object.class, name, ExpressionType.VARIABLE);
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
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.text.TextObjects> textObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.javamath.JavaMathObjects> mathObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers> nflPlayers;

    public QLayout_6_0_6_12C_Object(PersistableExpression parent, String name, int depth)
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
        this.textObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.text.TextObjects>(this, "textObjects");
        this.mathObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.javamath.JavaMathObjects>(this, "mathObjects");
        this.nflPlayers = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers>(this, "nflPlayers");
    }

    public QLayout_6_0_6_12C_Object(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
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
        this.textObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.text.TextObjects>(this, "textObjects");
        this.mathObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.javamath.JavaMathObjects>(this, "mathObjects");
        this.nflPlayers = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers>(this, "nflPlayers");
    }
}
