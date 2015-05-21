package org.isisaddons.app.kitchensink.dom.contrib.contributee;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QPerson extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Person> implements PersistableExpression<Person>
{
    public static final QPerson jdoCandidate = candidate("this");

    public static QPerson candidate(String name)
    {
        return new QPerson(null, name, 5);
    }

    public static QPerson candidate()
    {
        return jdoCandidate;
    }

    public static QPerson parameter(String name)
    {
        return new QPerson(Person.class, name, ExpressionType.PARAMETER);
    }

    public static QPerson variable(String name)
    {
        return new QPerson(Person.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.contrib.contributee.Persons> contributee1Objects;

    public QPerson(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributee1Objects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributee.Persons>(this, "contributee1Objects");
    }

    public QPerson(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributee1Objects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributee.Persons>(this, "contributee1Objects");
    }
}
