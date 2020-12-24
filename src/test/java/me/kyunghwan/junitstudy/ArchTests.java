package me.kyunghwan.junitstudy;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchTests {

    @Test
    void packageDependencyTests(){
        JavaClasses javaClass = new ClassFileImporter().importPackages("me.kyunghwan.junitstudy");

        ArchRule domainPackgeRule = classes().that().resideInAnyPackage("..domain..")
                .should().onlyBeAccessed().byClassesThat()
                .resideInAnyPackage("..study..", "..member.." , "..domain..");

        domainPackgeRule.check(javaClass);

        ArchRule memberPackageRule = noClasses().that().resideInAnyPackage("..domain")
                .should().accessClassesThat().resideInAnyPackage("..member..");

        memberPackageRule.check(javaClass);

        ArchRule studyPackageRule = noClasses().that().resideOutsideOfPackage("..study..")
                .should().accessClassesThat().resideInAnyPackage("..study..");

        studyPackageRule.check(javaClass);

        ArchRule freeOfCycles = slices().matching("..junitstudy.(*)..")
                .should().beFreeOfCycles();
        freeOfCycles.check(javaClass);
    }

}
