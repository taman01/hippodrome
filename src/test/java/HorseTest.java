import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class HorseTest {

    static Horse horse;

    @BeforeAll
    static void setUpAll()  {
        System.out.println("Тесты запущены");
        horse = new Horse("Horse", 2, 1);

    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Тесты закончены");
        horse = null;
    }

    @Test
    void nullNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t\t","\n\n\n\n"})
    void nullBlankException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0, 0));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    void nullSpeedException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    @Test
    void nullDistanceException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }




    @Test
    void getName() {
        assertEquals("Horse",horse.getName());

    }

    @Test
    void getSpeed() {
        assertEquals(2, horse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(1, horse.getDistance());
    }

    @Test
    void getDistanceByDefaultConstructor() {
        Horse horse = new Horse("Horse", 2);
        assertEquals(0,horse.getDistance());

    }

    @Test
    void moveWithRandom() {
        try(MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("Horse", 2).move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }


}