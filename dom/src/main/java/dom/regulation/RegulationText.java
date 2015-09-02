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

//import java.math.BigDecimal;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.JDOHelper;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.joda.time.LocalDate;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.eventbus.ActionInteractionEvent;
import org.apache.isis.applib.services.eventbus.EventBusService;
import org.apache.isis.applib.services.wrapper.WrapperFactory;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.util.TitleBuffer;

import xml.skos.MySKOSConcept;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Regulation_description_must_be_unique", 
            members={"ownedBy","regulationTitle"})
})
@javax.jdo.annotations.Queries( {
        @javax.jdo.annotations.Query(
                name = "findByOwnedBy", language = "JDOQL",
                value = "SELECT "
                        + "FROM dom.regulation.RegulationText "
                        + "WHERE ownedBy == :ownedBy"),
        @javax.jdo.annotations.Query(
        name = "findRegulations", language = "JDOQL",
        value = "SELECT "
                + "FROM dom.regulation.RegulationText "),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndCompleteIsFalse", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.RegulationText "
                    + "WHERE ownedBy == :ownedBy "
                    + "   && finalized == false"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndCompleteIsTrue", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.RegulationText "
                    + "WHERE ownedBy == :ownedBy "
                    + "&& finalized == true"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndKpi", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.RegulationText "
                    + "WHERE ownedBy == :ownedBy "
                    + "&& kpi == :kpi"),
    @javax.jdo.annotations.Query(
            name = "findByOwnedByAndRegulationTitleContains", language = "JDOQL",
            value = "SELECT "
                    + "FROM dom.regulation.RegulationText "
                    + "WHERE ownedBy == :ownedBy && "
                    + "regulationTitle.indexOf(:regulationTitle) >= 0"),
   @javax.jdo.annotations.Query(
            name = "findByClauseOwnedBy", language = "JDOQL",
            value = "SELECT "
                  + "FROM dom.regulation.Clause "
                  + "WHERE ownedBy == :ownedBy"),
   @javax.jdo.annotations.Query(
                          name = "findByListItem", language = "JDOQL",
                          value = "SELECT "
                                + "FROM dom.regulation.ListItem "
                                + "WHERE ownedBy == :ownedBy"),
        @javax.jdo.annotations.Query(
                name = "findByRegulationText", language = "JDOQL",
                value = "SELECT " + "FROM dom.regulation.RegulationText "
                        + "WHERE ownedBy == :ownedBy"),
    @javax.jdo.annotations.Query(
            name = "findByDefinitionItem", language = "JDOQL",
            value = "SELECT "
                  + "FROM dom.regulation.DefinitionItem "
                  + "WHERE ownedBy == :ownedBy")

})
@DomainObject(objectType="REGULATIONTEXT",autoCompleteRepository=RegulationTexts.class, autoCompleteAction="autoComplete")
 // default unless overridden by autoCompleteNXxx() method
//@Bookmarkable
@MemberGroupLayout (
		columnSpans={4,4,4,12},
		left={"Regulation Text (Edit)","Semantified Text","Regulation Tags (Edit)"},
		middle={"Rule (Update)","Definition (Update)"},
        right={"Search Term (Interpretation)","Select Ship Class (Interpretation)"})
public class RegulationText implements Categorized, Comparable<RegulationText> {

