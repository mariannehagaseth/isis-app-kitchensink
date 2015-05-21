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

import dom.regulation.Regulation;
import dom.regulation.Regulation.DocumentType;
import dom.regulation.Regulation.KPI;
import dom.regulation.Regulation.LanguageType;
import dom.regulation.Regulation.RegulationTemplateType;
import dom.regulation.Regulation.RegulationType;









//import java.math.BigDecimal;
import java.util.List;

import com.google.common.base.Predicates;

import org.joda.time.LocalDate;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.RestrictTo;
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
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk.InteractionContext.InvokedAs;

//@DomainServiceLayout(named="Regulation Hierarchy",menuOrder="10")
@DomainServiceLayout(named="Regulation",menuOrder="10")
@DomainService(repositoryFor = Regulation.class)
public class Regulations {

    //region > notYetFinalized (action)
   // @Bookmarkable
    @Action(semantics=SemanticsOf.SAFE)
    @MemberOrder(sequence = "10")
    public List<Regulation> notYetFinalized() {
        final List<Regulation> items = notYetFinalizedNoUi();
        if(items.isEmpty()) {
            container.informUser("All regulations have been finalized :-)");
        }
        return items;
    }

    @Programmatic
    public List<Regulation> notYetFinalizedNoUi() {
        return container.allMatches(
                new QueryDefault<Regulation>(Regulation.class, 
                        "findByOwnedByAndCompleteIsFalse", 
                        "ownedBy", currentUserName()));
    }
    //endregion

    
    //region > isFinalized (action)
    @Action(semantics=SemanticsOf.SAFE)
    @MemberOrder(sequence = "20")
    public List<Regulation> isFinalized() {
        final List<Regulation> items = isFinalizedNoUi();
        if(items.isEmpty()) {
            container.informUser("No regulations are Finalized!");
        }
        return items;
    }

    @Programmatic
    public List<Regulation> isFinalizedNoUi() {
        return container.allMatches(
            new QueryDefault<Regulation>(Regulation.class, 
                    "findByOwnedByAndCompleteIsTrue", 
                    "ownedBy", currentUserName()));
    }
    //endregion

    //region > categorized (action)
	@SuppressWarnings("unchecked")
	//@Bookmarkable
    @Action(semantics=SemanticsOf.SAFE)
    @MemberOrder(sequence = "40")
	// Added. OK??
	@ActionLayout(named="Search Regulations")
    public List<Regulation> categorized(
    		@ParameterLayout(named="KPI") final KPI kpi,
    		@ParameterLayout(named="Regulation Type") final RegulationType regulationType,
    		@ParameterLayout(named="Finalized?") final boolean finalized) {
    	// an example "naive" implementation (filtered in Java code, not DBMS)
		// ??? Changed from and to or. OK ???
        return container.allMatches(Regulation.class, 
                Predicates.or(
                		Regulation.Predicates.thoseOwnedBy(currentUserName()), 
                		Regulation.Predicates.thoseFinalized(finalized),
                		Regulation.Predicates.thoseKPIs(kpi)));
    }
    public KPI default0Categorized() {
        return KPI.MeasurementAnalysisAndImprovement;
    }
    public RegulationType default1Categorized() {
        return RegulationType.FunctionalRequirement;
    }
    public boolean default2Categorized() {
    	return false;
    }
   
    //endregion

