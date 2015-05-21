package org.isisaddons.app.kitchensink.dom.contrib.contributee;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QFoodStuff extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<FoodStuff> implements PersistableExpression<FoodStuff>
{
    public static final QFoodStuff jdoCandidate = candidate("this");

    public static QFoodStuff candidate(String name)
    {
        return new QFoodStuff(null, name, 5);
    }

    public static QFoodStuff candidate()
    {
        return jdoCandidate;
    }

    public static QFoodStuff parameter(String name)
    {
        return new QFoodStuff(FoodStuff.class, name, ExpressionType.PARAMETER);
    }

    public static QFoodStuff variable(String name)
    {
        return new QFoodStuff(FoodStuff.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.contrib.contributee.FoodStuffs> contributee2Objects;

    public QFoodStuff(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributee2Objects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributee.FoodStuffs>(this, "contributee2Objects");
    }

    public QFoodStuff(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributee2Objects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributee.FoodStuffs>(this, "contributee2Objects");
    }
}
