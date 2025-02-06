import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void nullHorseException(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    void emptyHorseException(){

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());

    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name" + i ,i, i));
        }
        Hippodrome h = new Hippodrome(horses);

        assertEquals(horses, h.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));

        }

        new Hippodrome(horses).move();


        for(Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {

        Horse horse1 = new Horse("Name1", 1, 1);
        Horse horse2 = new Horse("Name1", 1, 3);
        Horse horse3 = new Horse("Name1", 1, 8);
        Horse horse4 = new Horse("Name1", 1, 10);
        Horse horse5 = new Horse("Name1", 1, 15);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));

       assertSame(horse5, hippodrome.getWinner());

    }
}