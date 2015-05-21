package org.isisaddons.app.kitchensink.dom.hierarchy.grandchild;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QGrandchildObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<GrandchildObject> implements PersistableExpression<GrandchildObject>
{
    public static final QGrandchildObject jdoCandidate = candidate("this");

    public static QGrandchildObject candidate(String name)
    {
        return new QGrandchildObject(null, name, 5);
    }

    public static QGrandchildObject candidate()
    {
        return jdoCandidate;
    }

    public static QGrandchildObject parameter(String name)
    {
        return new QGrandchildObject(GrandchildObject.class, name, ExpressionType.PARAMETER);
    }

    public static QGrandchildObject variable(String name)
    {
        return new QGrandchildObject(GrandchildObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final org.isisaddons.app.kitchensink.dom.hierarchy.child.QChildObject child;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherObjects> otherObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects> otherBoundedObjects;

    public QGrandchildObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        if (depth > 0)
        {
            this.child = new org.isisaddons.app.kitchensink.dom.hierarchy.child.QChildObject(this, "child", depth-1);
        }
        else
        {
            this.child = null;
        }
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }

    public QGrandchildObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.child = new org.isisaddons.app.kitchensink.dom.hierarchy.child.QChildObject(this, "child", 5);
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.otherObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherObjects>(this, "otherObjects");
        this.otherBoundedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.other.OtherBoundedObjects>(this, "otherBoundedObjects");
    }
}
