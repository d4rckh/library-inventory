package com.dfour.libraryplatform;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.dfour.libraryplatform", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitureTests {

    @ArchTest
    private final ArchRule managers_should_not_depend_on_managers = noClasses()
            .that().resideInAPackage("com.dfour.libraryplatform.manager")
            .should().dependOnClassesThat().resideInAPackage("com.dfour.libraryplatform.manager");

    @ArchTest
    private final ArchRule services_should_not_depend_on_services = noClasses()
            .that().resideInAPackage("com.dfour.libraryplatform.service")
            .should().dependOnClassesThat().resideInAPackage("com.dfour.libraryplatform.service");


    @ArchTest
    private final ArchRule controllers_should_not_depend_on_controllers = noClasses()
            .that().resideInAPackage("com.dfour.libraryplatform.controller")
            .should().dependOnClassesThat().resideInAPackage("com.dfour.libraryplatform.controller");

    @ArchTest
    private final ArchRule repositories_should_not_depend_on_repositories = noClasses()
            .that().resideInAPackage("com.dfour.libraryplatform.repository")
            .should().dependOnClassesThat().resideInAPackage("com.dfour.libraryplatform.repository");

    @ArchTest
    private final ArchRule controllers_should_not_depend_on_repositories = noClasses()
            .that().resideInAPackage("com.dfour.libraryplatform.controller")
            .should().dependOnClassesThat().resideInAPackage("com.dfour.libraryplatform.repository");

}