    //region > LOG
    /**
     * It isn't common for entities to log, but they can if required.  
     * Isis uses slf4j API internally (with log4j as implementation), and is the recommended API to use.
     */
    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RegulationText.class);
      //endregion

    // region > title, icon
    public String title() {
        final TitleBuffer buf = new TitleBuffer();
        buf.append(getRegulationTitle());
        return buf.toString();
    }
    //endregion


    // Region plainRegulationText
    private String plainRegulationText;
    @javax.jdo.annotations.Column(allowsNull="false", length=10000)
    @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Regulation Text (Edit)", sequence="10")
    @PropertyLayout(typicalLength=10000, multiLine=8)
    public String getPlainRegulationText() {
      return plainRegulationText;
    }
    public void setPlainRegulationText(final String plainRegulationText) {
     this.plainRegulationText = plainRegulationText;
    }
    public void modifyPlainRegulationText(final String plainRegulationText) {
        setPlainRegulationText(plainRegulationText);
    }
    public void clearPlainRegulationText() {
        setPlainRegulationText(null);
    }
    //endregion


    // Region semantifiedRegulationText
    private String semantifiedRegulationText;
    @javax.jdo.annotations.Column(allowsNull="true", length=10000)
   // @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Semantified Text", sequence="10")
    @PropertyLayout(typicalLength=10000, multiLine=8)
    @Property(editing = Editing.DISABLED,editingDisabledReason = "Update using action that calls an SPI from the consolidation services")
    public String getSemantifiedRegulationText() {
        return semantifiedRegulationText;
    }
    public void setSemantifiedRegulationText(final String semantifiedRegulationText) {
        this.semantifiedRegulationText = semantifiedRegulationText;
    }
    public void modifySemantifiedRegulationText(final String semantifiedRegulationText) {
        setSemantifiedRegulationText(semantifiedRegulationText);
    }
    public void clearSemantifiedRegulationText() {
        setSemantifiedRegulationText(null);
    }
    @Action(semantics = SemanticsOf.IDEMPOTENT)
    @ActionLayout(named = "Semantify", position = ActionLayout.Position.PANEL)
    @MemberOrder(name="semantifiedRegulationText",
            sequence="10")
    public RegulationText updateSemantifiedRegulationText() {
                // Call API to do semantification:
                String AsyncRestTest = restClientTest.SkosFreetextAsync();
                this.setSemantifiedRegulationText(AsyncRestTest);
                container.flush();
                container.informUser("Semantify compeleted for " + container.titleOf(this));
                System.out.print("Calling Semantify here!!");
                return this;
     }
     //endregion



    // Region target
    private String target;
    @javax.jdo.annotations.Column(allowsNull="true", length=3000)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Rule (Update)", sequence="10")
    @PropertyLayout(typicalLength=3000, multiLine=3)
    public String getTarget() {
        return target;
    }
    public void setTarget(final String target) {
        this.target = target;
    }
    public void modifyTarget(final String target) {
        setTarget(target);
    }
    public void clearTarget() {
        setTarget(null);
    }
    //endregion


    // Region requirement
    private String requirement;
    @javax.jdo.annotations.Column(allowsNull="true", length=3000)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Rule (Update)", sequence="20")
    @PropertyLayout(typicalLength=3000, multiLine=3)
    public String getRequirement() {
        return requirement;
    }
    public void setRequirement(final String requirement) {
        this.requirement = requirement;
    }
    public void modifyRequirement(final String requirement) {
        setRequirement(requirement);
    }
    public void clearRequirement() {
        setRequirement(null);
    }
    //endregion


    // Region context
    private String context;
    @javax.jdo.annotations.Column(allowsNull="true", length=3000)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Rule (Update)", sequence="30")
    @PropertyLayout(typicalLength=3000, multiLine=3)
    public String getContext() {
        return context;
    }
    public void setContext(final String context) {
        this.context = context;
    }
    public void modifyContext(final String context) {
        setContext(context);
    }
    public void clearContext() {
        setContext(null);
    }
    //endregion


    // Region exception
    private String exception;
    @javax.jdo.annotations.Column(allowsNull="true", length=3000)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Rule (Update)", sequence="40")
    @PropertyLayout(typicalLength=3000, multiLine=3)
    public String getException() {
        return exception;
    }
    public void setException(final String exception) {
        this.exception = exception;
    }
    public void modifyException(final String exception) {
        setException(exception);
    }
    public void clearException() {
        setException(null);
    }
    //endregion



    // Region definitionName
    private String definitionName;
    @javax.jdo.annotations.Column(allowsNull="true", length=100)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Definition (Update)", sequence="10")
    @PropertyLayout(typicalLength=100)
    public String getDefinitionName() {
        return definitionName;
    }
    public void setDefinitionName(final String definitionName) {
        this.definitionName = definitionName;
    }
    public void modifyDefinitionName(final String definitionName) {
        setDefinitionName(definitionName);
    }
    public void clearDefinitionName() {
        setDefinitionName(null);
    }
    //endregion


    // Region definitionDescription
    private String definitionDescription;
    @javax.jdo.annotations.Column(allowsNull="true", length=100)
    //@Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Definition (Update)", sequence="20")
    @PropertyLayout(typicalLength=300,multiLine=3)
    public String getDefinitionDescription() {
        return definitionDescription;
    }
    public void setDefinitionDescription(final String definitionDescription) {
        this.definitionDescription= definitionDescription;
    }
    public void modifyDefinitionDescription(final String definitionDescription) {
        setDefinitionDescription(definitionDescription);
    }
    public void clearDefinitionDescription() {
        setDefinitionDescription(null);
    }
    //endregion



    // Region regulationTitle
    private String regulationTitle;
    @javax.jdo.annotations.Column(allowsNull="false", length=255)
    @Property(regexPattern="\\w[@&:\\-\\,\\.\\+ \\w]*")
    @MemberOrder(name="Regulation Tags (Edit)", sequence="10")
    @PropertyLayout(typicalLength=80)
    public String getRegulationTitle() {
        return regulationTitle;
    }
    public void setRegulationTitle(final String regulationTitle) {
        this.regulationTitle = regulationTitle;
    }
    public void modifyRegulationTitle(final String regulationTitle) {
        setRegulationTitle(regulationTitle);
    }
    public void clearRegulationTitle() {
        setRegulationTitle(null);
    }
    //endregion

    //region > regulationType (property)
    public static enum RegulationType {
        Certificate,
        Procedure,
        Checklist,
        TechnicalSpecification,
        OperationalSpecification,
        FunctionalRequirement,
        GoalBasedRegulation,
        Guideline,
        ReportSpecification,
        Template,
        Other;
    }
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private RegulationType regulationType;
    @javax.jdo.annotations.Column(allowsNull="true")
    //@Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update regulationType")
    @MemberOrder(name="Regulation Tags (Edit)", sequence="20")
    public RegulationType getRegulationType() {
        return regulationType;
    }
    public void setRegulationType(final RegulationType regulationType) {
        this.regulationType = regulationType;
    }
    //endregion



