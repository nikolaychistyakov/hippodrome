import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
class HorseTest {

    @Test
    public void firstArgumentNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 100, 100));
    }

    @Test
    public void firstArgumentNullThrowsIllegalArgumentExceptionCheckMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 100, 100)
                , "Name cannot be null.");
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"     ", "    ", "   ", "  ", " ", "", "\t", "\n", "\f"})
    public void firstArgumentSpaceThrowsIllegalArgumentException(String args) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(args, 100, 100));
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "    ", "   ", "  ", " ", "", "\t", "\n", "\f"})
    public void firstArgumentSpaceThrowsIllegalArgumentExceptionCheckMessage(String args) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(args, 100, 100),
                "Name cannot be blank.");
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }


    @Test
    public void secondArgumentNegativeIntegerThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 100));

    }

    @Test
    public void secondArgumentNegativeIntegerThrowsIllegalArgumentExceptionCheckMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -1, 100),
                "Speed cannot be negative.");

        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void thirdArgumentNegativeIntegerThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 100, -55));

    }

    @Test
    public void thirdArgumentNegativeIntegerThrowsIllegalArgumentExceptionCheckMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 100, -55),
                "Distance cannot be negative.");

        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void testFirstArgumentConstructorEqualsGetName() {
        Horse horse = new Horse("Horse", 50, 100);
        assertEquals(horse.getName(), "Horse");
    }

    @Test
    public void testSecondArgumentConstructorEqualsGetSpeed() {
        Horse horse = new Horse("Horse", 50, 100);
        assertEquals(horse.getSpeed(), 50);
    }

    @Test
    public void testThirdArgumentConstructorEqualsGetDistance() {
        Horse horse = new Horse("Horse", 50, 100);
        assertEquals(horse.getDistance(), 100);
    }

    @Test
    public void testConstructorWithTwoArguments() {
        Horse horse = new Horse("Horse", 50);
        assertEquals(horse.getDistance(), 0);
    }


    @Test
    public void testMethodMoveCallsInsideTheGetRandomDoubleMethod() {

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse h = new Horse("h", 1, 1);
            h.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2d, 0.9d), times(1));
        }
    }


    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0, 4.0})
    public void testWhatMethodMoveAssignsTheDistanceValueCalculatedByTheFormula(Double d) {
        Horse h = new Horse("H", 10, 100);

        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.7)).thenReturn(d);

            assertEquals(h.getDistance() + 31 * d,
                    h.getDistance() + 31 * Horse.getRandomDouble(0.2, 0.7));
        }
    }
}