package com.jason.leetcode.practice;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * RIDE SHARE SERVICE - CODING INTERVIEW PROBLEM
 *
 * You are building a ride-share matching service. Multiple riders can request rides
 * concurrently, and multiple drivers can accept rides concurrently.
 *
 * REQUIREMENTS:
 * 1. Riders can request rides with a specific location
 * 2. Drivers can come online and accept rides
 * 3. Match riders with available drivers (FIFO - first come, first served)
 * 4. Must be thread-safe for concurrent requests
 * 5. A driver can only handle one ride at a time
 * 6. Once matched, remove both rider and driver from available pools
 *
 * TODO: Complete the methods marked with TODO
 * TODO: Fix any thread-safety issues you find in the provided code
 */

class Rider {
  public final String id;
  public final String location;

  public Rider(String id, String location) {
    this.id = id;
    this.location = location;
  }
}

class Driver {
  public final String id;
  public final String location;

  public Driver(String id, String location) {
    this.id = id;
    this.location = location;
  }
}

class RideMatch {
  public final Rider rider;
  public final Driver driver;

  public RideMatch(Rider rider, Driver driver) {
    this.rider = rider;
    this.driver = driver;
  }
}

public class RideShareService {
  // Available riders waiting for a ride
  private BlockingQueue<Rider> waitingRiders = new LinkedBlockingQueue<>();

  // Available drivers ready to accept rides
  private BlockingQueue<Driver> availableDrivers = new LinkedBlockingQueue<>();

  // Completed matches
  private List<RideMatch> matches = new ArrayList<>();

  private ReentrantLock lock = new ReentrantLock();

  /**
   * TODO: A rider requests a ride. If a driver is available, match them immediately.
   * Otherwise, add the rider to the waiting queue.
   *
   * @param rider The rider requesting a ride
   * @return RideMatch if matched immediately, null if added to waiting queue
   */
  public RideMatch requestRide(Rider rider) {
    // TODO: Implement this method
    // Hint: Check if drivers are available, if so match and return
    // Otherwise add to waiting queue
    Driver driver = availableDrivers.poll();
    if (driver != null) {
      return createMatch(rider, driver);
    }

    waitingRiders.offer(rider);
    return null;
  }

  /**
   * TODO: A driver comes online and is ready to accept rides.
   * If riders are waiting, match with the first waiting rider immediately.
   * Otherwise, add the driver to the available pool.
   *
   * @param driver The driver coming online
   * @return RideMatch if matched immediately, null if added to available pool
   */
  public RideMatch driverOnline(Driver driver) {
    // TODO: Implement this method
    // Hint: Check if riders are waiting, if so match with first one
    // Otherwise add to available drivers
    Rider rider = waitingRiders.poll();
    if (rider != null) {
      return createMatch(rider, driver);
    }

    availableDrivers.offer(driver);
    return null;
  }

  /**
   * TODO: Record a successful match between rider and driver
   *
   * @param rider The matched rider
   * @param driver The matched driver
   * @return The RideMatch object
   */
  private RideMatch createMatch(Rider rider, Driver driver) {
    RideMatch match = new RideMatch(rider, driver);
    lock.lock();
    try {
      matches.add(match);
    } finally {
      lock.unlock();
    }
    return match;
  }

  /**
   * Get all completed matches
   */
  public List<RideMatch> getMatches() {
    return new ArrayList<>(matches);
  }

  /**
   * Get count of waiting riders
   */
  public int getWaitingRidersCount() {
    return waitingRiders.size();
  }

  /**
   * Get count of available drivers
   */
  public int getAvailableDriversCount() {
    return availableDrivers.size();
  }
}
