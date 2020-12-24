package me.kyunghwan.junitstudy;

import me.kyunghwan.junitstudy.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Order(2)
    @DisplayName("스터디 만들기 fast")
    @FastTest
    void create_new_study(){
        Study study = new Study(100);
        System.out.println(value++);
    }


    @Order(1)
    @DisplayName("스터지 만들기 slow")
    @Test
    void create1_new_study_again() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("slow slow slow");
        System.out.println(value++);
    }

    @Order(3)
    @DisplayName("스터디 반복")
    @RepeatedTest(value = 10, name = "{displayName} , {currentRepetition}/{totalRepetitions}")
    void create_study_repeated(){
        System.out.println("반복");
    }

    @Order(4)
    @DisplayName("날씨")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'" ,"20 ,스프링"})
    void parmaeterizedTest(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0) , argumentsAccessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter{

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "can only convert Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

//    @BeforeAll //static 선언!
//    static void beforeAll(){
//        System.out.println("before all");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("after all");
//    }
//
//    @BeforeEach
//    void beforeEach(){
//        System.out.println("before each");
//    }
//
//    @AfterEach
//    void afterEach(){
//        System.out.println("after each");
//    }

}