package ru.rustavil.fuel_consumption.repository.jpa;

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

    @Before
    public void setUp() throws Exception {
        assertThat(entityManager).isNotNull();
        assertThat(driverRepository).isNotNull();
    }

    @Test
    public void findByIdentifier() {
        DriverDto expected = new DriverDto(111111L);
        entityManager.persist(expected);
        entityManager.flush();

        try {
            DriverDto found = driverRepository.findByIdentifier(expected.getIdentifier());

            assertThat(found).isEqualTo(expected);
        } finally {
            entityManager.remove(expected);
            entityManager.flush();
        }
    }
}
