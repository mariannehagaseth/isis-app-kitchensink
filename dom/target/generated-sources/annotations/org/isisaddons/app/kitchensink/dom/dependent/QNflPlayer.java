package org.isisaddons.app.kitchensink.dom.dependent;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QNflPlayer extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<NflPlayer> implements PersistableExpression<NflPlayer>
{
    public static final QNflPlayer jdoCandidate = candidate("this");

    public static QNflPlayer candidate(String name)
    {
        return new QNflPlayer(null, name, 5);
    }

    public static QNflPlayer candidate()
    {
        return jdoCandidate;
    }

    public static QNflPlayer parameter(String name)
    {
        return new QNflPlayer(NflPlayer.class, name, ExpressionType.PARAMETER);
    }

    public static QNflPlayer variable(String name)
    {
        return new QNflPlayer(NflPlayer.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression name;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.dependent.NflLeague> league;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.dependent.NflRegion> region;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.dependent.NflTeam> team;
    public final ObjectExpression<org.apache.isis.applib.DomainObjectContainer> container;
    public final ObjectExpression<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers> nflPlayers;

    public QNflPlayer(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.name = new StringExpressionImpl(this, "name");
        this.league = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflLeague>(this, "league");
        this.region = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflRegion>(this, "region");
        this.team = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflTeam>(this, "team");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.nflPlayers = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers>(this, "nflPlayers");
    }

    public QNflPlayer(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.name = new StringExpressionImpl(this, "name");
        this.league = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflLeague>(this, "league");
        this.region = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflRegion>(this, "region");
        this.team = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflTeam>(this, "team");
        this.container = new ObjectExpressionImpl<org.apache.isis.applib.DomainObjectContainer>(this, "container");
        this.nflPlayers = new ObjectExpressionImpl<org.isisaddons.app.kitchensink.dom.dependent.NflPlayers>(this, "nflPlayers");
    }
}
