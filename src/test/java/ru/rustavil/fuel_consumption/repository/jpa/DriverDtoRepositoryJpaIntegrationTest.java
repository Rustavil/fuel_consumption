package ru.rustavil.fuel_consumption.repository.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DriverDtoRepositoryJpaIntegrationTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DriverRepositoryJpa driverRepository;

    private DriverDto expected;

    @Before
    public void setUp() throws Exception {
        assertThat(entityManager).isNotNull();
        assertThat(driverRepository).isNotNull();

        saveDriver();
    }

    @Test
    public void whenSearchDriverByIdentifierThenExpected() {
        DriverDto found = driverRepository.findByIdentifier(expected.getIdentifier());

        assertThat(found).isEqualTo(expected);
    }

    @After
    public void tearDown() throws Exception {
        removeDriver();
    }

    private void saveDriver() {
        expected = new DriverDto(111111L);
        entityManager.persist(expected);
        entityManager.flush();
    }

    private void removeDriver() {
        entityManager.remove(expected);
        entityManager.flush();
    }
}
