import org.example.Polygon
import org.example.TooFewSidesException
import spock.lang.Specification

class ExampleSpecification extends Specification {

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {

        when:
        int sides = new Polygon(4).getNumberOfSides()

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect an Exception to be thrown for a number of invalid inputs"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 3, 2]
    }
}
