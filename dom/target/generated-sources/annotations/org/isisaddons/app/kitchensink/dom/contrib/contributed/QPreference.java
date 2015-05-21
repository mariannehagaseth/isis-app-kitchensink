package org.isisaddons.app.kitchensink.dom.contrib.contributed;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QPreference extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Preference> implements PersistableExpression<Preference>
{
    public static final QPreference jdoCandidate = candidate("this");

    public static QPreference candidate(String name)
    {
        return new QPreference(null, name, 5);
    }

    public static QPreference candidate()
    {
        return jdoCandidate;
    }

    public static QPreference parameter(String name)
    {
        return new QPreference(Preference.class, name, ExpressionType.PARAMETER);
    }

    public static QPreference variable(String name)
    {
        return new QPreference(Preference.class, name, ExpressionType.VARIABLE);
    }

    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preference.PreferenceType> type;
    public final org.isisaddons.app.kitchensink.dom.contrib.contributee.QPerson person;
    public final org.isisaddons.app.kitchensink.dom.contrib.contributee.QFoodStuff foodStuff;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preferences> contributedObjects;

    public QPreference(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.type = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preference.PreferenceType>(this, "type");
        if (depth > 0)
        {
            this.person = new org.isisaddons.app.kitchensink.dom.contrib.contributee.QPerson(this, "person", depth-1);
        }
        else
        {
            this.person = null;
        }
        if (depth > 0)
        {
            this.foodStuff = new org.isisaddons.app.kitchensink.dom.contrib.contributee.QFoodStuff(this, "foodStuff", depth-1);
        }
        else
        {
            this.foodStuff = null;
        }
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preferences>(this, "contributedObjects");
    }

    public QPreference(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.type = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preference.PreferenceType>(this, "type");
        this.person = new org.isisaddons.app.kitchensink.dom.contrib.contributee.QPerson(this, "person", 5);
        this.foodStuff = new org.isisaddons.app.kitchensink.dom.contrib.contributee.QFoodStuff(this, "foodStuff", 5);
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.contributedObjects = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.contrib.contributed.Preferences>(this, "contributedObjects");
    }
}
