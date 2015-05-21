package org.isisaddons.app.kitchensink.dom.other;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QOtherBoundedObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<OtherBoundedObject> implements PersistableExpression<OtherBoundedObject>
{
    public static final QOtherBoundedObject jdoCandidate = candidate("this");

    public static QOtherBoundedObject candidate(String name)
    {
        return new QOtherBoundedObject(null, name, 5);
    }

    public static QOtherBoundedObject candidate()
    {
        return jdoCandidate;
    }

    public static QOtherBoundedObject parameter(String name)
    {
        return new QOtherBoundedObject(OtherBoundedObject.class, name, ExpressionType.PARAMETER);
    }

    public static QOtherBoundedObject variable(String name)
    {
        return new QOtherBoundedObject(OtherBoundedObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final StringExpression description;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.SomeCategory> someCategory;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;

    public QOtherBoundedObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.description = new StringExpressionImpl(this, "description");
        this.someCategory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.SomeCategory>(this, "someCategory");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }

    public QOtherBoundedObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.description = new StringExpressionImpl(this, "description");
        this.someCategory = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.SomeCategory>(this, "someCategory");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
    }
}
