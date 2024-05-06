import org.example.Colour
import org.example.Palette
import org.example.Polygon
import org.example.Renderer
import org.example.ShapeFactory
import org.example.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

class ExampleSpecification extends Specification {

    @Subject
    private Polygon polygon = new Polygon(5)

    void setupSpec() {
        // setup code that needs to be run once at the start

    }

    void setup() {
        // setup code that needs to be run before every test method

    }

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
        sides << ([-1, 0, 3, 2] as Number)
    }

    def "should be able to create a polygon with #sides sides"() {

        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 8, 14]
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0

    }

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()
        @Subject
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red

    }

    def "should demonstrate 'verifyAll'"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        verifyAll(polygon) {
            numberOfSides == 4
            renderer == renderer
            //checkDefaultShape(polygon, renderer)

        }
//        with(polygon) {
//            numberOfSides == 5
//            renderer == null
//            //checkDefaultShape(polygon, renderer)
//
//        }

//    private void checkDefaultShape(Polygon polygon, Renderer renderer) {
//        assert polygon.numberOfSides == 4
//        assert polygon.renderer == renderer
//    }
    }

    void cleanup() {
        // code that tears down things at the end of a test method
    }

    void cleanupSpec() {
        // code that tears down everything at the end when all tests have run
    }
}