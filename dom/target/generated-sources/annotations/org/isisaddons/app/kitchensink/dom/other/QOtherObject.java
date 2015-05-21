package org.isisaddons.app.kitchensink.dom.other;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QOtherObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<OtherObject> implements PersistableExpression<OtherObject>
{
    public static final QOtherObject jdoCandidate = candidate("this");

    public static QOtherObject candidate(String name)
    {
        return new QOtherObject(null, name, 5);
    }

    public static QOtherObject candidate()
    {
        return jdoCandidate;
    }

    public static QOtherObject parameter(String name)
    {
        return new QOtherObject(OtherObject.class, name, ExpressionType.PARAMETER);
    }

    public static QOtherObject variable(String name)
    {
        return new QOtherObject(OtherObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final StringExpression description;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.SomeCategory> someCategory;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QOtherObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.description = new StringExpressionImpl(this, "description");
        this.someCategory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.SomeCategory>(this, "someCategory");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QOtherObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.description = new StringExpressionImpl(this, "description");
        this.someCategory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.SomeCategory>(this, "someCategory");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