//region > kpi (property)
    	   public static enum KPI {
    		   ManagementLeadershipAndAccountability,
    		   RecruitmentAndManagementOfShoreBasedPersonnel,
    		   RecruitmentAndManagementOfShipPersonnel,
    		   ReliabilityAndMaintenanceStandards,
    		   NavigationalSafety,
    		   CargoAndBallastOperations,
    		   ManagementOfChange,
    		   IncidentInvestigationAndAnalysis,
    		   SafetyManagementShoreBasedMonitoring,
    		   EnvironmentalManagement,
    		   EmergencyPreparednessAndContingencyPlanning,
    		   MeasurementAnalysisAndImprovement;
    }
 @javax.jdo.annotations.Persistent(defaultFetchGroup="true")  
    private KPI kpi;
    @javax.jdo.annotations.Column(allowsNull="true")
  //   @Property(editing= Editing.DISABLED,editingDisabledReason="Use action to update kpi")
    /*The @Disabled annotation means that the member cannot be used in any instance of the class. When applied to the property it means that the user may not modify the value of that property (though it may still be modified programmatically). When applied to an action method, it means that the user cannot invoke that method.*/
    @MemberOrder(name="Regulation Tags (Edit)", sequence="30")
    public KPI getKpi() {
        return kpi;
    }
    public void setKpi(final KPI kpi) {
        this.kpi = kpi;
    }
    //endregion


    // Region regulationAND
    private boolean regulationAND;
    @javax.jdo.annotations.Column(allowsNull="true")
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    @MemberOrder(name="Regulation Tags (Edit)", sequence="40")
    public boolean getRegulationAND() {
        return regulationAND;
    }
    public void setRegulationAND(final boolean regulationAND) {
        this.regulationAND = regulationAND;
    }
    //end region




    // region Invalidated (property)
    private boolean invalidated;
    @javax.jdo.annotations.Column(allowsNull="true")
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    @MemberOrder(name="Regulation Tags (Edit)", sequence="50")
    public boolean getInvalidated() {
        return invalidated;
    }
    public void setInvalidated(final boolean invalidated) {
        this.invalidated = invalidated;
    }
    // end region
  

    
    //region > ownedBy (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private String ownedBy;
    @PropertyLayout(hidden=Where.EVERYWHERE)
	@ActionLayout(hidden=Where.EVERYWHERE)
    @javax.jdo.annotations.Column(allowsNull="false")
    @Property(editing= Editing.DISABLED,editingDisabledReason="OwnedBy is static")
    public String getOwnedBy() {
        return ownedBy;
    }
	@ActionLayout(hidden=Where.EVERYWHERE)
    public void setOwnedBy(final String ownedBy) {
        this.ownedBy = ownedBy;
    }
    //endregion


    // Start region amendmentDate
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private LocalDate amendmentDate;
    @Property(editing= Editing.DISABLED,editingDisabledReason="Always set to current date")
    @PropertyLayout(hidden=Where.EVERYWHERE)
    @ActionLayout(hidden=Where.EVERYWHERE)
    @javax.jdo.annotations.Column(allowsNull="true")
    public LocalDate getAmendmentDate() {
        return amendmentDate;
    }
    public void setAmendmentDate(final LocalDate amendmentDate) {this.amendmentDate = amendmentDate;}
    public void clearAmendmentDate() {
        setAmendmentDate(null);
    }
    public String validateAmendmentDate(final LocalDate amendmentDate) {
        if (amendmentDate == null) {
            return null;
        }
        //return regulations.validateAmendmentDate(amendmentDate);
        return null; // do not want to test
    }
    //endregion


	 //region > finalized (property)
	   @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    private boolean finalized;
   // @Property(editing= Editing.DISABLED,editingDisabledReason="Finalized is changed elsewhere")
       @javax.jdo.annotations.Column(allowsNull="true")
       @MemberOrder(name="Regulation Tags (Edit)", sequence="60")
       public boolean getFinalized() {
	    	        return finalized;
	    }
	    public void setFinalized(final boolean finalized) {
	        this.finalized = finalized;
	    }
	   // public boolean isFinalized() {
	     //   return finalized;
	    //}
	    //endregion
	   


    //region > version (derived property)
    @PropertyLayout(hidden=Where.EVERYWHERE)
    @ActionLayout(hidden=Where.EVERYWHERE)
    public Long getVersionSequence() {
        if(!(this instanceof javax.jdo.spi.PersistenceCapable)) {
            return null;
        } 
        javax.jdo.spi.PersistenceCapable persistenceCapable = (javax.jdo.spi.PersistenceCapable) this;
        final Long version = (Long) JDOHelper.getVersion(persistenceCapable);
        return version;}
    // hide property (imperatively, based on state of object)
    public boolean hideVersionSequence() {
        return !(this instanceof javax.jdo.spi.PersistenceCapable);
    }
    //endregion


    //region > id (derived property)
    @PropertyLayout(hidden=Where.EVERYWHERE)
    @ActionLayout(hidden=Where.EVERYWHERE)
    public Long getId() {
        if(!(this instanceof javax.jdo.spi.PersistenceCapable)) {
            return null;
        }
        javax.jdo.spi.PersistenceCapable persistenceCapable = (javax.jdo.spi.PersistenceCapable) this;
        final Long id = (Long) JDOHelper.getObjectId(persistenceCapable);
        return id;
    }
    // hide property (imperatively, based on state of object)
    public boolean hideId() {
        return !(this instanceof javax.jdo.spi.PersistenceCapable);
    }
    //endregion

    //region > id (derived property)
    @PropertyLayout(hidden=Where.EVERYWHERE)
    @ActionLayout(hidden=Where.EVERYWHERE)
    public String getIdString() {
        if(!(this instanceof javax.jdo.spi.PersistenceCapable)) {
            return null;
        }
        javax.jdo.spi.PersistenceCapable persistenceCapable = (javax.jdo.spi.PersistenceCapable) this;
        final String id = (String) JDOHelper.getObjectId(persistenceCapable).toString();
        final int indexEnd = id.indexOf("[");
        return id.substring(0,indexEnd);
    }

    // region > ParentRegulation
    private RegulationText parentRegulation;
    @javax.jdo.annotations.Persistent(defaultFetchGroup="true")
    @javax.jdo.annotations.Column(allowsNull="true")
    // @Mandatory
    @MemberOrder(name="Regulation Tags (Edit)",sequence = "70")
   // @Property(editing=Editing.DISABLED)
    public RegulationText getParentRegulation()
    { return parentRegulation; }
    public void setParentRegulation(final RegulationText parentRegulation)
    { this.parentRegulation = parentRegulation; }
    public List<RegulationText> choicesParentRegulation()
    { List<RegulationText> list = container.allInstances(RegulationText.class);
        list.remove(this);
     return list;
    }
