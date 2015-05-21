package org.isisaddons.app.kitchensink.dom.hierarchy.child;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QChildObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<ChildObject> implements PersistableExpression<ChildObject>
{
    public static final QChildObject jdoCandidate = candidate("this");

    public static QChildObject candidate(String name)
    {
        return new QChildObject(null, name, 5);
    }

    public static QChildObject candidate()
    {
        return jdoCandidate;
    }

    public static QChildObject parameter(String name)
    {
        return new QChildObject(ChildObject.class, name, ExpressionType.PARAMETER);
    }

    public static QChildObject variable(String name)
    {
        return new QChildObject(ChildObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final org.isisaddons.app.kitchensink.dom.hierarchy.parent.QParentObject parent;
    public final CollectionExpression grandchildren;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects> parentObjects;

    public QChildObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        if (depth > 0)
        {
            this.parent = new org.isisaddons.app.kitchensink.dom.hierarchy.parent.QParentObject(this, "parent", depth-1);
        }
        else
        {
            this.parent = null;
        }
        this.grandchildren = new CollectionExpressionImpl(this, "grandchildren");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.parentObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects>(this, "parentObjects");
    }

    public QChildObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.parent = new org.isisaddons.app.kitchensink.dom.hierarchy.parent.QParentObject(this, "parent", 5);
        this.grandchildren = new CollectionExpressionImpl(this, "grandchildren");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.parentObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects>(this, "parentObjects");
    }
}
