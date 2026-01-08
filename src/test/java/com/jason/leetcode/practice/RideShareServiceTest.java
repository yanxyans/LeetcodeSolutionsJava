package com.jason.leetcode.practice;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class RideShareServiceTest {

  @Test
  void testBasicRiderThenDriver() {
    RideShareService service = new RideShareService();

    // Rider requests first - should wait
    Rider rider = new Rider("R1", "Downtown");
    RideMatch match1 = service.requestRide(rider);
    assertNull(match1, "Rider should wait when no drivers available");
    assertEquals(1, service.getWaitingRidersCount());

    // Driver comes online - should match
    Driver driver = new Driver("D1", "Downtown");
    RideMatch match2 = service.driverOnline(driver);
    assertNotNull(match2, "Driver should match with waiting rider");
    assertEquals(rider.id, match2.rider.id);
    assertEquals(driver.id, match2.driver.id);
    assertEquals(0, service.getWaitingRidersCount());
    assertEquals(0, service.getAvailableDriversCount());
  }

  @Test
  void testBasicDriverThenRider() {
    RideShareService service = new RideShareService();

    // Driver comes online first - should wait
    Driver driver = new Driver("D1", "Downtown");
    RideMatch match1 = service.driverOnline(driver);
    assertNull(match1, "Driver should wait when no riders waiting");
    assertEquals(1, service.getAvailableDriversCount());

    // Rider requests - should match
    Rider rider = new Rider("R1", "Downtown");
    RideMatch match2 = service.requestRide(rider);
    assertNotNull(match2, "Rider should match with available driver");
    assertEquals(rider.id, match2.rider.id);
    assertEquals(driver.id, match2.driver.id);
    assertEquals(0, service.getWaitingRidersCount());
    assertEquals(0, service.getAvailableDriversCount());
  }

  @Test
  void testMultipleRidersOneDriver() {
    RideShareService service = new RideShareService();

    // 3 riders request
    Rider r1 = new Rider("R1", "Downtown");
    Rider r2 = new Rider("R2", "Airport");
    Rider r3 = new Rider("R3", "Mall");

    service.requestRide(r1);
    service.requestRide(r2);
    service.requestRide(r3);

    assertEquals(3, service.getWaitingRidersCount());

    // 1 driver comes online - should match with first rider (FIFO)
    Driver d1 = new Driver("D1", "Downtown");
    RideMatch match = service.driverOnline(d1);

    assertNotNull(match);
    assertEquals("R1", match.rider.id, "Should match with first rider (FIFO)");
    assertEquals(2, service.getWaitingRidersCount());
  }

  @Test
  void testConcurrentRequests() throws InterruptedException {
    RideShareService service = new RideShareService();
    int numRiders = 50;
    int numDrivers = 50;

    ExecutorService executor = Executors.newFixedThreadPool(20);
    CountDownLatch latch = new CountDownLatch(numRiders + numDrivers);

    // Submit 50 riders concurrently
    for (int i = 0; i < numRiders; i++) {
      final int id = i;
      executor.submit(
          () -> {
            try {
              Rider rider = new Rider("R" + id, "Location" + id);
              service.requestRide(rider);
            } finally {
              latch.countDown();
            }
          });
    }

    // Submit 50 drivers concurrently
    for (int i = 0; i < numDrivers; i++) {
      final int id = i;
      executor.submit(
          () -> {
            try {
              Driver driver = new Driver("D" + id, "Location" + id);
              service.driverOnline(driver);
            } finally {
              latch.countDown();
            }
          });
    }

    latch.await(5, TimeUnit.SECONDS);
    executor.shutdown();

    // Should have 50 matches, no waiting riders or drivers
    assertEquals(50, service.getMatches().size(), "Should have 50 matches");
    assertEquals(0, service.getWaitingRidersCount(), "Should have no waiting riders");
    assertEquals(0, service.getAvailableDriversCount(), "Should have no available drivers");
  }

  @Test
  void testNoDriverReuse() throws InterruptedException {
    RideShareService service = new RideShareService();

    // 1 driver, 2 riders
    Driver driver = new Driver("D1", "Downtown");
    service.driverOnline(driver);

    Rider r1 = new Rider("R1", "Downtown");
    Rider r2 = new Rider("R2", "Downtown");

    service.requestRide(r1);
    service.requestRide(r2);

    // Should have 1 match, 1 waiting rider
    assertEquals(1, service.getMatches().size());
    assertEquals(1, service.getWaitingRidersCount());
    assertEquals(0, service.getAvailableDriversCount());
  }

  @Test
  void testConcurrentMatchIntegrity() throws InterruptedException {
    RideShareService service = new RideShareService();
    int numPairs = 100;

    ExecutorService executor = Executors.newFixedThreadPool(40);
    CountDownLatch latch = new CountDownLatch(numPairs * 2);
    ConcurrentHashMap<String, Boolean> usedDrivers = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Boolean> usedRiders = new ConcurrentHashMap<>();

    // Submit riders and drivers concurrently
    for (int i = 0; i < numPairs; i++) {
      final int id = i;

      executor.submit(
          () -> {
            try {
              Rider rider = new Rider("R" + id, "Loc");
              service.requestRide(rider);
            } finally {
              latch.countDown();
            }
          });

      executor.submit(
          () -> {
            try {
              Driver driver = new Driver("D" + id, "Loc");
              service.driverOnline(driver);
            } finally {
              latch.countDown();
            }
          });
    }

    latch.await(10, TimeUnit.SECONDS);
    executor.shutdown();

    // Verify no duplicates
    List<RideMatch> matches = service.getMatches();
    for (RideMatch match : matches) {
      assertNull(
          usedDrivers.put(match.driver.id, true),
          "Driver " + match.driver.id + " matched multiple times");
      assertNull(
          usedRiders.put(match.rider.id, true),
          "Rider " + match.rider.id + " matched multiple times");
    }

    assertEquals(100, matches.size());
  }
}