// end region ParentRegulation.



    // Region searchTerm
    private String searchTerm;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Search Term (Interpretation)", sequence="10")
    @PropertyLayout(typicalLength=100)
    public String getSearchTerm() {
        return searchTerm;
    }
    public void setSearchTerm(final String searchTerm) {
        this.searchTerm = searchTerm;
    }
    public void modifySearchTerm(final String searchTerm) {
        setSearchTerm(searchTerm);
    }
    public void clearSearchTerm() {
        setSearchTerm(null);
    }
    @MemberOrder(name="searchTerm", sequence="11")
    @Action(semantics = SemanticsOf.IDEMPOTENT)
    public RegulationText searchTerm() {
        String restTest = restClientTest.SkosFreetext();
        this.setDefinitionTerm(restTest);
        container.flush();
        container.informUser("Search Term finished for " + container.titleOf(this));
        return this;
    }
    //endregion



    // Region broaderTerm
    private String broaderTerm;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Search Term (Interpretation)", sequence="20")
    @PropertyLayout(typicalLength=100)
    @Property(editing=Editing.DISABLED)
    public String getBroaderTerm() {
        return broaderTerm;}
    public void setBroaderTerm(final String broaderTerm) {
        this.broaderTerm = broaderTerm;}
    public void modifyBroaderTerm(final String broaderTerm) {
        setBroaderTerm(broaderTerm);}
    public void clearBroaderTerm() {
        setBroaderTerm(null);}
    //endregion


    // Region preferredTerm
    private String preferredTerm;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Search Term (Interpretation)", sequence="30")
    @PropertyLayout(typicalLength=100)
    @Property(editing=Editing.DISABLED)
    public String getPreferredTerm() {
        return preferredTerm;}
    public void setPreferredTerm(final String preferredTerm) {
        this.preferredTerm = preferredTerm;}
    public void modifyPreferredTerm(final String preferredTerm) {
        setPreferredTerm(preferredTerm);}
    public void clearPreferredTerm() {
        setPreferredTerm(null);}
    //endregion



    // Region definitionTerm
    private String definitionTerm;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Search Term (Interpretation)", sequence="40")
    @PropertyLayout(typicalLength=100)
    @Property(editing=Editing.DISABLED)
    public String getDefinitionTerm() {
        return definitionTerm;}
    public void setDefinitionTerm(final String definitionTerm) {
        this.definitionTerm = definitionTerm;}
    public void modifyDefinitionTerm(final String definitionTerm) {
        setDefinitionTerm(definitionTerm);}
    public void clearDefinitionTerm() {
        setDefinitionTerm(null);}
    //endregion



    // Region searchShipTerm
    private String searchShipClass;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Select Ship Class (Interpretation)", sequence="10")
    @PropertyLayout(typicalLength=100, named = "Ship Class")
    //@Property(editing=Editing.DISABLED)
    public String getSearchShipClass() {
        return searchShipClass;}
    public void setSearchShipClass(final String searchShipClass) {
        this.searchShipClass = searchShipClass;}
    public void modifySearchShipClass(final String searchShipClass) {
        setSearchShipClass(searchShipClass);}
    public void clearSearchShipClass() {
        setSearchShipClass(null);}
    // IDEMPOTENT correct?
    @MemberOrder(name="searchShipClass", sequence="20")
    //@javax.jdo.annotations.Column(allowsNull="true")
    @Action(semantics = SemanticsOf.IDEMPOTENT)
   // public ShipType searchShipClass() {
    public RegulationText searchShipClass() {
        // Must change to shipType:  ShipType restTest = restClientTest.fetchSKOSconcept();
        System.out.println("restTestSKOSconcept = restClientTest.fetchSKOSconcept();");
        MySKOSConcept restTestSKOSconcept = restClientTest.fetchSKOSconcept();
        // Nå returnerer bare null!!
        System.out.println("stringShip");
        // Må returnere en liste av mulige ship classes her!!
      //  SKOSConceptProperty skosConProp = restTestSKOSconcept.getSkosConceptProperty();
       // System.out.println("skosConProp = " + skosConProp);
       // String stringShip = skosConProp.toString();
        //System.out.println("stringShip = " + stringShip);
        //System.out.println("this.setListOfShipClasses(stringShip);");
        //this.setListOfShipClasses(stringShip);

        System.out.println("flush...");
        container.flush();
        container.informUser("Search Ship Class finished for " + container.titleOf(this));
     //   return restTestSKOSconcept.getText(); // Denne må endres til å hente ship class-list!!
        this.setListOfShipClasses("ListOfShipClasses");
        return this;
    }
    //endregion

