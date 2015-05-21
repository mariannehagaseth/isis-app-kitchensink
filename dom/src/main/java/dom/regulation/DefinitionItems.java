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


import dom.regulation.DefinitionItem;

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


@DomainObjectLayout(named="Definition Item")
@DomainServiceLayout(menuOrder = "20.4")
@DomainService(repositoryFor = DefinitionItem.class)
public class DefinitionItems {

  
    //region > newClause (action)
    @MemberOrder(sequence = "5")
    @ActionLayout(named="Add New Definition Item")
    public DefinitionItem newDefinitionItem(
            final @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*") @ParameterLayout(named="Section Title") String sectionTitle, 
            final  						@ParameterLayout(typicalLength=100,named="Defined Term Name") String definedTermName,
            final						@ParameterLayout(typicalLength=500,named="Defined Term Definition") String definedTermDefinition,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject") String subject,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") boolean invalidated,
            final  @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Regulation") Regulation regulation
            ) 
    {
        return newDefinitionItem(sectionTitle, definedTermName, definedTermDefinition, subject, invalidated, 
        		currentUserName());
    }
    public String default0NewDefinitionItem() {
        return "Section 1.1 ";
    }

    public String default1NewDefinitionItem() {
        return "";
    }
    public String default2NewDefinitionItem() {
        return "";
    }
    public String default3NewDefinitionItem() {
        return "";
    }
    public boolean default4NewDefinitionItem() {
        return false;
    }

     

    //region > allFreeText (action)
    
    @Action(semantics=SemanticsOf.SAFE,restrictTo=RestrictTo.PROTOTYPING)
    @MemberOrder(sequence = "6")
    @ActionLayout(named="List all Defined Items")
    public List<DefinitionItem> allDefinitionItem() {
        final List<DefinitionItem> items = container.allMatches(
                new QueryDefault<DefinitionItem>(DefinitionItem.class, 
                        "findByDefinitionItem", 
                        "ownedBy", currentUserName()));
        if(items.isEmpty()) {
            container.warnUser("No Definitions found.");
        }
        return items;
    }
    //endregion


    //region > autoComplete (programmatic)
    @Programmatic // not part of metamodel
    public List<DefinitionItem> autoComplete(final String definitionItem) {
        return container.allMatches(
                new QueryDefault<DefinitionItem>(DefinitionItem.class, 
                        "findByDefinitionItem", 
                        "ownedBy", currentUserName()));
    }
    //endregion

    //region > helpers
    @Programmatic // for use by fixtures
    /*The @Programmatic annotation can be used to cause Apache Isis to complete ignore a class member. 
     * This means it won't appear in any viewer, its value will not be persisted, 
     * and it won't appear in any XML snapshots .*/
    public DefinitionItem newDefinitionItem(
            final String sectionTitle, 
            final String definedTermName,
            final String definedTermDefinition,
            final String subject,
  //          final Regulation regulation,
 //           final Rule rule,
            final boolean invalidated,
            final String userName
            ) {
        final DefinitionItem definitionItem = container.newTransientInstance(DefinitionItem.class);
        definitionItem.setSectionTitle(sectionTitle);
        definitionItem.setOwnedBy(userName);
        definitionItem.setDefinedTermName(definedTermName);
        definitionItem.setDefinedTermDefinition(definedTermDefinition);
        definitionItem.setSubject(subject);
        definitionItem.setInvalidated(invalidated);        
        container.persist(definitionItem);
        container.flush();
        return definitionItem;
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
