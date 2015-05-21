package org.isisaddons.app.kitchensink.dom.busrules;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QBusRulesObject extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<BusRulesObject> implements PersistableExpression<BusRulesObject>
{
    public static final QBusRulesObject jdoCandidate = candidate("this");

    public static QBusRulesObject candidate(String name)
    {
        return new QBusRulesObject(null, name, 5);
    }

    public static QBusRulesObject candidate()
    {
        return jdoCandidate;
    }

    public static QBusRulesObject parameter(String name)
    {
        return new QBusRulesObject(BusRulesObject.class, name, ExpressionType.PARAMETER);
    }

    public static QBusRulesObject variable(String name)
    {
        return new QBusRulesObject(BusRulesObject.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.busrules.BusRulesObjects> busRulesObjects;

    public QBusRulesObject(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.busRulesObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.busrules.BusRulesObjects>(this, "busRulesObjects");
    }

    public QBusRulesObject(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.busRulesObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.busrules.BusRulesObjects>(this, "busRulesObjects");
    }
}