// region select ship class
    @MemberOrder(name="listOfShipClasses", sequence="30")
    //@javax.jdo.annotations.Column(allowsNull="true")
    @Action(semantics = SemanticsOf.IDEMPOTENT)
    // public ShipType searchShipClass() {
    public RegulationText selectShipClass() {
        // Must change to shipType:  ShipType restTest = restClientTest.fetchSKOSconcept();
        // Must change this!Must add a link to an instance containing the position in the string, the listed name and the ship class.
        this.setSelectedShipClass("Selected ship class");
        return this;
        }
    // end region select ship class



/**
    // Region listOfShipClasses
    private ShipType listOfShipClasses;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Select Ship Class (Interpretation)", sequence="30")
    @PropertyLayout(typicalLength=100)
    // @Property(editing=Editing.DISABLED)
    public ShipType getListOfShipClasses() {
        return listOfShipClasses;}
    public void setListOfShipClasses(final ShipType listOfShipClasses) {
        this.listOfShipClasses = listOfShipClasses;}
    public void modifyListOfShipClasses(final ShipType listOfShipClasses) {
        setListOfShipClasses(listOfShipClasses);}
    public void clearListOfShipClasses() {
        setListOfShipClasses(null);}
    //endregion
**/
    // Region listOfShipClasses: Must be updated to list of ship classes.
    private String listOfShipClasses;
    @javax.jdo.annotations.Column(allowsNull="true", length=100)
    @MemberOrder(name="Select Ship Class (Interpretation)", sequence="40")
    @PropertyLayout(typicalLength=100)
    // @Property(editing=Editing.DISABLED)
    public String getListOfShipClasses() {
        return listOfShipClasses;}
    public void setListOfShipClasses(final String listOfShipClasses) {
        this.listOfShipClasses = listOfShipClasses;}
    public void modifyListOfShipClasses(final String listOfShipClasses) {
        setListOfShipClasses(listOfShipClasses);}
    public void clearListOfShipClasses() {
        setListOfShipClasses(null);}
    //endregion

    // Region searchShipTerm
    private String selectedShipClass;
    @javax.jdo.annotations.Column(allowsNull="true", length=1000)
    @MemberOrder(name="Select Ship Class (Interpretation)", sequence="50")
    @PropertyLayout(typicalLength=100, named = "Selected Ship Class")
    //@Property(editing=Editing.DISABLED)
    public String getSelectedShipClass() {
        return searchShipClass;}
    public void setSelectedShipClass(final String selectedShipClass) {
        this.selectedShipClass = selectedShipClass;}
