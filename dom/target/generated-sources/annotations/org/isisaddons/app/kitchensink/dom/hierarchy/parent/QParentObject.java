package org.isisaddons.app.kitchensink.dom.hierarchy.parent;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QParentObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<ParentObject> implements PersistableExpression<ParentObject>
{
    public static final QParentObject jdoCandidate = candidate("this");

    public static QParentObject candidate(String name)
    {
        return new QParentObject(null, name, 5);
    }

    public static QParentObject candidate()
    {
        return jdoCandidate;
    }

    public static QParentObject parameter(String name)
    {
        return new QParentObject(ParentObject.class, name, ExpressionType.PARAMETER);
    }

    public static QParentObject variable(String name)
    {
        return new QParentObject(ParentObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final CollectionExpression children;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.hierarchy.child.ChildObjects> childObjects;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects> parentObjects;

    public QParentObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.children = new CollectionExpressionImpl(this, "children");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.childObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.child.ChildObjects>(this, "childObjects");
        this.parentObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects>(this, "parentObjects");
    }

    public QParentObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.children = new CollectionExpressionImpl(this, "children");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.childObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.child.ChildObjects>(this, "childObjects");
        this.parentObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObjects>(this, "parentObjects");
    }
}