    //region > newRegulation (action)
    @MemberOrder(sequence = "5")
    @ActionLayout(named="Add New Regulation")
    public Regulation newRegulation(
            final  @Parameter(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")  @ParameterLayout(named="Regulation Title") String regulationTitle, 
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(typicalLength=50, multiLine=5,named="Regulation Objective") String objective,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(typicalLength=50, multiLine=5,named="Regulation Text") String regulationText,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Regulation Type") RegulationType regulationType,
            final 												@ParameterLayout(named="Validity Date") LocalDate validityDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="KPI") KPI kpi,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Published In") DocumentType publishedIn,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Amendment Date") LocalDate amendmentDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Applicability Date") LocalDate applicabilityDate,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Expiration Date") LocalDate expirationDate,
            final 											   @ParameterLayout(named="Conforms To (Template)") RegulationTemplateType conformsTo,
            final 											   @ParameterLayout(named="Language") LanguageType language,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Subject (Key words)") String subject,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="IsMandatory") Boolean isMandatory,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RegulationAND") Boolean regulationAND,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="RegulationOR") Boolean regulationOR,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Invalidated") Boolean invalidated,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Finalized") Boolean finalized
    		) 
    {
        return newRegulation(regulationTitle, objective, regulationText, regulationType, validityDate, kpi, currentUserName(),
				publishedIn,
				amendmentDate,
				applicabilityDate,
				expirationDate,
				conformsTo,
				language,
				subject,
				isMandatory,
				regulationAND,
				regulationOR,
				invalidated,
				finalized);
    }
    public String default0NewRegulation() {
        return "Chapter 1";
    }

    public String default1NewRegulation() {
        return "";
    }

    public String default2NewRegulation() {
        return "";
    }
    public RegulationType default3NewRegulation() {
        return RegulationType.FunctionalRequirement;
    }

    public LocalDate default4NewRegulation() {
        return clockService.now().plusDays(60);
    }
    
    public KPI default5NewRegulation() {
        return KPI.NavigationalSafety;
    }

    public DocumentType default6NewRegulation() {
        return  DocumentType.SOLAS;
    }

    public LocalDate default7NewRegulation() {
        return clockService.now();
    }
    
    public LocalDate default8NewRegulation() {
        return clockService.now().plusDays(60);
    }
    public LocalDate default9NewRegulation() {
        return clockService.now().plusDays(6000);
    }
    public RegulationTemplateType default10NewRegulation() {
        return  RegulationTemplateType.Regulation ;
    }
    public LanguageType default11NewRegulation() {
        return  LanguageType.English;
    }
    public String default12NewRegulation() {
        return  "";
    }
    public Boolean default13NewRegulation() {
        return  Boolean.TRUE;
    }
    public Boolean default14NewRegulation() {
        return  Boolean.FALSE;
    } public Boolean default15NewRegulation() {
        return  Boolean.FALSE;
    } public Boolean default16NewRegulation() {
        return  Boolean.FALSE;
    }
   
   /* public String validateNewRegulation(
            final String description, 
            final Category category, final Subcategory subcategory, 
            final LocalDate dueBy, final BigDecimal cost) {
        return Subcategory.validate(category, subcategory);
    }*/
    //endregion

    //region > allToDos (action)
    
    @Action(semantics=SemanticsOf.SAFE,restrictTo=RestrictTo.PROTOTYPING)
    @MemberOrder(sequence = "6")
    // Add this: OK???
    @PropertyLayout(named="List all Regulations")
    public List<Regulation> allRegulations() {
        final List<Regulation> items = container.allMatches(
                new QueryDefault<Regulation>(Regulation.class, 
                        "findByOwnedBy", 
                        "ownedBy", currentUserName()));
        if(items.isEmpty()) {
            container.warnUser("No regulations found.");
        }
        return items;
    }
    //endregion

    //region > autoComplete (programmatic)
    @Programmatic // not part of metamodel
    public List<Regulation> autoComplete(final String regulationTitle) {
        return container.allMatches(
                new QueryDefault<Regulation>(Regulation.class, 
                        "findByOwnedByAndRegulationTitleContains", 
                        "ownedBy", currentUserName(), 
                        "regulationTitle", regulationTitle));
    }
    //endregion

    //region > helpers
    @Programmatic // for use by fixtures
    /*The @Programmatic annotation can be used to cause Apache Isis to complete ignore a class member. 
     * This means it won't appear in any viewer, its value will not be persisted, 
     * and it won't appear in any XML snapshots .*/
    public Regulation newRegulation(
            final String regulationTitle, 
            final String objective,
            final String regulationText,
            final RegulationType regulationType, 
            final LocalDate validityDate, 
            final KPI kpi,
            final String userName,
            final DocumentType publishedIn,
            final LocalDate amendmentDate,
            final LocalDate applicabilityDate,
            final LocalDate expirationDate,
            final RegulationTemplateType conformsTo,
            final LanguageType language,
            final String subject,
            final Boolean isMandatory,
            final Boolean regulationAND,
            final Boolean regulationOR,
            final Boolean invalidated,
            final Boolean finalized
            ) {
        final Regulation regulation = container.newTransientInstance(Regulation.class);
        regulation.setRegulationTitle(regulationTitle);
        regulation.setValidityDate(validityDate);
        regulation.setKpi(kpi);
        regulation.setOwnedBy(userName);
        regulation.setRegulationType(regulationType);
        regulation.setObjective(objective);
        regulation.setRegulationText(regulationText);
        regulation.setPublishedIn(publishedIn);
        regulation.setAmendmentDate(amendmentDate);
        regulation.setApplicabilityDate(applicabilityDate);
        regulation.setExpirationDate(expirationDate);
        regulation.setConformsTo(conformsTo);
        regulation.setLanguage(language);
        regulation.setSubject(subject);
        regulation.setIsMandatory(isMandatory);
        regulation.setRegulationAND(regulationAND);
        regulation.setRegulationOR(regulationOR);
        regulation.setInvalidated(invalidated);
        regulation.setFinalized(finalized);
        container.persist(regulation);
        container.flush();

        return regulation;
    }
    
    private String currentUserName() {
        return container.getUser().getName();
    }

    //endregion

    //region > common validation
    //   private static final long ONE_WEEK_IN_MILLIS = 7 * 24 * 60 * 60 * 1000L;
    @Programmatic
    public String validateValidityDate(LocalDate validityDate) {
        return isInPast(validityDate) ? "The Validity Date cannot be in the past" : null;
    }
    @Programmatic
    boolean isInPast(final LocalDate someDate) {
        return someDate.toDateTimeAtStartOfDay().getMillis() < clockService.nowAsMillis();
    } 
    //endregion

    
    //region > common validation
    @Programmatic
    public String validateAmendmentDate(LocalDate amendmentDate) {
        return isInPast(amendmentDate) ? "Set the amendment date to today, or to a date in the future" : null;
    }
    //endregion

    //region > common validation
    //   private static final long ONE_WEEK_IN_MILLIS = 7 * 24 * 60 * 60 * 1000L;
    @Programmatic
    public String validateApplicabilityDate(LocalDate applicabilityDate) {
        return isInPast(applicabilityDate) ? "Set the applicability date to today, or to a date in the future" : null;
    }
    //endregion

    
    //region > common validation
    //   private static final long ONE_WEEK_IN_MILLIS = 7 * 24 * 60 * 60 * 1000L;
    //@Programmatic
    //public String validateExpirationDate(LocalDate expirationDate) {
    //    return isInPast(expirationDate) ? "Set the expiration date to today, or to a date in the future" : null;
   // }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

    @javax.inject.Inject
    private ClockService clockService;
    //endregion

}