// end region

    //region > lifecycle callbacks

    public void created() {
        LOG.debug("lifecycle callback: created: " + this.toString());
    }
    public void loaded() {
        LOG.debug("lifecycle callback: loaded: " + this.toString());
    }
    public void persisting() {
        LOG.debug("lifecycle callback: persisting: " + this.toString());
    }
    public void persisted() {
        LOG.debug("lifecycle callback: persisted: " + this.toString());
    }
    public void updating() {
        LOG.debug("lifecycle callback: updating: " + this.toString());
    }
    public void updated() {
        LOG.debug("lifecycle callback: updated: " + this.toString());
    }
    public void removing() {
        LOG.debug("lifecycle callback: removing: " + this.toString());
    }
    public void removed() {
        LOG.debug("lifecycle callback: removed: " + this.toString());
    }
    //endregion

    //region > object-level validation

    /**
     * In a real app, if this were actually a rule, then we'd expect that
     * invoking the {@link #completed() done} action would clear the {@link #getDueBy() dueBy}
     * property (rather than require the user to have to clear manually).
     */
    //endregion


    //region similar Regulations found by semantic-ontological search.
    // Search Similar Regulations (Interpretation)
    // Do not need to store the search result!!
    // Combined semantic and ontological search!
    //region > dependencies (property), add (action), remove (action)
    private Set<RegulationText> similarRegulations = new TreeSet<>();
    //private SortedSet<ToDoItem> dependencies = new TreeSet<>();  // not compatible with neo4j (as of DN v3.2.3)
    @Collection()
    @CollectionLayout(  render = RenderType.EAGERLY) // not compatible with neo4j (as of DN v3.2.3)
    public Set<RegulationText> getSimilarRegulations() {return similarRegulations;}
    public void setSimilarRegulations(Set<RegulationText> similarRegulations) {this.similarRegulations=similarRegulations;}
    @MemberOrder(name="similarRegulations", sequence="20")
    public RegulationText SearchSimilarRegulations(){
        setSimilarRegulations(restClientTest.findSimilarRegulations(this));
        return this;
    }
     //endregion similar Regulations found by semantic-ontological search.

    //region > events
    public static abstract class AbstractActionInteractionEvent extends ActionInteractionEvent<RegulationText> {
        private static final long serialVersionUID = 1L;
        private final String description;
        public AbstractActionInteractionEvent(
                final String description,
                final RegulationText source,
                final Identifier identifier,
                final Object... arguments) {
            super(source, identifier, arguments);
            this.description = description;
        }
        public String getEventDescription() {
            return description;
        }
    }

    public static class DeletedEvent extends AbstractActionInteractionEvent {
        private static final long serialVersionUID = 1L;
        public DeletedEvent(
                final RegulationText source,
                final Identifier identifier,
                final Object... arguments) {
            super("deleted", source, identifier, arguments);
        }
    }

    //endregion


    //region > toString, compareTo
    @Override
    public String toString() {
//        return ObjectContracts.toString(this, "description,complete,dueBy,ownedBy");
        return ObjectContracts.toString(this, "regulationTitle,plainRegulationText, ownedBy");
    }

    /**
     * Required so can store in {@link SortedSet sorted set}s (eg {@link #getDependencies()}). 
     */
    @Override
    public int compareTo(final RegulationText other) {
        return ObjectContracts.compare(this, other, "regulationTitle,plainRegulationText, ownedBy");
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

    @javax.inject.Inject
    private RegulationTexts regulationTexts;

    @javax.inject.Inject
    private RESTclientTest restClientTest;

    @SuppressWarnings("deprecation")
	Bulk.InteractionContext bulkInteractionContext;
    public void injectBulkInteractionContext(@SuppressWarnings("deprecation") Bulk.InteractionContext bulkInteractionContext) {
        this.bulkInteractionContext = bulkInteractionContext;
    }

    @javax.inject.Inject
//    private Scratchpad scratchpad;

    EventBusService eventBusService;
    public void injectEventBusService(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }

    @javax.inject.Inject
    private WrapperFactory wrapperFactory;

    //endregion
}
