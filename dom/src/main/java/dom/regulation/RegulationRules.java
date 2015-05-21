/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package dom.regulation;


import dom.regulation.RegulationRule;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.SortedBy;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.CollectionInteraction;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Bulk.InteractionContext.InvokedAs;


//@DomainObjectLayout(named="Regulation List Item")
@DomainObjectLayout(named="Rule")
@DomainServiceLayout(menuOrder = "30")
@DomainService(repositoryFor = RegulationRule.class)
public class RegulationRules {

  
    //region > newClause (action)
    @MemberOrder(sequence = "5")
    @ActionLayout(named="Add New Rule")
    public RegulationRule newRegulationRule(
            final @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") @ParameterLayout(named="Section Title") String sectionTitle, 
            final  											 @ParameterLayout(named="Target") String hasTarget,
            final  @Parameter(optionality=Optionality.OPTIONAL)@ParameterLayout(named="Context") String hasContext,
            final  											 @ParameterLayout(named="Requirement") String hasRequirement,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RESTTest") String showRestTest,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Exception") String hasException,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject") String subject,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") boolean invalidated,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="IsMandatory") boolean isMandatory,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RuleAND") boolean ruleAND,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RuleOR") boolean ruleOR,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Regulation") Regulation regulation
            //,
            //final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Rule") Rule rule
            ) 
    {
        return newRegulationRule(sectionTitle,
        		hasTarget,
        		hasContext,
        		hasRequirement,
        		showRestTest,
        		hasException,
        		subject, 
        		invalidated, 
        		isMandatory,
        		ruleAND,
        		ruleOR,
        //		regulation,
        //		rule,
        		currentUserName());
    }
    public String default0NewRegulationRule() {
        return "Section 1.1 ";
    }

    public String default1NewRegulationRule() {
        return "";
    }
    public String default2NewRegulationRule() {
        return "";
    }
    public String default3NewRegulationRule() {
        return "";
    }
    public String default4NewRegulationRule() {
        return "";
    }
    
    public String default5NewRegulationRule() {
        return "";
    }
    public String default6NewRegulationRule() {
        return "";
    }
    public boolean default7NewRegulationRule() {
        return false;
    }
    public boolean default8NewRegulationRule() {
        return true;
    }
    public boolean default9NewRegulationRule() {
        return false;
    }
    public boolean default10NewRegulationRule() {
        return false;
    }

     

    //region > allFreeText (action)
    
    @Action(semantics=SemanticsOf.SAFE,restrictTo=RestrictTo.PROTOTYPING)
    @MemberOrder(sequence = "6")
    @ActionLayout(named="List all Rules")
    public List<RegulationRule> allRegulationRule() {
        final List<RegulationRule> items = container.allMatches(
                new QueryDefault<RegulationRule>(RegulationRule.class, 
                        "findByRegulationRule", 
                        "ownedBy", currentUserName()));
        if(items.isEmpty()) {
            container.warnUser("No Sections found.");
        }
        return items;
    }
    //endregion


    //region > autoComplete (programmatic)
    @Programmatic // not part of metamodel
    public List<RegulationRule> autoComplete(final String regulationRule) {
        return container.allMatches(
                new QueryDefault<RegulationRule>(RegulationRule.class, 
                        "findByRegulationRule", 
                        "ownedBy", currentUserName()));
    }
    //endregion

    //region > helpers
    @Programmatic // for use by fixtures
    /*The @Programmatic annotation can be used to cause Apache Isis to complete ignore a class member. 
     * This means it won't appear in any viewer, its value will not be persisted, 
     * and it won't appear in any XML snapshots .*/
    public RegulationRule newRegulationRule(
            final String sectionTitle, 
            final String hasTarget,
            final String hasContext,
            final String hasRequirement,
            final String showRestTest,
            final String hasException,
            final String subject,
  //          final Regulation regulation,
 //           final Rule rule,
            final boolean invalidated,
            final boolean isMandatory,
            final boolean ruleAND,
            final boolean ruleOR,
            final String userName
            ) {
        final RegulationRule regulationRule = container.newTransientInstance(RegulationRule.class);
        regulationRule.setSectionTitle(sectionTitle);
        regulationRule.setOwnedBy(userName);
        regulationRule.setHasTarget(hasTarget);
        regulationRule.setHasContext(hasContext);
        regulationRule.setHasRequirement(hasRequirement);
        regulationRule.setShowRestTest(showRestTest);
        regulationRule.setHasException(hasException);
        regulationRule.setSubject(subject);
        regulationRule.setInvalidated(invalidated);
        regulationRule.setIsMandatory(isMandatory);
        regulationRule.setRuleAND(ruleAND);
        regulationRule.setRuleOR(ruleOR);
 //       listItem.setRegulation(regulation);
 //       freeText.setRule(rule);
        
        container.persist(regulationRule);
        container.flush();
        return regulationRule;
    }
    
    private String currentUserName() {
        return container.getUser().getName();
    }

    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

    @javax.inject.Inject
    private ClockService clockService;
    //endregion

}
