import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class HippodromeTest {

    @Test
    public void testAddInConstructorNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testAddInConstructorNullThrowsIllegalArgumentExceptionCheckMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null),
                "Horses cannot be null.");
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }


    @Test
    public void testAddInConstructorEmptyListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void testAddInConstructorEmptyListThrowsIllegalArgumentExceptionCheckMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()),
                "Horses cannot be empty.");
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }


    @Test
    public void testGetHorses() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("H" + i, i + 10, i + 15));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testGetWinner() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("H" + i, i + 10, i + 15));
        }
        List<Horse> sortHorses = horses.stream().sorted((n1, n2) -> Double.compare(n2.getDistance(), n1.getDistance()))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(sortHorses.get(0), hippodrome.getWinner());

    }

    @Test
    public void testMove() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(horses));
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }


}