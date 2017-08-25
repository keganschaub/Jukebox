package tests;
/**
 * Unit test for class Usage that determines if something
 * has been done two times on the same calendar date.
 * 
 * This example was intended to show an example of test 
 * driven development where we:
 *  
 * 1) write what we want or need first as a test
 * 2) make the code compile
 * 3) make sure the tests fail (red bar)
 * 4) write the least amount of code to make the tests pass (green bar)
 * 
 */
import static org.junit.Assert.*;
import model.IUsage;

import org.junit.Test;

import usage.Usage;

public class UsageTest {
  
  @Test
  public void testGetters() {
    IUsage u1 = new Usage(1);
    assertEquals(1, u1.maxTimesForUse());
    IUsage u2 = new Usage(8);
    assertEquals(8, u2.maxTimesForUse());
  }
  
  @Test
  public void testTimesUsedAndCanUse() {
    IUsage u2 = new Usage(2);
    assertEquals(0, u2.timesUsed());
    assertTrue(u2.canUse());
    
    // Use once
    u2.use();
    assertEquals(1, u2.timesUsed());
    assertTrue(u2.canUse());
         
    // Use twice 
    assertTrue(u2.use());
    assertEquals(2, u2.timesUsed());
    assertFalse(u2.canUse());
    
    // But not a 3rd time
    assertFalse(u2.use());
    assertEquals(2, u2.timesUsed());
    assertFalse(u2.canUse());
    
  }

  @Test
  public void testUsage() {
    // Only 2 uses allowed for each Usage object on the current day.
    IUsage u = new Usage(2);
    assertTrue(u.canUse());
    
    u.use();
    assertTrue(u.canUse());
    assertEquals(1, u.timesUsed());

    u.use();
    assertFalse(u.canUse());
    assertEquals(2, u.timesUsed());
  }
  
  @Test
  public void testUsageWhenDateChanges() {
    // Only 2 uses allowed for each Usage object on the current day.
    IUsage logins = new Usage(4);
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertFalse(logins.canUse());
    assertEquals(4, logins.timesUsed());
    // The next method is not in the interface, so I would have
    // to change the type of logins to Usage, or cast like this:
    ((Usage) logins).pretendItIsTomorrow();
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertTrue(logins.use());
    assertFalse(logins.canUse());
    assertEquals(4, logins.timesUsed());
  }
  
  
}